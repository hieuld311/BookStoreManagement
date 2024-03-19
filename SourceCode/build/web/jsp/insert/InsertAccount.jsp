<%-- 
    Document   : InsertAccount
    Created on : Oct 31, 2023, 5:28:35 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <form action="AccountURL" method="post">
            <input type="hidden" name="go" value="insertAccount">
            <h2>Create Account</h2>
            <p>Username <input type="text" name="username" value="" /></p>
            <p>Password <input type="text" name="password" value="" /></p>
            <p>FullName <input type="text" name="fullname" value="" /></p>        
            <p>Role <input type="number" name="isAdmin" value="" /></p>
            <p><input type="submit" name="submit" value="Add" /></p>
            <p><input type="reset" value="clear" /></p>
        </form>
    </body>
</html>
