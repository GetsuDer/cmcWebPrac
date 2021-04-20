<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator"%>
<html>
    <head>
        <title>Staff members page</title>
    </head>

    <body>
        <p> <a href="/res/">Main</a> </p>
        <p> 
        <form method="get" action="/res/add_staff">
            <input type="submit" value="Add staff member"/>
        </form>
        </p>
        <form method="get" action="/find">
            Name: <br>
            <input type="text" name="name"><br>
            Address: <br>
            <input type="text" name="address"><br>
            Date of employment: <br>
            <input type="text" name="employment_date"><br>
            
            <input type="submit" value="Filter">
        </form>
        <%
            Collection<StaffMember> members = Factory.getInstance().getStaffMemberDAO().getAllStaffMembers();
            for (StaffMember mem : members) {
                out.println(mem + "<br>");
            }
        %>
    </body>
</html>

