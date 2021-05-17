<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.io.*, logic.*, DAO.*, java.util.Collection, java.util.Iterator, java.text.SimpleDateFormat, java.text.DateFormat, java.util.Date"%>
<html>
    <head>
        <title>Staff members page</title>
    </head>

    <body>
        <p> <center> <h1><a href="/res/">Main</a> </h1></p>
        <p> 
        <form method="get" name="add" action="/res/add_staff">
            <input type="submit" name="add" value="Add staff member"/>
        </form>
        </p>
        <form method="get" name="filter" action="/res/filter_staff">
            Name: <br>
            <input type="text" name="filter_name" value="${filter_name}"><br>
            Address: <br>
            <input type="text" name="filter_address" value="${filter_address}"><br>
            Date of employment: (later than, format dd/mm/yyyy)<br>
            <input type="text" name="filter_workStart" value="${filter_workStart}"><br>
            
            <input type="submit" name="filter" value="Filter">
        </form>
        <%
            Collection<StaffMember> members = Factory.getInstance().getStaffMemberDAO().getAllStaffMembers();
            String filter_name = request.getAttribute("filter_name").toString();
            String filter_address = request.getAttribute("filter_address").toString();
            String filter_workStart = request.getAttribute("filter_workStart").toString();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date filterDate = null;
            if (!filter_workStart.equals("")) {
                filterDate = format.parse(filter_workStart);
            }
            for (StaffMember mem : members) {
                if (!filter_name.equals("") && (mem.getName() == null || !mem.getName().toLowerCase().contains(filter_name.toLowerCase()))) continue;
                if (!filter_address.equals("") && (mem.getAddress() == null || !mem.getAddress().toLowerCase().contains(filter_address.toLowerCase()))) continue;
                if (filterDate != null && mem.getWorkStart() != null && filterDate.after(mem.getWorkStart())) continue;
                out.println("<a href=/res/staff_info?back=staff&dep_id=-1&id=" + mem.getId() + ">" + (mem.getName() == null ? "unnamed staff member" : mem.getName()) + "</a><br>");
            }
        %>
    </body>
</html>

