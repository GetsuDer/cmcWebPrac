<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator"%>
<html>
    <head>
        <title>Staff members page</title>
    </head>

    <body>
        <p> <a href="main">Main</a> </p>
        <p> <a href="staff_edit">Add staff member</a> </p>
        <form method="get" action="/find">
            <input type="text" name="name">
            <input type="text" name="address">
            <input type="text" name="employment_date">
            <input type="submit" name="find" value="add">
        </form>
        <%
            Collection<StaffMember> members = Factory.getInstance().getStaffMemberDAO().getAllStaffMembers();
            for (StaffMember mem : members) {
                System.out.println(mem);
            }
        %>
    </body>
</html>

