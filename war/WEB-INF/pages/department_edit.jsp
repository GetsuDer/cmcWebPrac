<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit department</title>
    </head>

    <body>
        <p> <a href="departments">Departments</a> </p>

        <form method="get" action="/res/confirm_department">
            <input type="hidden" name="id" value="${id}">
            Name: <br>
            <input type="text" name="name" value="${name}"> <br>
            
            Director: ${director}<br>
            <a href="staff_assignment?dep_id=${id}">choose Director</a> <br>
            <input type="hidden" name="director_id" value="${director_id}">

            Head department: <br>
            <input type="text" name="headDepartment" value="${headDepartment}"> <br>
            
            <input type="submit" value="Confirm department">

        </form>
    </body>
</html>

