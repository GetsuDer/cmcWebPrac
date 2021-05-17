<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit staff</title>
    </head>

    <body>
        <form method="get" name="edit" action="/res/confirm_staff">
            <input type="hidden" name="id" value="${id}">
            Name: <br>
            <input type="text" name="name" value="${name}"> <br>
            
            Address: <br> 
            <input type="text" name="address" value="${address}"> <br>
            
            Education: <br>
            <input type="text" name="education" value="${education}"> <br>
            
            Date of employment: (format: dd/mm/yyyy)<br> 
            <input type="text" name="workStart" value="${workStart}"> <br>
            
            <input type="submit" name="confirm" value="Confirm staff member">

        </form>
        
        <%
           if (request.getAttribute("id").equals("-1")) {
               out.println("<a href=\"/res/staff\">Return</a>");
           } else {
               out.println("<a href=\"/res/staff_info?id=" + request.getAttribute("id") + "&back=staff\">Return</a>");
           }
        %>
    </body>
</html>

