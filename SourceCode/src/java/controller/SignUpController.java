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
import jakarta.servlet.http.HttpSession;
import model.AccountDAO;

/**
 *
 * @author hieul
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpURL"})
public class SignUpController extends HttpServlet {

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
                service = "signUpForm";
            }
            String message = request.getParameter("message");
            if (message == null) {
                message = "";
            }

            if (service.equals("signUpForm")) {
                request.setAttribute("message", message);
                RequestDispatcher dispath
                        = request.getRequestDispatcher("/jsp/SignUp.jsp");
                // run
                dispath.forward(request, response);
            }
            if (service.equals("checkSignUp")) {
                String username = request.getParameter("username");
                String pass1 = request.getParameter("password1");
                String pass2 = request.getParameter("password2");
                String fullname = request.getParameter("fullname");
                if (!pass1.equals(pass2)) {
                    message = "Username existed!";
                    request.setAttribute("message", message);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/SignUp.jsp");
                    // run
                    dispath.forward(request, response);
                }
                int userSignUp = dao.createAccount(new Account(username, pass1, fullname, 0));
                if (userSignUp == 0) {
                    message = "Username existed!";
                    request.setAttribute("message", message);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/SignUp.jsp");
                    // run
                    dispath.forward(request, response);
                } else {
                    message = "SignUp success!";
                    request.setAttribute("message", message);
                    RequestDispatcher dispath
                            = request.getRequestDispatcher("/jsp/Login.jsp");
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
