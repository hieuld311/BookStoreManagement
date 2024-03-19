/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.ProductDAO;

/**
 *
 * @author hieul
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ProductDAO dao = new ProductDAO();
            String service = request.getParameter("go");
            if (service == null) {
                service = "listAllProduct";
            }
            String message = request.getParameter("message");
            if (message == null) {
                message = "";
            }
            if (service.equals("listAllProduct")) {
                //call dao
                Vector<Product> vector = dao.getData("select * from Product");
                // create/call some parameters           
                String pageProduct = "Products";
                String tableProduct = "List of Products";
                //set data for view/jsp
                request.setAttribute("message", message);
                request.setAttribute("data", vector);
                request.setAttribute("pageProduct", pageProduct);
                request.setAttribute("tableProduct", tableProduct);
                // select view/jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/display/DisplayProduct.jsp");
                // run
                dispath.forward(request, response);
            }
            if (service.equals("deleteProduct")) {
                String sku = request.getParameter("sku");
                int n = dao.deleteProduct(sku);
                String st = "";
                if (n > 0) {
                    st = "delete success";
                } else {
                    st = "can't delete because...";
                }
                response.sendRedirect("ProductURL?message=" + st);
            }
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) { //request by <a> --> show form
                    //step1: show insert form
                    //get data                    
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/insert/InsertProduct.jsp");
                    // run
                    dispath.forward(request, response);
                } else { //request by form --> insert data into DB
                    //step 2: insert into DB 
                    String sku = request.getParameter("sku");
                    String name = request.getParameter("name");
                    String type = request.getParameter("type");
                    String description = request.getParameter("description");
                    String quantity = request.getParameter("quantity");
                    String price = request.getParameter("price");
                    String status = request.getParameter("status");
                    //convert
                    int Quantity = Integer.parseInt(quantity);
                    double Price = Double.parseDouble(price);
                    Product obj = new Product(sku, name, type, description, Quantity, Price);
                    int n = dao.insertProduct(obj);
                    String st = "";
                    if (n > 0) {
                        st = "insert success";
                    } else {
                        st = "can't insert because...";
                    }
                    response.sendRedirect("ProductURL?message=" + st);
                }
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String sku = request.getParameter("sku");
                    Vector<Product> vector = dao.getData("select * from Product where sku='" + sku + "'");
                    request.setAttribute("data", vector);
                    RequestDispatcher dispath = request.getRequestDispatcher("/jsp/update/UpdateProduct.jsp");
                    dispath.forward(request, response);
                } else {
                    String sku = request.getParameter("sku");
                    String name = request.getParameter("name");
                    String type = request.getParameter("type");
                    String description = request.getParameter("description");
                    String quantity = request.getParameter("quantity");
                    String price = request.getParameter("price");
                    //convert
                    int Quantity = Integer.parseInt(quantity);
                    double Price = Double.parseDouble(price);
                    Product obj = new Product(sku, name, type, description, Quantity, Price);
                    int n = dao.updateProduct(obj);
                    String st = "";
                    if (n > 0) {
                        st = "update success";
                    } else {
                        st = "can't update because...";
                    }
                    response.sendRedirect("ProductURL?message=" + st);
                }
            }
            if (service.equals("searchType")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String type = request.getParameter("type");
                    Vector<Product> vector = dao.getData("select * from Product where type='" + type + "'");
                    String tableProduct = "List of Products";
                    //set data for view/jsp
                    request.setAttribute("message", message);
                    request.setAttribute("data", vector);
                    request.setAttribute("tableProduct", tableProduct);
                    // select view/jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/Home.jsp");
                    // run
                    dispath.forward(request, response);
                }
            }
            if (service.equals("searchName")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String name = request.getParameter("name").trim();
                    Vector<Product> vector = dao.getData("select * from Product where name='" + name + "'");
                    if (vector.isEmpty()) {
                        message = "No such item in Product";
                    }
                    String tableProduct = "List of Products";
                    //set data for view/jsp                 
                    request.setAttribute("message", message);
                    request.setAttribute("data", vector);
                    request.setAttribute("tableProduct", tableProduct);
                    // select view/jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/Home.jsp");
                    // run
                    dispath.forward(request, response);
                }
            }
            if (service.equals("searchSKU")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String sku = request.getParameter("sku").trim();
                    Vector<Product> vector = dao.getData("select * from Product where sku='" + sku + "'");
                    if (vector.isEmpty()) {
                        message = "No such item in Product";
                    }
                    String tableProduct = "List of Products";
                    //set data for view/jsp                 
                    request.setAttribute("message", message);
                    request.setAttribute("data", vector);
                    request.setAttribute("tableProduct", tableProduct);
                    // select view/jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/display/DisplayProduct.jsp");
                    // run
                    dispath.forward(request, response);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
