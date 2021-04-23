<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Choose department</title>
    </head>

    <body>
        <%
            Collection<Department> departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
            for (Department dep : departments) {
                System.out.println(dep);
                }
        %>
        <p><a href="positions">Return</a></p>

    </body>
</html>

