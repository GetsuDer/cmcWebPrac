<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.util.ArrayList"%>
<html>
    <head>
        <title>Choose staff member</title>
    </head>

    <body>
        <center> Choose staff member;
        <%
            Collection<StaffMember> members = Factory.getInstance().getStaffMemberDAO().getAllStaffMembers();
            for (StaffMember mem : members) {
            out.println("<a href=department_edit?id=" + request.getAttribute("dep_id") +  "&director_id=" + mem.getId() + ">" + mem + "</a><br>");
                }
        %>
        <p><a href="department_edit?id=${dep_id}">Return</a></p>

    </body>
</html>

