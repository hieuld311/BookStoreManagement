/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import entity.Orders;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.OrderDetailsDAO;
import model.OrdersDAO;

/**
 *
 * @author hieul
 */
@WebServlet(name = "OrdersController", urlPatterns = {"/OrdersURL"})
public class OrdersController extends HttpServlet {

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
            OrdersDAO dao = new OrdersDAO();
            String service = request.getParameter("go");
            if (service == null) {
                service = "listAllOrders";
            }
            String message = request.getParameter("message");
            if (message == null) {
                message = "";
            }
            if (service.equals("listAllOrders")) {
                //call dao
                Vector<Orders> vector = dao.getData("select * from Orders");
                // create/call some parameters           
                String pageOrders = "Orderss";
                String tableOrders = "List of Orderss";
                //set data for view/jsp
                request.setAttribute("message", message);
                request.setAttribute("data", vector);
                request.setAttribute("pageOrders", pageOrders);
                request.setAttribute("tableOrders", tableOrders);
                // select view/jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/display/DisplayOrders.jsp");
                // run
                dispath.forward(request, response);
            }
            if (service.equals("deleteOrders")) {
                String id = request.getParameter("oid");
                int ID = Integer.parseInt(id);
                OrderDetailsDAO orderDetailsDao = new OrderDetailsDAO();
                orderDetailsDao.removeCustId(ID);
                int n = dao.deleteOrders(ID);
                String st = "";
                if (n > 0) {
                    st = "delete success";
                } else {
                    st = "can't delete because...";
                }
                response.sendRedirect("OrdersURL?message=" + st);
            }
            if (service.equals("insertOrders")) {
                String submit = request.getParameter("submit");
                if (submit == null) { //request by <a> --> show form
                    //step1: show insert form
                    //get data                    
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/insert/InsertOrders.jsp");
                    // run
                    dispath.forward(request, response);
                } else { //request by form --> insert data into DB
                    //step 2: insert into DB 
                    //    String orderID = request.getParameter("orderID");
                    String dateBuy = request.getParameter("dateBuy");
                    String total = request.getParameter("total");
                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String username = request.getParameter("username");
                    String status = request.getParameter("status");
                    //convert
                    //    int OrderID = Integer.parseInt(orderID);
                    double Total = Double.parseDouble(total);
                    Orders obj = new Orders(dateBuy, Total, name, phone, address, username, status);
                    int n = dao.insertOrders(obj);
                    String st = "";
                    if (n > 0) {
                        st = "insert success";
                    } else {
                        st = "can't insert because...";
                    }
                    response.sendRedirect("OrdersURL?message=" + st);
                }
            }
            if (service.equals("updateOrders")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String id = request.getParameter("oid");
                    Vector<Orders> vector = dao.getData("select * from Orders where [orderId]='" + id + "'");
                    request.setAttribute("data", vector);
                    RequestDispatcher dispath = request.getRequestDispatcher("/jsp/update/UpdateOrders.jsp");
                    dispath.forward(request, response);
                } else {
                    String orderID = request.getParameter("orderID");
                    String dateBuy = request.getParameter("dateBuy");
                    String total = request.getParameter("total");
                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String username = request.getParameter("username");
                    String status = request.getParameter("status");
                    //convert
                    int OrderID = Integer.parseInt(orderID);
                    double Total = Double.parseDouble(total);
                    Orders obj = new Orders(OrderID, dateBuy, Total, name, phone, address, username, status);
                    int n = dao.updateOrders(obj);
                    String st = "";
                    if (n > 0) {
                        st = "update success";
                    } else {
                        st = "can't update because...";
                    }
                    response.sendRedirect("OrdersURL?message=" + st);
                }
            }
            if (service.equals("searchID")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String id = request.getParameter("id").trim();
                    Vector<Orders> vector = dao.getData("select * from [Orders] where orderId='" + id + "'");
                    if (vector.isEmpty()) {
                        message = "No such item in Orders";
                    }
                    String tableOrders = "List of Orderss";
                    //set data for view/jsp                 
                    request.setAttribute("message", message);
                    request.setAttribute("data", vector);
                    request.setAttribute("tableOrders", tableOrders);
                    // select view/jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/display/DisplayOrders.jsp");
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
