<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit department</title>
    </head>

    <body>
        <p> <a href="departments">Departments</a> </p>
        <form method="get" action="confirm">
            <input type="text" name="name">
            <input type="text" name="director">
            <input type="text" name="head department">
            <a href="position_edit">Add position</a>
            
            <%
                Collection<Position> positions = Factory.getInstance().getPositionDAO().getPositionsByDepartment(request.getParameter("department");
                iterator = positions.iterator();
                while (iterator.hasNext()) {
                Position position = (Position) iterator.next();
                System.out.println(position);
            %>
            <input type="submit" name="Confirm department">

        </form>
    </body>
</html>

