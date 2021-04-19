<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator"%>
<html>
    <head>
        <title>Department page</title>
    </head>

    <body>
        <p> <a href="main">Main</a> </p>
        <p> <a href="department_edit">Add department</a> </p>
        <form method="get" action="/find">
            <input type="text" name="name">
            <input type="submit" name="find" value="add">
        </form>
        <%
            Collection departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
            Iterator iterator = departments.iterator();
            while (iterator.hasNext()) {
                Department department = (Department) iterator.next();
                System.out.println(department);
            }
        %>
    </body>
</html>

