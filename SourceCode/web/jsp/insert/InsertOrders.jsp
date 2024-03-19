<%-- 
    Document   : InsertOrders
    Created on : Oct 31, 2023, 12:08:42 AM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Order</title>
    </head>
    <body>
        <form action="OrdersURL" method="post">
            <input type="hidden" name="go" value="insertOrders">
            <h2>Insert Product</h2>
            <!--<p>OrderID <input type="text" name="orderID" value="" /></p>  -->  
            <p>DateBuy <input type="date" name="dateBuy" value="" /></p>
            <p>Total <input type="text" name="total" value="" /></p>         
            <p>Name <input type="text" name="name" value="" /></p>
            <p>Phone <input type="text" name="phone" value="" /></p> 
            <p>Address <input type="text" name="address" value="" /></p>
            <p>Username <input type="text" name="username" value="" /></p>  
            <p>Status <input type="text" name="status" value="" /></p>  

            <p><input type="submit" name="submit" value="Add" /></p>
            <p><input type="reset" value="Clear" /></p>
        </form>
    </body>
</html>
