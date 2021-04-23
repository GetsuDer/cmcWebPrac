<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.util.ArrayList"%>

<html>
    <head>
        <title>Department info</title>
    </head>

    <body>
        Name: ${name} <br>
        Director: ${director} <br>
        Head department: ${headDepartment} <br>

        SubDepartments: <br>
        <%
            ArrayList<String> subs = (ArrayList<String>)request.getAttribute("subs");
            for (String sub : subs) {
                out.println(sub + "<br>");
                }
        %>
        Positions: <br>
        <%
            ArrayList<String> positions = (ArrayList<String>)request.getAttribute("poss");
            for (String pos : positions) {
                out.println(pos + "<br>");
            }
        %>
        <p><a href="/res/departments">Return</a>
        <p><a href="/res/department_edit?id=${id}">Edit</a>
        <form method="get" action="/res/delete_department">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Delete department">
        </form>
    </body>
</html>

