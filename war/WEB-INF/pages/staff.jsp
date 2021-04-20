<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator"%>
<html>
    <head>
        <title>Staff members page</title>
    </head>

    <body>
        <p> <a href="../res/">Main</a> </p>
        <p> <a href="staff_edit">Add staff member</a> </p>
        <form method="get" action="/find">
            Name:<input type="text" name="name"><br>
            Address:<input type="text" name="address"><br>
            Date of employment:<input type="text" name="employment_date"><br>
            <input type="submit" name="find" value="Filter">
        </form>
        <%
            Collection<StaffMember> members = Factory.getInstance().getStaffMemberDAO().getAllStaffMembers();
            for (StaffMember mem : members) {
                out.println(mem + "<br>");
            }
        %>
    </body>
</html>

