<%-- 
    Document   : DisplayOrders
    Created on : Oct 31, 2023, 12:08:34 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Orders" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String pageOrders=(String)request.getAttribute("pageOrders");%>
        <title><%=pageOrders%></title>
    </head>
    <body>
        <div> 
            <form action="OrdersURL" method="get">
                <input type="hidden" name="go" value="searchID">
                <input type="text" name="id">                  
                <input type="submit" value="Search">
            </form>
        </div>
        <%
            Vector<Orders> vector =(Vector<Orders>)request.getAttribute("data");
            String tableOrders=(String)request.getAttribute("tableOrders");    
            String message=(String)request.getAttribute("message");      
        %>
        <p style="color: blue"><%=message%></p>
        <p><a href="OrdersURL?go=insertOrders"> Insert Orders</a></p>
        <table border=1>
            <caption><%=tableOrders%></caption>
            <tr>
                <th>OrderID</th>
                <th>DateBuy</th>
                <th>Total</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Address</th>    
                <th>Username</th>  
                <th>Status</th>  
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (Orders Orders : vector){ 
           //     String status = Orders.getStatus().trim();
            %>
            <tr>
                <td><%=Orders.getOrderID()%></td>
                <td><%=Orders.getDateBuy()%></td>
                <td><%=Orders.getTotal() %></td>                
                <td><%=Orders.getName() %></td>                
                <td><%=Orders.getAddress()%></td>
                <td><%=Orders.getPhone()%></td>  
                <td><%=Orders.getUsername()%></td>   
                <td><%=Orders.getStatus()%></td> 
                <% if (!Orders.getStatus().equals("Complete")){ %>                
                <td><a href="OrdersURL?go=updateOrders&oid=<%=Orders.getOrderID()%>">update</a></td>
                <td><a href="OrdersURL?go=deleteOrders&oid=<%=Orders.getOrderID()%>">delete</a></td>  
                <%}else{%>
                <td>Completed</td>   
                <td><a href="OrdersURL?go=deleteOrders&oid=<%=Orders.getOrderID()%>">delete</a></td> 
                <%}%>
            </tr>
            <%}%>
        </table>
        <p><a href="HomeURL" style="font-weight: bold;">Back to main page</a></p>

    </body>
</html>
