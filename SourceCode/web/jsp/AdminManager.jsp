<%-- 
    Document   : AdminManager
    Created on : Nov 1, 2023, 3:56:58 PM
    Author     : hieul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            }
        </style>
    </head>
    <body>

        <div class="panel-right">        
            <% String username = (String) session.getAttribute("username"); %>
            <% if(username != null){ %>
            <div>Welcome <%= username %> </div>
            <%}%>        
        </div>


        <div class="panel">
            <div>ADMINSTRATOR</div>
            <div style="display: flex; justify-content: space-between;"> 
                <%if(session.getAttribute("username")!= null){%>
                <div style="margin-right: 10px;"><a href="LogoutURL">Logout</a></div>
                <%}%>
                <%if(session.getAttribute("username")== null){%>
                <div style="margin-right: 10px;"><a href="LoginURL">Login</a></div>
                <%}%>            
            </div>

        </div>

        <div class="content">
            <div>
                <ul style="list-style-type: none; padding-left: 10px">
                    <li><a href="AccountURL">Account Manager</a></li>
                    <li><a href="ProductURL">Product Manager</a></li>
                    <li><a href="OrdersURL">Orders Manager</a></li>
                    <li><a href="OrderDetailsURL">OrderDetail Manager</a></li>
                </ul>
            </div>
            <div>
                Workspace of Admin!
            </div>
        </div>
    </body>
</html>
