<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Choose staff member</title>
    </head>

    <body>
        <%
            Collection<StaffMember> members = Factory.getInstance().getStaffMemberDAO().getAllStaffMembers();
            for (StaffMember mem : members) {
                System.out.println(mem);
                }
        %>
        <p><a href="departments">Return</a></p>

    </body>
</html>

