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
        <p><a href="/res/department_edit?id=${id}">Edit</a>
        <form method="get" action="/res/delete_department">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Delete department">
        </form>
    </body>
</html>

