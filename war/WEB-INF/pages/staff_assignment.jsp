<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.util.ArrayList, java.util.HashSet"%>
<html>
    <head>
        <title>Choose staff member</title>
    </head>

    <body>
        <center> Choose staff member;
        <%
            StaffMemberDAO dao = Factory.getInstance().getStaffMemberDAO();
            Position pos = new Position();
            Collection<StaffMember> already_works = new HashSet<StaffMember>();
            if (request.getAttribute("pos_id") != null) {
                pos = Factory.getInstance().getPositionDAO().getPositionById(Long.parseLong(request.getAttribute("pos_id").toString()));
                already_works = dao.getStaffMembersByPosition(pos);
            }
            Collection<StaffMember> members = dao.getAllStaffMembers();
            for (StaffMember mem : members) {
                if (request.getAttribute("position").equals("director")) {
                    out.println("<a href=department_edit?" + (request.getAttribute("head_id") == null ? "" : "head_id=" + request.getAttribute("head_id") + "&") + "id=" + request.getAttribute("dep_id") +  "&director_id=" + mem.getId() + ">" + mem + "</a><br>");
                } else {
                    boolean good = true;

                    for (StaffMember bad_mem : already_works) {
                        if (bad_mem.getId() == mem.getId()) {
                            good = false;
                            break;
                        }
                    }
                    if (good) out.println("<a href=department_info?pos_id=" + request.getAttribute("pos_id") + "&mem_id=" + mem.getId() + "&id=" + request.getAttribute("dep_id") + ">" + mem + "</a><br>");
                }
            }
        %>
        <p><a href="department_edit?id=${dep_id}&director_id=${director_id}&head_id=${head_id}">Return</a></p>

    </body>
</html>

