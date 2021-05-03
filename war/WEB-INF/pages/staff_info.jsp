<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.ArrayList, java.text.SimpleDateFormat, java.util.Date"%>

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
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            for (Employee emp : emps) {
                if (emp.getEndTime() == null) continue;
                Position pos = emp.getPosition();
                out.println(pos.getName() + "<br>");
                out.println("Responsibilities: " + pos.getResponsibilities() + "<br>");
                if (emp.getPosition().getDepartment() != null) {
                    Department dep = emp.getPosition().getDepartment();
                    out.println("Department:<a href=/res/department_info?id=" + dep.getId() + ">" + dep.getName() + "</a><br>");
                }
                out.println("<form method=\"get\" action=\"/res/editOldEmployee\"> <input type=\"hidden\" name=\"id\" value=\"" + emp.getId() + "\"> <input type=\"text\" name=\"workStart\" value=\"" + (emp.getStartTime() == null ? "" : format.format(emp.getStartTime())) + "\"><input type=\"text\" name=\"workEnd\" value=\"" + (emp.getEndTime() == null ? "" : format.format(emp.getEndTime())) + "\"> <input type=\"submit\" value=\"confirm\"> <input type=\"hidden\" name=\"dep_id\" value=\"" + request.getAttribute("dep_id") + "\"> <input type=\"hidden\" name=\"director_id\" value=\"" + request.getAttribute("director_id") + "\"> <input type=\"hidden\" name=\"back\" value=\"" + request.getAttribute("back") + "\"></form>");
                out.println("<form method=\"get\" action=\"/res/deleteOldEmployee\"> <input type=\"hidden\" name=\"id\" value=\"" + emp.getId() + "\"> <input type=\"submit\" value=\"delete\"> <input type=\"hidden\" name=\"dep_id\" value=\"" + request.getAttribute("dep_id") + "\"> <input type=\"hidden\" name=\"director_id\" value=\"" + request.getAttribute("director_id") + "\"> <input type=\"hidden\" name=\"back\" value=\"" + request.getAttribute("back") + "\"></form> <br>");

            }
            out.println("<br>");
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

