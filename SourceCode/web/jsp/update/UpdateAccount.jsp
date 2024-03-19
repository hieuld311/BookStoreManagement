<%-- 
    Document   : UpdateAccount
    Created on : Oct 31, 2023, 5:28:52 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Account" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Account</title>
    </head>
    <body>
        <%
        Vector<Account> vector =(Vector<Account>)request.getAttribute("data");
        Account account = vector.get(0);
        %>
        <form action="AccountURL" method="post">
            <input type="hidden" name="go" value="updateAccount">
            <h2>Create Account</h2>
            <p>Username <input type="text" name="username" value="<%=account.getUsername()%>" /></p>
            <p>Password <input type="text" name="password" value="<%=account.getPassword()%>" /></p>
            <p>FullName <input type="text" name="fullname" value="<%=account.getFullName() %>" /></p>        
            <p>Role <input type="number" name="isAdmin" value="<%=account.getIsAdmin()%>" /></p>
            <p><input type="submit" name="submit" value="Update" /></p>
            <p><input type="reset" value="clear" /></p>
        </form>
    </body>
</html>
