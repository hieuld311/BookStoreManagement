<%-- 
    Document   : UpdateOrders
    Created on : Oct 31, 2023, 12:08:49 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Orders" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Vector<Orders> vector =(Vector<Orders>)request.getAttribute("data");          
            Orders ord = vector.get(0);    
        %>
        <form action="OrdersURL" method="post">
            <input type="hidden" name="go" value="updateOrders">
            <h2>Insert Product</h2>
            <p>OrderID <input type="text" name="orderID" value="<%=ord.getOrderID()%>" readonly/></p>
            <p>DateBuy <input type="text" name="dateBuy" value="<%=ord.getDateBuy()%>" /></p>
            <p>Total <input type="text" name="total" value="<%=ord.getTotal() %>" /></p>         
            <p>Name <input type="text" name="name" value="<%=ord.getName() %>" /></p>
            <p>Phone <input type="text" name="phone" value="<%=ord.getAddress()%>" /></p> 
            <p>Address <input type="text" name="address" value="<%=ord.getPhone()%>" /></p>
            <p>Username <input type="text" name="username" value="<%=ord.getUsername()%>" readonly/></p>  
            <p>Status <input type="text" name="status" value="<%=ord.getStatus()%>"/></p>  

            <p><input type="submit" name="submit" value="Update" /></p>
            <p><input type="reset" value="Clear" /></p>
        </form>
    </body>
</html>
