/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.Vector;
import model.AccountDAO;
import model.OrderDetailsDAO;
import model.ProductDAO;

/**
 *
 * @author hieul
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeURL"})
public class HomeController extends HttpServlet {

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
            AccountDAO accountDao = new AccountDAO();
            String service = request.getParameter("go");
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
//            if (username == null || username.isEmpty()){
//                username = "guest";
//                session.setAttribute("username", username);
//                service = "homePage";
//            }            
            int n = accountDao.isAuthorized(username);
            if (n == 1) {
                service = "admin";
            } else {
                service = "homePage";
            }
            if (service.equals("homePage")) {
                String message = (String) request.getAttribute("message");

                ProductDAO productDao = new ProductDAO();
                OrderDetailsDAO orderDetailsDao = new OrderDetailsDAO();
                //call dao
                Vector<Product> vector = productDao.getData("select * from Product where status = 1");
                for (Product product : vector) {
                    int orderedQty = orderDetailsDao.getOrderedQuantityOf(product.getSku());
                    int restQty = product.getQuantity() - orderedQty;
                    product.setQuantity(restQty);
                }
                if (session != null){
                    Cart cart = (Cart) session.getAttribute("cart");
                    if (cart != null){
                        cart.refresh(); //re-fix for real qty in DB
                        Map<String, Integer> items = cart.getItems();
                        if (items != null){
                            for (Product product : vector){
                                String sku = product.getSku();
                                if (items.containsKey(sku)){
                                    int qtyInCart = items.get(sku);
                                    
                                    int restQuantity = product.getQuantity() - qtyInCart;
                                    if (restQuantity < 0){
                                        restQuantity = 0;
                                    }
                                    product.setQuantity(restQuantity);
                                }
                            }
                        }
                    }
                }
                
                
                // create/call some parameters           
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

            if (service.equals("admin")) {

                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/AdminManager.jsp");
                // run
                dispath.forward(request, response);

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
