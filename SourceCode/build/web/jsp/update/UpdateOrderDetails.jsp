<%-- 
    Document   : UpdateOrderDetails
    Created on : Oct 31, 2023, 12:36:59 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="entity.OrderDetails" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update OrderDetails</title>
    </head>
    <body>
        <%
        ResultSet rsPrd=(ResultSet)request.getAttribute("rsPrd"); 
        Vector<OrderDetails> vector =(Vector<OrderDetails>)request.getAttribute("data");
        OrderDetails od = vector.get(0);
        %>
        <form action="OrderDetailsURL" method="post">
            <input type="hidden" name="go" value="updateOrderDetails">
            <h2>Insert OrderDetails</h2>
            <p>ID <input type="text" name="id" value="<%=od.getId()%>" readonly/></p>
            <p>SKU
                <select name="sku">
                    <%while(rsPrd.next()){%>
                        <option value="<%=rsPrd.getString(1)%>"<%=rsPrd.getString(1).equals(od.getSku())?"selected":""%>><%=rsPrd.getString(2)%></option>
                    <%}%>
                </select>
            </p>
            <p>OrderID <input type="text" name="orderId" value="<%=od.getOrderId() %>" /></p>
            <p>Price <input type="text" name="price" value="<%=od.getPrice() %>" /></p>
            <p>Quantity <input type="text" name="quantiy" value="<%=od.getQuantiy()%>" /></p>
            <p>Total <input type="text" name="total" value="<%=od.getTotal()%>" /></p>
            <p>Status <input type="text" name="status" value="<%=od.getStatus()%>" /></p>
            

            <p><input type="submit" name="submit" value="Update" /></p>
            <p><input type="reset" value="clear" /></p>
        </form>
    </body>
</html>
