<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit staff</title>
    </head>

    <body>
        <p> <a href="staff">Staff members</a> </p>
        <form method="get" action="/res/confirm_staff">
            <input type="hidden" name="id" value="${id}">
            Name: <br>
            <input type="text" name="name" value="${name}"> <br>
            
            Address: <br> 
            <input type="text" name="address" value="${address}"> <br>
            
            Education: <br>
            <input type="text" name="education" value="${education}"> <br>
            
            Date of employment: (format: dd/mm/yyyy)<br> 
            <input type="text" name="workStart" value="${workStart}"> <br>
            
            <input type="submit" name="Confirm staff member">

        </form>
    </body>
</html>

