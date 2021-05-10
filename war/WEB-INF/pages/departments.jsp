<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator"%>
<html>
    <head>
        <title>Departments page</title>
    </head>

    <body>
        <p> <h1> <center> <a href="/res/">Main</a> </p>
        <p> 
        <form method="get" name="add" action="/res/add_department">
            <input type="submit" name="add" value="Add department">
        </form>
        </p>
        <form method="get" action="/res/filter_departments">
            Name:<input type="text" name="filter_name" value="${filter_name}">
            <input type="submit" value="Filter">
        </form>
        </h1>
        <%
            Collection<Department> departments = Factory.getInstance().getDepartmentDAO().getAllDepartments();
            for (Department department : departments) {
                String name_info = (department.getName() == null) ? "no name" : department.getName();
                
                Object filter = request.getAttribute("filter_name");
                if ((filter != null && !filter.equals("")) && (department.getName() == null || !department.getName().contains(filter.toString()))) continue;
                String dir_id = (department.getDirector() == null) ? "-1" : department.getDirector().getId().toString();
                String director_info = (department.getDirector() == null) ? "none" : department.getDirector().getName();
                String head_info = (department.getHeadDepartment() == null) ? "none" : department.getHeadDepartment().getName();
                if (head_info == null) head_info = "no name";
                if (director_info == null) director_info = "no name";
                out.println("<center><br>" + "Name: " + name_info + " Director: " + director_info + " Head Department: " + head_info + "<a href=/res/department_info?back=deps&id=" + department.getId() + "&director_id=" + dir_id + "> more</a>");
                
            }
        %>
    </body>
</html>

