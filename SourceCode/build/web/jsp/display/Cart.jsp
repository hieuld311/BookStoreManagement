<%-- 
    Document   : Cart
    Created on : Nov 2, 2023, 2:48:11 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Cart" %>
<%@page import="entity.Product" %>
<%@page import="model.ProductDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<%
    // Assuming cart and items are available in the session
    Cart cart = (Cart) session.getAttribute("cart");
    Map<String, Integer> items = cart.getItems();
%>
<h1>Your Cart</h1>
<table>
    <tr>
        <th>SKU</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total</th>
        <th></th>
    </tr>
<%
    for (Map.Entry<String, Integer> entry : items.entrySet()) {
        String sku = entry.getKey();
        int quantity = entry.getValue();

        // Assuming you have a method to get the product by SKU
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProduct(sku);
        double price = product.getPrice();
        double total = price * quantity;
%>
    <tr>
        <td><%=sku%></td>
        <td><%=quantity%></td>
        <td><%=price%></td>
        <td><%=total%></td>
        <td><a href="CartURL?cart&sku=<%=sku%>&quantity=0">Remove Item</a></td>
    </tr>
<%
    }
%>
</table>



    </body>
</html>

<style>
    .no-border {
      border: none;
    }
    .right-align {
      text-align: right;
    }
  </style>
  
  <table border="1">
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Quantity</th>
      <th>Price</th>
      <th>Total</th>
      <th></th>
    </tr>
    <tr>
      <td>1</td>
      <td>Dell</td>
      <td><input type="number" name="quantity" /></td>
      <td>300</td>
      <td>300</td>
      <td><a href="">Remove</a></td>
    </tr>
    <tr class="no-border">
      <td><a href="">Update</a></td>
      <td colspan="2" class="no-border"></td>
      <td class="right-align no-border">Total:</td>
      <td class="no-border"><span id="grand_total"></span></td>
      <td class="no-border">Remove all</td>
    </tr>
  </table>
