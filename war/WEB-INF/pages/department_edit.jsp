<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit department</title>
    </head>

    <body>
        <p> <a href="departments">Departments</a> </p>

        <form method="get" action="/res/confirm_department">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="head_id" value="${head_id}">
            
            Name: <br>
            <input type="text" name="name" value="${name}"> <br>
            
            Director: ${director}
            <a href="staff_assignment?dep_id=${id}">change</a> <br>
            <input type="hidden" name="director_id" value="${director_id}">

            Head department: ${head}
            <a href="department_assignment?dep_id=${id}&head_id=${head_id}&director_id=${director_id}">change</a><br>
            
            <input type="submit" value="Confirm department">

        </form>
    </body>
</html>

