<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit department</title>
    </head>

    <body>
        <p> <a href="departments">Departments</a> </p>

        <form method="get" action="confirm">
            Name: <br>
            <input type="text" name="name" value="${name}"> <br>
            
            Director: <br>
            <input type="text" name="director" value="${director}"> <br>
            
            Head department: <br>
            <input type="text" name="head department" value="${headDepartment}"> <br>
            
            <a href="position_edit">Add position</a>
            
            <%
               /* Collection<Position> positions = Factory.getInstance().getPositionDAO().getPositionsByDepartment(request.getParameter("department");
                iterator = positions.iterator();
                while (iterator.hasNext()) {
                Position position = (Position) iterator.next();
                out.println(position);*/
            %>
            <input type="submit" value="Confirm department">

        </form>
    </body>
</html>

