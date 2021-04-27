<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.util.ArrayList"%>

<html>
    <head>
        <title>Department info</title>
    </head>

    <body>
        <center>
        Name: ${name} <br>
        Director: ${director} 
        <% 
            if (!request.getAttribute("director_id").equals("-1")) {
                out.println("<a href=/res/staff_info?back=dep&dep_id=" + request.getAttribute("id") + "&id=" + request.getAttribute("director_id") + ">see</a>");
            }
        %>
        <br>

        Head department: ${head}
        <%
             String head_id = request.getAttribute("head_id").toString();
             if (!head_id.equals("-1")) {
                 Department head = Factory.getInstance().getDepartmentDAO().getDepartmentById(Long.parseLong(head_id));
                 out.println("<a href=/res/department_info?director_id=" + ((head.getDirector() == null) ? "-1" : head.getDirector().getId().toString()) + "&id=" + head_id + ">see</a>");
            }
        %>
        <br>

        SubDepartments: <br>
        <%
            ArrayList<String> subs = (ArrayList<String>)request.getAttribute("subs");
            for (String sub : subs) {
                out.println("<a href=/res/department_info?id=" + sub.split(" ")[1] + ">" + sub.split(" ")[3] + "</a><br>");
            
            }
        %>
        Positions: <br>
        <%
            ArrayList<String> positions = (ArrayList<String>)request.getAttribute("poss");
            for (String pos : positions) {
                String pos_id = pos.split(" ")[1];
                out.println(pos.split(" ")[3] + "<a href=/res/position_edit?id=" + pos_id + ">edit</a><br>");
                Position position = Factory.getInstance().getPositionDAO().getPositionById(Long.parseLong(pos_id));
                Collection<StaffMember> workers = Factory.getInstance().getStaffMemberDAO().getStaffMembersByPosition(position);
                for (StaffMember mem : workers) {
                    out.println("<a href=/res/staff_info?back=dep&director_id=" + request.getAttribute("director_id") + "&id=" + mem.getId().toString() + "&dep_id=" + request.getAttribute("id") + ">" + mem.getName() + "</a><br>");
                }
                out.println("<br>");
            }
        
        %>
        <p><form method="get" action="/res/position_edit">
            <input type="hidden" name="id" value="-1">
            <input type="hidden" name="dep_id" value="${id}">
            <input type="submit" value="add position">
        </form>
        <a href="/res/departments">Return</a>
        <a href="/res/department_edit?id=${id}&director_id=${director_id}&head_id=${head_id}">Edit</a>
        <form method="get" action="/res/delete_department">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Delete department">
        </form>
    </body>
</html>

