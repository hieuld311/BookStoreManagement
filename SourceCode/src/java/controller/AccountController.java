/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Account;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.AccountDAO;

/**
 *
 * @author hieul
 */
@WebServlet(name = "AccountController", urlPatterns = {"/AccountURL"})
public class AccountController extends HttpServlet {

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
            AccountDAO dao = new AccountDAO();
            String service = request.getParameter("go");
            if (service == null) {
                service = "listAllAccount";
            }
            String message = request.getParameter("message");
            if (message == null) {
                message = "";
            }
            if (service.equals("listAllAccount")) {
                //call dao
                Vector<Account> vector = dao.getData("select * from Account");
                // create/call some parameters           
                String pageAccount = "Accounts";
                String tableAccount = "List of Accounts";
                //set data for view/jsp
                request.setAttribute("message", message);
                request.setAttribute("data", vector);
                request.setAttribute("pageAccount", pageAccount);
                request.setAttribute("tableAccount", tableAccount);
                // select view/jsp
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/display/DisplayAccount.jsp");
                // run
                dispath.forward(request, response);
            }
            if (service.equals("deleteAccount")) {
                String username = request.getParameter("id");
                int n = dao.deleteAccount(username);
                String st = "";
                if (n > 0) {
                    st = "delete success";
                } else {
                    st = "can't delete because...";
                }
                response.sendRedirect("AccountURL?message=" + st);
            }
            if (service.equals("insertAccount")) {
                String submit = request.getParameter("submit");
                if (submit == null) { //request by <a> --> show form
                    //step1: show insert form
                    //get data                    
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/insert/InsertAccount.jsp");
                    // run
                    dispath.forward(request, response);
                } else { //request by form --> insert data into DB
                    //step 2: insert into DB 
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String fullName = request.getParameter("fullname");
                    String role = request.getParameter("isAdmin");                 
                    //convert
                    int Role = Integer.parseInt(role);                   
                    Account obj = new Account(username, password, fullName, Role);
                    int n = dao.createAccount(obj);
                    String st = "";
                    if (n > 0) {
                        st = "insert success";
                    } else {
                        st = "can't insert because...";
                    }
                    response.sendRedirect("AccountURL?message=" + st);
                }
            }
            if (service.equals("updateAccount")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String username = request.getParameter("id");
                    Vector<Account> vector = dao.getData("select * from Account where username='" + username + "'");
                    request.setAttribute("data", vector);
                    RequestDispatcher dispath = request.getRequestDispatcher("/jsp/update/UpdateAccount.jsp");
                    dispath.forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String fullName = request.getParameter("fullname");
                    String role = request.getParameter("isAdmin");                  
                    //convert
                    int Role = Integer.parseInt(role);                   
                    Account obj = new Account(username, password, fullName, Role);
                    int n = dao.updateAccount(obj);
                    String st = "";
                    if (n > 0) {
                        st = "update success";
                    } else {
                        st = "can't update because...";
                    }
                    response.sendRedirect("AccountURL?message=" + st);
                }
            }
            if (service.equals("searchUser")){
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String username = request.getParameter("username");
                    Vector<Account> vector = dao.getData("select * from Account where username='" + username + "'");
                    String tableAccount = "List of Accounts";
                    //set data for view/jsp
                    request.setAttribute("message", message);
                    request.setAttribute("data", vector);
                    request.setAttribute("tableAccount", tableAccount);
                    // select view/jsp
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/display/DisplayAccount.jsp");
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
