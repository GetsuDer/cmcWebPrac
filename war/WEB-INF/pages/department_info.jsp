<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.util.ArrayList, java.text.SimpleDateFormat, java.util.Date"%>

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
                out.println("<a href=/res/staff_info?back=dep&director_id=" + request.getAttribute("director_id") + "&dep_id=" + request.getAttribute("id") + "&id=" + request.getAttribute("director_id") + ">see</a>");
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

        <br> SubDepartments: <br>
        <%
            ArrayList<String> subs = (ArrayList<String>)request.getAttribute("subs");
            for (String sub : subs) {
                out.println("<a href=/res/department_info?id=" + sub.split(" ")[1] + ">" + sub.split(" ")[3] + "</a><br>");
            
            }
        %>
        <br> Positions: <br>
        <%
            ArrayList<String> positions = (ArrayList<String>)request.getAttribute("poss");
            for (String pos : positions) {
                String pos_id = pos.split(" ")[1];
                Position position = Factory.getInstance().getPositionDAO().getPositionById(Long.parseLong(pos_id));
                out.println("position: " + (position.getName() == null ? "unnamed" : position.getName()) + "<a href=/res/position_edit?dep_id=" + request.getAttribute("id") + "&id=" + pos_id + "> edit</a><br>");
                
                Collection<Employee> workers = Factory.getInstance().getEmployeeDAO().getEmployeesByPosition(position);
                Date cur_date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                int current_workers = 0;
                for (Employee emp : workers) {
                    if (emp.getEndTime() != null) continue;
                    current_workers++;
                    StaffMember mem = emp.getStaffMember();
                    out.println("<a href=/res/staff_info?back=dep&director_id=" + request.getAttribute("director_id") + "&id=" + mem.getId().toString() + "&dep_id=" + request.getAttribute("id") + ">" + mem.getName() + "</a><br>");
                    out.println("<form method=\"get\" action=\"/res/setWorkStart\"> <input type=\"hidden\" name=\"pos_id\" value=" + pos_id + "> <input type=\"hidden\" name=\"mem_id\" value=" + mem.getId() + "><input type=\"text\" name=\"workStart\" value=" + (emp.getStartTime() == null ? "" : format.format(emp.getStartTime())) + "> <input type=\"submit\" value=\"set work start\"></form>");
                    out.println("<form method=\"get\" action=\"/res/remove_worker\"> <input type=\"text\" name=\"workEnd\" value=" + format.format(cur_date) + "> <input type=\"submit\" value=\"remove\"> <input type=\"hidden\" name=\"pos_id\" value=" + pos_id + "> <input type=\"hidden\" name=\"mem_id\" value=" + mem.getId().toString() + "> <input type=\"hidden\" name=\"dep_id\" value=" + request.getAttribute("id") + "></form>");
                }
                for (int i = 0; i < position.getSize() - current_workers; i++) {
                    out.println("<a href=/res/staff_assignment?pos_id=" + pos_id + "&position=worker&dep_id=" + request.getAttribute("id") + ">add worker</a><br>");
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
        <form method="get" name="delete" action="/res/delete_department">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Delete department">
        </form>
    </body>
</html>

