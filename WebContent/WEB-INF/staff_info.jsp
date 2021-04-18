<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection"%>

<html>
    <head>
        <title>Staff Member info</title>
    </head>

    <body>
        <% 
            StaffMemberDAO staffMemberDAO = Factory.getInstance().getStaffMemberDAO();
            StaffMember mem = staffMemberDAO.getStaffMemberById(request.getParameter("id"));
            System.out.println(mem);
            Collection<Position> positions = Factory.getInstance().getPositionDAO().getPositionsByStaffMember(mem); 
            for (Position pos : positions) {
                System.out.println(pos);
            }
        %>
        <p><a href="staff">Return</a></p>
        <p><a href="staff_edit">Edit</a></p>
        <form method="get" action="delete">
            <input type="submit" name="Delete">
        </form>
    </body>
</html>

