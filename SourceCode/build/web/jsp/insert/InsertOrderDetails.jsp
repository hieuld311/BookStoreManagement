<%-- 
    Document   : InsertOrderDetails
    Created on : Oct 31, 2023, 12:36:48 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rsPrd=(ResultSet)request.getAttribute("rsPrd"); 
        %>
        <form action="OrderDetailsURL" method="post">
            <input type="hidden" name="go" value="insertOrderDetails">
            <h2>Insert OrderDetails</h2>           
            <!--<p>ID <input type="text" name="id" value="" /></p>  -->
            <p>SKU
                <select name="sku">
                    <%while(rsPrd.next()){%>
                        <option value="<%=rsPrd.getString(1)%>"><%=rsPrd.getString(2)%></option>
                    <%}%>
                </select>
            </p>
            <p>OrderID <input type="text" name="orderId" value="" /></p>
            <p>Price <input type="text" name="price" value="" /></p>
            <p>Quantity <input type="text" name="quantiy" value="" /></p>
            <p>Total <input type="text" name="total" value="" /></p>
            <p>Status <input type="text" name="status" value="" /></p>
            

            <p><input type="submit" name="submit" value="Add" /></p>
            <p><input type="reset" value="clear" /></p>
        </form>
    </body>
</html>
