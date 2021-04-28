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
                if (request.getAttribute("position").equals("director")) {
                    out.println("<a href=department_edit?" + (request.getAttribute("head_id") == null ? "" : "head_id=" + request.getAttribute("head_id") + "&") + "id=" + request.getAttribute("dep_id") +  "&director_id=" + mem.getId() + ">" + mem + "</a><br>");
                } else {
                    out.println("<a href=department_info?pos_id=" + request.getAttribute("pos_id") + "&mem_id=" + mem.getId() + "&id=" + request.getAttribute("dep_id") + ">" + mem + "</a><br>");
                }
            }
        %>
        <p><a href="department_edit?id=${dep_id}&director_id=${director_id}&head_id=${head_id}">Return</a></p>

    </body>
</html>

