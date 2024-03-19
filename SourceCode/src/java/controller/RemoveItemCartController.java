/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * @author hieul
 */
@WebServlet(name = "RemoveItemCartController", urlPatterns = {"/RemoveItemCart"})
public class RemoveItemCartController extends HttpServlet {

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
            HttpSession session = request.getSession(false); // false for check scope timeout because user's problem
            if (session != null) {                
                //2. Cust takes his/her cart
                Cart cart = (Cart) session.getAttribute("cart");
                if(cart != null) {                    
                    //3. Cust gets all items
                    Map<String, Integer> items = cart.getItems();
                    if(items != null) {                        
                        //4. Cust get all selected removed items
                        String[] selectedItems = request.getParameterValues("chkItem");
                        if(selectedItems != null) {                            
                            //5. System removes item by item from cart
                            for(String item : selectedItems) {
                                cart.removeItemFromCart(item);
                            } // end remove selected items.
                            session.setAttribute("cart", cart);
                        } // end selectedItems has existed
                    }
                } // end cart has existed
            } // end session has existed
        }
        finally {
            //6. System refresh cart 
            /// call View Your Cart again by URL rewriting
//            String urlRewriting = "DispatchController"
//                    + "?btAction=" + "View Your Cart";                                             
         //   response.sendRedirect();
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
