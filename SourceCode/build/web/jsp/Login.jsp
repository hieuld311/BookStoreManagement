<%-- 
    Document   : Login
    Created on : Oct 19, 2023, 12:55:56 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <%
            String message=(String)request.getAttribute("message");
        %>
        <h1>Login</h1>
        <form action="LoginURL?go=checkLogin" method="post">
            <input type="hidden" name="go" value="login">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" value=""></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" value=""></td>
                </tr>
            </table>       
            <P> <input type="submit" name="login" value="login" />
                <input type="reset" value="clear" />
        </form>
        <p style="color: blue"><%=message%></p>
        <br>
        <!--Need a new account? <a href="register.html">Register here</a>-->
        Need a new account? <a href="SignUpURL">Register here</a>
        <br>
    </body>
</html>
