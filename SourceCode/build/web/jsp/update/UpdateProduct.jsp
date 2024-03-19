<%-- 
    Document   : UpdateProduct
    Created on : Oct 30, 2023, 11:50:40 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        <%
            Vector<Product> vector =(Vector<Product>)request.getAttribute("data");
            Product product = vector.get(0);
        %>        
        <form action="ProductURL" method="post">
            <input type="hidden" name="go" value="updateProduct">
            <h2>Insert Product</h2>
            <p>SKU <input type="text" name="sku" value="<%=product.getSku()%>" /></p>
            <p>Name <input type="text" name="name" value="<%=product.getName()%>" /></p>
            <p>Type <input type="text" name="type" value="<%=product.getType() %>"</p>         
            <p>Description <input type="text" name="description" value="<%=product.getDescription() %>" /></p>
            <p>Quantity <input type="number" name="quantity" value="<%=product.getQuantity()%>" /></p> 
            <p>Price <input type="number" name="price" value="<%=product.getPrice()%>" /></p>         
            
            <p><input type="submit" name="submit" value="Update" /></p>
            <p><input type="reset" value="Clear" /></p>
        </form>
    </body>
</html>
