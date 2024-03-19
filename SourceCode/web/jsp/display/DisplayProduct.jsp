<%-- 
    Document   : DisplayProduct
    Created on : Oct 30, 2023, 11:16:59 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Product" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String pageProduct=(String)request.getAttribute("pageProduct");%>
        <title><%=pageProduct%></title>
    </head>
    <body>
        <div> 
            <form action="ProductURL" method="get">
                <input type="hidden" name="go" value="searchSKU">
                <input type="text" name="sku">                  
                <input type="submit" value="Search">
            </form>
        </div>
        <%
            Vector<Product> vector =(Vector<Product>)request.getAttribute("data");
            String tableProduct=(String)request.getAttribute("tableProduct");    
            String message=(String)request.getAttribute("message");      
        %>
        <p style="color: blue"><%=message%></p>
        <p><a href="ProductURL?go=insertProduct"> Insert Product</a></p>
        <table border=1>
            <caption><%=tableProduct%></caption>
            <tr>
                <th>SKU</th>
                <th>Name</th>
                <th>Type</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Price</th>   
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for (Product Product : vector){%>
            <tr>
                <td><%=Product.getSku()%></td>
                <td><%=Product.getName()%></td>
                <td><%=Product.getType() %></td>                
                <td><%=Product.getDescription() %></td>                
                <td><%=Product.getQuantity()%></td>
                <td><%=Product.getPrice()%></td> 
                <td><a href="ProductURL?go=updateProduct&sku=<%=Product.getSku()%>">update</a></td>
                <td><a href="ProductURL?go=deleteProduct&sku=<%=Product.getSku()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
        <p><a href="HomeURL" style="font-weight: bold;">Back to main page</a></p>
    </body>
</html>
