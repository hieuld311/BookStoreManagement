<%-- 
    Document   : DisplayAccount
    Created on : Oct 31, 2023, 5:28:16 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Account" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String pageAccount=(String)request.getAttribute("pageAccount");%>
        <title><%=pageAccount%></title>
    </head>
    <body>
        <div> 
            <form action="AccountURL" method="get">
                <input type="hidden" name="go" value="searchUser">
                <input type="text" name="username">                  
                <input type="submit" value="Search">
            </form>
        </div>
        <%
           // DAOTitle dao = new DAOTitle();                  
           Vector<Account> vector =(Vector<Account>)request.getAttribute("data"); //dao.getData("select * from Accounts");
            String tableAccount=(String)request.getAttribute("tableAccount");    
            String message=(String)request.getAttribute("message");      
        %>
    <p style="color: blue"><%=message%></p>
        <p><a href="AccountURL?go=insertAccount"> Create Account</a></p>
        <table border=1>
            <caption><%=tableAccount%></caption>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>FullName</th>
                <th>Role</th>                
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (Account Account : vector){ 
            %>
            <tr>
                <td><%=Account.getUsername()%></td>
                <td><%=Account.getPassword()%></td>
                <td><%=Account.getFullName() %></td>      
                <td><%=Account.getIsAdmin()%></td>
                <td><a href="AccountURL?go=updateAccount&id=<%=Account.getUsername()%>">update</a></td>
                <td><a href="AccountURL?go=deleteAccount&id=<%=Account.getUsername()%>">delete</a></td>               
            </tr>
            <%}%>
        </table>
        <p><a href="HomeURL" style="font-weight: bold;">Back to main page</a></p>
    </body>
</html>
