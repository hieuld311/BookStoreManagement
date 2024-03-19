/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetailsDAO;

/**
 *
 * @author hieul
 */
@WebServlet(name = "OrderDetailsController", urlPatterns = {"/OrderDetailsURL"})
public class OrderDetailsController extends HttpServlet {

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
            OrderDetailsDAO dao = new OrderDetailsDAO();
            String service = request.getParameter("go");
            if (service == null) {
                service = "listAllOrderDetails";
            }
            String message = request.getParameter("message");
            if (message == null) {
                message = "";
            }
            if (service.equals("listAllOrderDetails")) {
                //call dao
                Vector<OrderDetails> vector = dao.getData("select * from OrderDetails");

                ResultSet rsPrd
                        = dao.getDataDB("select sku, name from Product");
                //    request.setAttribute("rsPrd", rsPrd);
                Hashtable<String, String> prdMap = new Hashtable<>();
                try {
                    while (rsPrd.next()) {
                        prdMap.put(rsPrd.getString(1), rsPrd.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
                // create/call some parameters           
                String pageOrderDetails = "OrderDetails";
                String tableOrderDetails = "List of OrderDetails";
                //set data for view/jsp
                request.setAttribute("message", message);
                request.setAttribute("data", vector);
                request.setAttribute("prdMap", prdMap);
                request.setAttribute("pageOrderDetails", pageOrderDetails);
                request.setAttribute("tableOrderDetails", tableOrderDetails);
                // select view/jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/display/DisplayOrderDetails.jsp");
                // run
                dispath.forward(request, response);
            }
            if (service.equals("deleteOrderDetails")) {
                String sku = request.getParameter("sku");
                String id = request.getParameter("odid");
                int ID = Integer.parseInt(id);
                int n = dao.deleteOrderDetails(sku, ID);
                String st = "";
                if (n > 0) {
                    st = "delete success";
                } else {
                    st = "can't delete because...";
                }
                response.sendRedirect("OrderDetailsURL?message=" + st);
            }
            if (service.equals("insertOrderDetails")) {
                String submit = request.getParameter("submit");
                if (submit == null) { //request by <a> --> show form
                    //step1: show insert form
                    //get data                 
                    ResultSet rsPrd
                            = dao.getDataDB("select sku, name from Product");
                    request.setAttribute("rsPrd", rsPrd);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/insert/InsertOrderDetails.jsp");
                    // run
                    dispath.forward(request, response);
                } else { //request by form --> insert data into DB
                    //step 2: insert into DB 
                    //    String orderID = request.getParameter("orderID");
                    String sku = request.getParameter("sku");
                    String orderId = request.getParameter("orderId");
                    String price = request.getParameter("price");
                    String quantiy = request.getParameter("quantiy");
                    String total = request.getParameter("total");
                    //convert
                    int OrderId = Integer.parseInt(orderId);
                    double Price = Double.parseDouble(price);
                    int Quantiy = Integer.parseInt(quantiy);
                    double Total = Double.parseDouble(total);
                    OrderDetails obj = new OrderDetails(sku, OrderId, Price, Quantiy, Total);
                    int n = dao.insertOrderDetails(obj);
                    String st = "";
                    if (n > 0) {
                        st = "insert success";
                    } else {
                        st = "can't insert because...";
                    }
                    response.sendRedirect("OrderDetailsURL?message=" + st);
                }
            }
            if (service.equals("updateOrderDetails")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String sku = request.getParameter("sku");
                    String id = request.getParameter("odid");
                    int ID = Integer.parseInt(id);
                    Vector<OrderDetails> vector = dao.getData("select * from OrderDetails where sku ='" + sku + "' and order_id = '" + ID + "'");
                    ResultSet rsPrd
                            = dao.getDataDB("select sku, name from Product");

//                    Hashtable<String, String> prdMap = new Hashtable<>();
//                    try {
//                        while (rsPrd.next()) {
//                            prdMap.put(rsPrd.getString(1), rsPrd.getString(2));
//                        }
//                    } catch (SQLException ex) {
//                        Logger.getLogger(OrderDetails.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    request.setAttribute("data", vector);
                    request.setAttribute("rsPrd", rsPrd);
                    //     request.setAttribute("prdMap", prdMap);
                    RequestDispatcher dispath = request.getRequestDispatcher("/jsp/update/UpdateOrderDetails.jsp");
                    dispath.forward(request, response);
                } else {
                    String sku = request.getParameter("sku");
                    String orderId = request.getParameter("orderId");
                    String price = request.getParameter("price");
                    String quantiy = request.getParameter("quantiy");
                    String total = request.getParameter("total");
                    //convert
                    int OrderId = Integer.parseInt(orderId);
                    double Price = Double.parseDouble(price);
                    int Quantiy = Integer.parseInt(quantiy);
                    double Total = Double.parseDouble(total);
                    OrderDetails obj = new OrderDetails(sku, OrderId, Price, Quantiy, Total);
                    int n = dao.updateOrderDetails(obj);
                    String st = "";
                    if (n > 0) {
                        st = "update success";
                    } else {
                        st = "can't update because...";
                    }
                    response.sendRedirect("OrderDetailsURL?message=" + st);
                }
            }
            if (service.equals("searchID")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String id = request.getParameter("id").trim();
                    Vector<OrderDetails> vector = dao.getData("select * from [OrderDetails] where order_id='" + id + "'");
                    if (vector.isEmpty()) {
                        message = "No such item in OrderDetails";
                    }
                    ResultSet rsPrd
                        = dao.getDataDB("select sku, name from Product");
                //    request.setAttribute("rsPrd", rsPrd);
                Hashtable<String, String> prdMap = new Hashtable<>();
                try {
                    while (rsPrd.next()) {
                        prdMap.put(rsPrd.getString(1), rsPrd.getString(2));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrderDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
                    String tableOrderDetails = "List of OrderDetailss";
                    //set data for view/jsp   
                    
                    request.setAttribute("message", message);
                    request.setAttribute("data", vector);
                    request.setAttribute("prdMap", prdMap);
                    request.setAttribute("tableOrderDetails", tableOrderDetails);
                    // select view/jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/display/DisplayOrderDetails.jsp");
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
