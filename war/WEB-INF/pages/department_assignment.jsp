<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.util.ArrayList"%>
<html>
    <head>
        <title>Choose department</title>
    </head>

    <body>
        <%
            Collection<Department> departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
            for (Department dep : departments) {
                if (dep.getId().toString().equals(request.getAttribute("dep_id").toString())) continue;
                out.println("<a href=department_edit?director_id=" + request.getAttribute("director_id") + "&id=" + request.getAttribute("dep_id") + "&head_id=" + dep.getId() + ">" + dep + "</a><br>");
            }
        %>
        <p><a href="department_edit?id=${dep_id}&head_id=${head_id}&director_id=${director_id}">Return</a></p>

    </body>
</html>

