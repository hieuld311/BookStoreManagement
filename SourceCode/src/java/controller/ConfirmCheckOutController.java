/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import entity.Orders;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import model.OrderDetailsDAO;
import model.OrdersDAO;

/**
 *
 * @author hieul
 */
@WebServlet(name = "ConfirmCheckOutController", urlPatterns = {"/ConfirmCheckOut"})
public class ConfirmCheckOutController extends HttpServlet {

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
            HttpSession session = request.getSession();
            Vector<Product> vector = (Vector<Product>) session.getAttribute("selectedItems");

            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(currentDate);

            String username = (String) session.getAttribute("username");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String grandTotal = request.getParameter("grandtotal");
            double Total = Double.parseDouble(grandTotal);
            
            OrderDetailsDAO orderdetailsDao = new OrderDetailsDAO();
            OrdersDAO orderDao = new OrdersDAO();
            
            //add new orders in db
            Orders bill = new Orders(date, Total, fullname, phone, address, username, "waiting");
            orderDao.insertOrders(bill);
            Orders billcheckOut = orderDao.getNewestID();
            
            //add new orderdetails in db        
            for(Product product : vector){
                String sku = product.getSku();
                int orderID = billcheckOut.getOrderID();
                double price = product.getPrice();
                int qty = product.getQuantity();
                double total = price * qty;
                OrderDetails obj = new OrderDetails(sku, orderID, price, qty, total);
                orderdetailsDao.insertOrderDetails(obj);
            }           
            request.setAttribute("itemsConfirmed", vector);
            request.setAttribute("billcheckOut", billcheckOut);
            RequestDispatcher dispath = request.getRequestDispatcher("/jsp/CheckOutSuccess.jsp");
            dispath.forward(request, response);

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
