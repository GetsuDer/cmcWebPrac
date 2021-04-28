<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.ArrayList"%>

<html>
    <head>
        <title>Staff Member info</title>
    </head>

    <body>
        <center>
        Name: ${name} <br>
        Address: ${address} <br>
        Education: ${education} <br>
        WorkStart: ${workStart} <br>
        Current positions: <br>
        <%
            StaffMember mem = Factory.getInstance().getStaffMemberDAO().getStaffMemberById(Long.parseLong(request.getAttribute("id").toString()));
            Collection<Employee> emps = Factory.getInstance().getEmployeeDAO().getEmployeesByStaffMember(mem);
            for (Employee emp : emps) {
                if (emp.getEndTime() != null) continue;
                Position pos = emp.getPosition();
                out.println(pos.getName() + "<br>");
                out.println("Responsibilities: " + pos.getResponsibilities() + "<br>");
                if (emp.getPosition().getDepartment() != null) {
                    Department dep = emp.getPosition().getDepartment();
                    out.println("Department:<a href=/res/department_info?id=" + dep.getId() + ">" + dep.getName() + "</a><br>");
                }
                out.println("<br>");
            }
            out.println("Previous positions: ");
            for (Employee emp : emps) {
                if (emp.getEndTime() == null) continue;
                Position pos = emp.getPosition();
                out.println(pos.getName() + "<br>");
                out.println("Responsibilities: " + pos.getResponsibilities() + "<br>");
                if (emp.getPosition().getDepartment() != null) {
                    Department dep = emp.getPosition().getDepartment();
                    out.println("Department:<a href=/res/department_info?id=" + dep.getId() + ">" + dep.getName() + "</a><br>");
                }
                out.println("<br>");

            }
        %>
        <%
            if (request.getAttribute("back").equals("staff")) {
                out.println("<a href=/res/staff>Return</a>");
            } else {
                out.println("<a href=/res/department_info?id=" + request.getAttribute("dep_id") + "&director_id=" + request.getAttribute("director_id") + ">Return</a>");
            }   
        %>

        <form method="get" action="/res/staff_edit">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Edit">
        </form>

        <form method="get" action="/res/delete_staff">
            <input type="hidden" name="id" value="${id}">
            <input type="submit" value="Delete">
        </form>
    </body>
</html>

