<%-- 
    Document   : InsertProduct
    Created on : Oct 30, 2023, 11:47:18 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Product</title>
    </head>
    <body>
        <form action="ProductURL" method="post">
            <input type="hidden" name="go" value="insertProduct">
            <h2>Insert Product</h2>
            <p>SKU <input type="text" name="sku" value="" /></p>
            <p>Name <input type="text" name="name" value="" /></p>
            <p>Type <input type="text" name="type" value="" /></p>         
            <p>Description <input type="text" name="description" value="" /></p>
            <p>Quantity <input type="number" name="quantity" value="" /></p> 
            <p>Price <input type="number" name="price" value="" /></p>                  
            
            <p><input type="submit" name="submit" value="Add" /></p>
            <p><input type="reset" value="Clear" /></p>
        </form>
    </body>
</html>
