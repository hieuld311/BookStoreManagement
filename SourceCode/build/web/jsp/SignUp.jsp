<%-- 
    Document   : SignUp
    Created on : Nov 1, 2023, 2:40:27 PM
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
        <h1>Sign Up</h1>
        <form action="SignUpURL?go=checkSignUp" method="post" >
            <input type="hidden" name="go" value="signup">
            <table>
                <tr>
                    <td>Username:</td>
                <td><input type="text" name="username" value="" required=""></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>   <input type="password" name="password1" value="" required=""></td>
                </tr>
                <tr>
                    <td>Confirm:</td>
                <td><input type="password" name="password2" value="" required=""></td>
                </tr>
                <tr>
                    <td>Full Name:</td>
                <td><input type="text" name="fullname" value="" required=""></td>
                </tr>
            </table>      
            <P> <input type="submit" name="create" value="Create Account" />
                <input type="reset" value="clear" />
        </form>
        <p style="color: blue"><%=message%></p>
        Already having an account? <a href="LoginURL?go=loginForm">Login</a>
    </body>
</html>
