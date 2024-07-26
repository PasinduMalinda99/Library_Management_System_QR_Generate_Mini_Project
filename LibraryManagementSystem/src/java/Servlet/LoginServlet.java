/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.CreateQr;
import Controller.LoadBooks;
import Controller.Login;
import Model.BoolList;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Avindu Aloka
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setPassWord(request.getParameter("password"));
        String errorMessage = "";
        Login lg = new Login();
        int res = lg.setlogUser(user);
        if (res == 4) {
            User user2 = lg.setUserDetails(user);
            // Create a session
            HttpSession session = request.getSession(true);
            // Set session attribute
            session.setAttribute("userId", user2.getUserId());
            session.setAttribute("username", user2.getUserName());
            session.setAttribute("password", user2.getPassWord());
            session.setAttribute("DateTime", user2.getRegDate());
            session.setAttribute("Role", user2.getRole());

            // Create a cookie for the username
            Cookie usernameCookie = new Cookie("username", user.getUserName());
            Cookie usernameCookie1 = new Cookie("password", user.getPassWord());
            usernameCookie.setMaxAge(24 * 60 * 60); // 1 day
            response.addCookie(usernameCookie);
            response.addCookie(usernameCookie1);
            LoadBooks bkso = new LoadBooks();
            bkso.listBooks();
            request.setAttribute("books", bkso.books);

            if (user2.getRole().equals("Admin")) {

                CreateQr crQr = new CreateQr("Deleted");
                crQr.FileDeletion();

                RequestDispatcher dispatcher = request.getRequestDispatcher("DashBoard.jsp");
                dispatcher.forward(request, response);

                if (bkso.books != null && !bkso.books.isEmpty()) {
                    System.out.println("Fail");
                } else {
                    System.out.println("Pass");
                }

            } else {

                CreateQr crQr = new CreateQr("Deleted");
                crQr.FileDeletion();

                RequestDispatcher dispatcher = request.getRequestDispatcher("ClientDash.jsp");
                dispatcher.forward(request, response);
                if (bkso.books != null && !bkso.books.isEmpty()) {
                    System.out.println("Fail");
                } else {
                    System.out.println("Pass");
                }
            }
        } else {
            errorMessage = "Sorry, Password or Username is incorrect";
            // Set error message in request attribute
            request.setAttribute("errorMessage", errorMessage);

            // Forward the request to the JSP for displaying the message
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
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
