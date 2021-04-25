<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit position</title>
    </head>

    <body>
        <p> <a href="/res/department_info?id=${dep_id}">Back</a> </p>
        <form method="get" action="/res/confirm_position">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="dep_id" value="${dep_id}">
            Name: <br>
            <input type="text" name="name" value="${name}"> <br>
            Size: <br>
            <input type="text" name="size" value="${size}"> <br>
            Duties: <br>
            <input type="text" name="duties" value="${duties}"> <br>

            <input type="submit" value="Confirm Position">
        </form>
        <form method="get" action="/res/delete_position">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="dep_id" value="${dep_id}">
            <input type="submit" value="Delete">
        </form>

    </body>
</html>

