<%-- 
    Document   : DisplayOrderDetails
    Created on : Oct 31, 2023, 12:36:38 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Hashtable" %>
<%@page import="java.util.Vector" %>
<%@page import="entity.OrderDetails" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String pageOrderDetails=(String)request.getAttribute("pageOrderDetails");%>
        <title><%=pageOrderDetails%></title>
    </head>
    <body>
        <div> 
            <form action="OrderDetailsURL" method="get">
                <input type="hidden" name="go" value="searchID">
                <input type="text" name="id">                  
                <input type="submit" value="Search">
            </form>
        </div>

        <%
            Vector<OrderDetails> vector =(Vector<OrderDetails>)request.getAttribute("data");
            Hashtable<String, String> prdMap = (Hashtable<String, String>)request.getAttribute("prdMap");
            String tableOrderDetails=(String)request.getAttribute("tableOrderDetails");    
            String message=(String)request.getAttribute("message");      
        %>
        <p style="color: blue"><%=message%></p>
        <p><a href="OrderDetailsURL?go=insertOrderDetails"> Insert OrderDetails</a></p>
        <table border=1>
            <caption><%=tableOrderDetails%></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>OrderID</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>     
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (OrderDetails orderDetails : vector){ 
                String bookName = prdMap.get(orderDetails.getSku());
            %>
            <tr>
                <td><%=orderDetails.getId()%></td>
                <td><%=bookName %></td>
                <td><%=orderDetails.getOrderId() %></td>                
                <td><%=orderDetails.getPrice() %></td>                
                <td><%=orderDetails.getQuantiy()%></td>
                <td><%=orderDetails.getTotal()%></td>    
                <td><a href="OrderDetailsURL?go=updateOrderDetails&sku=<%=orderDetails.getSku() %>&odid=<%=orderDetails.getOrderId()%>">update</a></td>
                <td><a href="OrderDetailsURL?go=deleteOrderDetails&sku=<%=orderDetails.getSku() %>&odid=<%=orderDetails.getOrderId()%>">delete</a></td>                
            </tr>
            <%}%>
        </table>
        <p><a href="HomeURL" style="font-weight: bold;">Back to main page</a></p>

    </body>
</html>
