<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit department</title>
    </head>

    <body>
        <p> <a href="departments">Departments</a> </p>

        <form method="get" name="add" action="/res/confirm_department">
            <input type="hidden" name="id" value="${id}">
            <input type="hidden" name="head_id" value="${head_id}">
            
            Name: <br>
            <input type="text" name="name" value="${name}"> <br>
            
            <%
                if (!request.getAttribute("id").equals("-1")) {

                out.println("Director: " + request.getAttribute("director") + "<a href=\"staff_assignment?position=director&head_id=" + request.getAttribute("head_id") + "&director_id=" + request.getAttribute("director_id") + "&dep_id=" + request.getAttribute("id") + "\">change</a> <a href=\"department_edit?id=" + request.getAttribute("id") + "&director_id=-1&head_id=" + request.getAttribute("head_id") + "\">delete</a><br> <input type=\"hidden\" name=\"director_id\" value=" + request.getAttribute("director_id") + "> Head department: " + request.getAttribute("head") + " <a href=\"department_assignment?dep_id=" + request.getAttribute("id") + "&head_id=" + request.getAttribute("head_id") + "&director_id=" + request.getAttribute("director_id") + "\">change</a> <a href=\"department_edit?id=" + request.getAttribute("id") + "&director_id=" + request.getAttribute("director_id") + "&head_id=-1\">delete</a><br>");

                }
            %>

            <input type="submit" name="submit" value="Confirm department">

        </form>
    </body>
</html>

