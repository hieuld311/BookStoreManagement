<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Product" %>
<html>
    <head>
        <title>Check-out Confirm page</title>
        <style>
            .panel {
                display: flex;
                justify-content: space-between;
                padding: 10px;
                border-bottom: 1px solid black;
            }
            .panel-right {
                text-align: right;
                padding-right: 40px;
            }
            .content {
                display: flex;
                justify-content: space-between;
                padding: 10px;
            }
            .content > div {
                width: calc(8%);
                border-right: 1px solid black;
                padding-right: 10px;
            }
            .content > div:last-child {
                width: calc(92% - 20px);
                border-right: none;
                padding-right: 0;
            }

            body {
                margin: 0;
                padding: 10px;
            }
        </style>
    </head>
</head>
<body left-padding: 10px>
    <div class="panel-right">        
        <% String username = (String) session.getAttribute("username"); %>
        <% if(username != null){ %>
        <div>Welcome <%= username %> </div>
        <% } else { %>
        <div>Welcome Guest </div>
        <% } %>           
    </div>


    <div class="panel">
        <div>BOOK</div>
        <div style="display: flex; justify-content: space-between;"> 
            <div style="margin-right: 10px;"><a href="HomeURL">Home</a></div>
            <%if(session.getAttribute("username")!= null){%>
            <div style="margin-right: 10px;"><a href="LogoutURL">Logout</a></div>
            <%}%>
            <%if(session.getAttribute("username")== null){%>
            <div style="margin-right: 10px;"><a href="LoginURL">Login</a></div>
            <%}%>
            <div style="margin-right: 10px;"><a href="SignUpURL">Register</a></div>
            <%if(session.getAttribute("username")!= null){%>
            <div style="margin-right: 10px;"><a href="Cart">View Cart</a></div>
            <%}%>

        </div>
        <div> 
            <form action="ProductURL" method="get">
                <input type="hidden" name="go" value="searchName">
                <input type="text" name="name">                  
                <input type="submit" value="Search">
            </form>
        </div>

    </div>
    <h1>Checkout Confirm</h1>
    <%
    Vector<Product> vector =(Vector<Product>)request.getAttribute("itemsSelected");
    %>

    <form action="ConfirmCheckOut" method="post">
        Full Name*(5-30 characters)<br>
        <input type="text" name="fullname" value="" required>
        <br>
        <br>
        Phone number*(10 digits)<br>
        <input type="text" name="phone" value="" required>
        <br>
        <br>
        Address*<br>
        <input type="text" name="address" value="" required>
        <br>
        <br>
        <input type="submit" value="Checkout">
        <br>
        <br>
        <%
                      // Check if the items in cart are not empty
                      if (vector != null && !vector.isEmpty()) {
        %>
        <table width=50% border=1 >
            <tr>
                <th>No.</th>
                <th>SKU</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
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
                <td><%=product.getSku()%></td>
                <td><%=product.getName()%></td>
                <td><%=product.getQuantity()%></td>
                <td><%=product.getPrice()%></td>
                <td><%= String.format("%.3f", (product.getQuantity() * product.getPrice())) %></td>

            </tr>
            <%
  }
            %>
            <tr class="no-border">
                <td colspan="5" class="right-align no-border">Total</td>
                <td class="no-border"><input type="hidden" name="grandtotal" value="<%= String.format("%.3f", grandTotal) %>"><%= String.format("%.3f", grandTotal) %></td>
            </tr>

        </table>                  
    </form>
    <%
} 
    %>
    <% session.setAttribute("selectedItems", vector); %>

</body>
</html>
