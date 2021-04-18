<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection"%>

<html>
    <head>
        <title>Department info</title>
    </head>

    <body>
        <% 
            DepartmentDAO departmentDAO = Factory.getInstance().getDepartmentDAO();
            Department department = departmentDAO.getDepartmentById(request.getParameter("id"));
            System.out.println(department);
            Collection<Department> subDeps = departmentDAO.getSubDepartments(department);
            for (Department dep : subDeps) {
                System.out.println(dep);
            }
            Collection<Position> positions = Factory.getInstance().getPositionDAO().getPositionsByDepartment(department);
            for (Position pos : positions) {
                System.out.println(pos);
            }
        %>
        <p><a href="departments">Return</a></p>
        <p><a href="department_edit">Edit</a></p>
        <form method="get" action="delete">
            <input type="submit" name="Delete">
        </form>
    </body>
</html>

