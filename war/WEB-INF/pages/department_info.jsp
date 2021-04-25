<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.util.ArrayList"%>

<html>
    <head>
        <title>Department info</title>
    </head>

    <body>
        Name: ${name} <br>
        Director: ${director} <br>
        Head department: ${headDepartment}
        <%
            if (!request.getAttribute("headDepartment").equals("")) {
                out.println("<a href=/res/department_info?id=" + request.getAttribute("headDepartment") + ">see</a>");
            }
        %>
        <br>
        SubDepartments: <br>
        <%
            ArrayList<String> subs = (ArrayList<String>)request.getAttribute("subs");
            for (String sub : subs) {
                out.println(sub + "<a href=/res/department_info?id=" + sub.split(" ")[1] + ">see</a><br>");
            
            }
        %>
        Positions: <br>
        <%
            ArrayList<String> positions = (ArrayList<String>)request.getAttribute("poss");
            for (String pos : positions) {
            out.println(pos + "<a href=/res/position_edit?id=" + pos.split(" ")[1] + ">edit</a><br>");
            }
        
        %>
        <p><form method="get" action="/res/position_edit">
            <input type="hidden" name="id" value="-1">
            <input type="hidden" name="dep_id" value="${id}">
            <input type="submit" value="add position">
        </form>
        <a href="/res/departments">Return</a>
        <a href="/res/department_edit?id=${id}&director_id=${director_id}">Edit</a>
        <form method="get" action="/res/delete_department">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Delete department">
        </form>
    </body>
</html>

