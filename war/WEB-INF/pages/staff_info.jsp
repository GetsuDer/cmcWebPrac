<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection"%>

<html>
    <head>
        <title>Staff Member info</title>
    </head>

    <body>
        Name: ${name} <br>
        Address: ${address} <br>
        Education: ${education} <br>
        WorkStart: ${workStart} <br>

        <a href="/res/staff">Return</a>
        <a href="/res/staff_edit">Edit</a>
        <form method="get" action="/res/delete_staff">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Delete">
        </form>
    </body>
</html>

