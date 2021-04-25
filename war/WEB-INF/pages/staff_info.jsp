<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.ArrayList"%>

<html>
    <head>
        <title>Staff Member info</title>
    </head>

    <body>
        Name: ${name} <br>
        Address: ${address} <br>
        Education: ${education} <br>
        WorkStart: ${workStart} <br>
        Positions: <br>
        <%
            ArrayList<String> poss = (ArrayList<String>)request.getAttribute("poss");
            for (String s : poss) {
                out.println(s + "<br>");
            }
        %>
        <%
            if (request.getAttribute("back").equals("staff")) {
                out.println("<a href=/res/staff>Return</a>");
            } else {
                out.println("<a href=/res/department_info?id=" + request.getAttribute("dep_id") + "&director_id=" + request.getAttribute("id") + ">Return</a>");
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

