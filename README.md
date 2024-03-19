<hr>Author: Hieuld

#javawebapplicationdevelopment

Table of Contents:
- Resources
- Description
- Technology

<hr>

Resources:
|#|Resource|Description|
|---|---|---|
|1|[Book Store Management](https://github.com/hieuld311/BookStoreManagement.git)|The documentation about my web app for Book Store’s context.|
|2|[Source Code](https://github.com/hieuld311/BookStoreManagement/tree/main/SourceCode) |The folder contains the source code of the project.|

Description
- A bookstore web application for buying book.
- User are divided into 2 main role: admin and customers/
- Both roles are authenticated through the Login feature;
- In addition, customers are also supported the Register feature.
-  As an admin, we can do some operations to manage system like search, delete or update.
- - As a customer, we can do some operations to order books like:
    - View books in the store.
    - Cart’s actions:
        - Add books to the cart.
        - View books in the cart.
        - Remove books from the cart.
    - Checkout:
        - View the details of the prepared order.
        - Fill in the delivery information.
        - Receive the corresponding bill about the order.
- This web is tested for screens and features on desktops; not yet for mobile.

Technology:
1. Frontend
- JSP - Java Server Pages
2. Backend
- Servlets as Controllers
- JDBC - Java Database Connectivity
3. Database
- Microsoft SQL Server.
4. Tools
 - Netbeans 8.2
 - Java JDK 1.8
 - Apache Tomcat 10.0.27
