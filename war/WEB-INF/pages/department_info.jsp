<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection"%>

<html>
    <head>
        <title>Department info</title>
    </head>

    <body>
        Name: "${name}" <br>
        Director: "${director}" <br>
        Head department: "${headDepartment}" <br>

        <p><a href="/res/departments">Return</a>
        <p><a href="/res/department_edit">Edit</a>
        <form method="get" action="/delete_department">
            <input type="submit" value="Delete department">
        </form>
    </body>
</html>

