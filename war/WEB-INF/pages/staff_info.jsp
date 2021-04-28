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
        Positions: <br>
        <%
            ArrayList<String> poss = (ArrayList<String>)request.getAttribute("poss");
            for (String s : poss) {
                out.println(s.split(" ")[3] + "<br>");
                out.println("Responsibilities: " + s.split(" ")[5] + "<br>");
                String dep_id = s.split(" ")[7];
                if (dep_id != null) {
                    Department dep = Factory.getInstance().getDepartmentDAO().getDepartmentById(Long.parseLong(dep_id));
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

