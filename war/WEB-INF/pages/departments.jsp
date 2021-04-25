<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator"%>
<html>
    <head>
        <title>Departments page</title>
    </head>

    <body>
        <p> <h1> <center> <a href="/res/">Main</a> </p>
        <p> 
        <form method="get" action="/res/add_department">
            <input type="submit" value="Add department">
        </form>
        </p>
        <form method="get" action="/find">
            Name:<input type="text" name="name">
            <input type="submit" value="Filter">
        </form>
        </h1>
        <%
            Collection departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
            Iterator iterator = departments.iterator();
            while (iterator.hasNext()) {
                Department department = (Department) iterator.next();
                String dir_id = (department.getDirector() == null) ? "-1" : department.getDirector().getId().toString();
                out.println("<br>" + department + "<a href=/res/department_info?id=" + department.getId() + "&director_id=" + dir_id + ">more</a>");
                
            }
        %>
    </body>
</html>

