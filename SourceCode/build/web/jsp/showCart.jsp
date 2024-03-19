<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Product" %>
<%@page import="model.ProductDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <style>
            .no-border {
                border: none;
            }
            .right-align {
                text-align: right;
            }
            .center-align{
                text-align: center;
            }
        </style>

        <%
          Vector<Product> vector =(Vector<Product>)request.getAttribute("items_in_cart");
        %>

        <h1>Shopping Cart Details</h1>               
        <%
          // Check if the items in cart are not empty
          if (vector != null && !vector.isEmpty()) {
        %>
        <h3><a href="HomeURL">Continue Shopping</a></h3>
        <form action="UpdateQuantityInCart" method="post">
            <table width=50% border=1 >
                <tr>
                    <th>No.</th>
                    <th>SKU</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th></th>
                </tr>
                <%
                  int counter = 0;
                  double grandTotal=0;
                  for (Product product : vector) {
                    counter++;
                    grandTotal += (product.getQuantity() * product.getPrice());
                %>
                <tr>
                    <td><%=counter %>.</td>
                    <td><input type="hidden" name="sku" value="<%=product.getSku()%>"><%=product.getSku()%></td>
                    <td><%=product.getName()%></td>
                    <td class="center-align"><input type="number" name="quantity" value="<%=product.getQuantity()%>" /></td>
                    <td><%=product.getPrice()%></td>
                    <td><%= String.format("%.3f", (product.getQuantity() * product.getPrice())) %></td>
                    <td><a href="RemoveItemFromCart?sku=<%=product.getSku()%>">Remove</a></td>
                </tr>
                <%
                  }
                %>
                <tr class="no-border">
                    <td class ="center-align no-border"><input type="submit" value="Update All"></td>
                    <td colspan="3" class="no-border"></td>
                    <td class="right-align no-border">Total:</td>
                    <td class="no-border"><span><%= String.format("%.3f", grandTotal) %></span></td>
                    <td class="no-border"><a href="RemoveItemFromCart?sku=all">Remove all</a></td>
                </tr>
            </table>                  
        </form>
        <br>
        
        
        <form action="CheckOut" method="post">
            <%
                  for (Product product : vector) {                                
            %>
            <td><input type="hidden" name="sku" value="<%=product.getSku()%>"></td>
                <%}%>
            <input type="submit" value="Check-out">
        </form>
        <%
          } else {
        %>
        <!--show message-->
        <h2>No item exist in your cart</h2>
        <a href="HomeURL" style="color: blue">Add more items to Your Cart right now</a>
        <%
            }    
        %>
        
        
    </body>
</html>
