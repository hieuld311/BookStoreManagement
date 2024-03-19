<%-- 
    Document   : Home
    Created on : Nov 1, 2023, 4:39:31 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="entity.Product" %>
<!DOCTYPE html>
<html>
    <head>    
        <title>My website</title>
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
    <body>
        <%
            Vector<Product> vector =(Vector<Product>)request.getAttribute("data");
            String tableProduct=(String)request.getAttribute("tableProduct");   
            String message=(String)request.getAttribute("message");
        %>
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

        <div class="content">
            <div>
                <ul style="list-style-type: none; padding-left: 10px">
                    <li><a href="HomeURL"><b><u>All Product</u></b></a></li>
                    <li><a href="ProductURL?go=searchType&type=business">Business</a></li>
                    <li><a href="ProductURL?go=searchType&type=mod_cook">Modern Cooking</a></li>
                    <li><a href="ProductURL?go=searchType&type=technology">Technology</a></li>
                    <li><a href="ProductURL?go=searchType&type=psychology">Psychology</a></li>
                    <li><a href="ProductURL?go=searchType&type=trad_cook">Traditional Cooking</a></li>
                </ul>
            </div>
            <div>
                <%if(message != null){%>
                <p style="color: blue"><%=message%></p>
                <%}%>             
                <table border=1>       
                    <caption><%=tableProduct%></caption>
                <tr>
                    <th>SKU</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Quantity</th>                   
                        <%if(session.getAttribute("username")!= null){%>
                    <th>Price</th> 
                        <%}%>
                        <%if(session.getAttribute("username")!= null){%>
                    <th>Add Cart</th> 
                        <%}%>

                </tr>
                <% for (Product product : vector){%>
                <tr>
                    <td><%=product.getSku()%></td>
                    <td><%=product.getName()%></td>              
                    <td><%=product.getDescription() %></td>  
                    <% if(session.getAttribute("username") != null) { %>
                    <td><%=product.getQuantity()%></td>
                    <% } %>
                    <td><%=product.getPrice()%></td> 
                    <% if(session.getAttribute("username") != null) { %>
                    <td><a href="AddToCart?sku=<%=product.getSku()%>">Add Cart</a></td> 
                    <% } %>
                </tr>

                <%}%>
                </table>
            </div>
        </div>
    </body>
</html>

