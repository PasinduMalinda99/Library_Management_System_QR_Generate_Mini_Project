/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.CreateQr;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Avindu Aloka
 */
@WebServlet(name = "GenerateQrCodeSevlet", urlPatterns = {"/GenerateQrCodeSevlet"})
public class GenerateQrCodeSevlet extends HttpServlet {

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
            out.println("<title>Servlet GenerateQrCodeSevlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerateQrCodeSevlet at " + request.getContextPath() + "</h1>");
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
        String BookId = request.getParameter("bookId");
        String BookTitle = request.getParameter("bookTitle");
        String BookDes = request.getParameter("bookdescription");
        String BookAuthor = request.getParameter("bookAuthor");
        String BookRegDate = request.getParameter("bookReg");
        String ProfileId = request.getParameter("userId");

        String QrGen = BookId + " " + BookTitle + " " + BookAuthor + " " + ProfileId + " " + BookRegDate + " PASS";
        try {
            Thread.sleep(5000);
            CreateQr crQr = new CreateQr(QrGen);
            crQr.FileDeletion();
            boolean results = crQr.isRealesed(Integer.parseInt(ProfileId), Integer.parseInt(BookId));
            if (results == true) {
                Thread.sleep(1000);
                crQr.imageDataGet(Integer.parseInt(ProfileId), Integer.parseInt(BookId));

                request.setAttribute("BookId", BookId);
                request.setAttribute("BookTitle", BookTitle);
                request.setAttribute("BookDes", BookDes);
                request.setAttribute("BookAuthor", BookAuthor);

                Thread.sleep(2000);
                request.getRequestDispatcher("QrTicketView.jsp").forward(request, response);
            } else {
                crQr.createQr();
                Thread.sleep(1000);
                boolean result = crQr.SaveQrCode(Integer.parseInt(ProfileId), Integer.parseInt(BookId));

                request.setAttribute("BookId", BookId);
                request.setAttribute("BookTitle", BookTitle);
                request.setAttribute("BookDes", BookDes);
                request.setAttribute("BookAuthor", BookAuthor);

                Thread.sleep(2000);
                response.sendRedirect("QrTicketView.jsp");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
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
