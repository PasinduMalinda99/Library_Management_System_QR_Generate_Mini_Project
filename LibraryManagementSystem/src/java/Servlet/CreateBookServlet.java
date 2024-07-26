/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import Controller.AddBook;
import Controller.LoadBooks;
import Model.Book;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;

/**
 *
 * @author Avindu Aloka
 */
@WebServlet(name = "CreateBookServlet", urlPatterns = {"/CreateBookServlet"})
public class CreateBookServlet extends HttpServlet {

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
            out.println("<title>Servlet CreateBookServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateBookServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        if (session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
        } else {
            Book book = new Book();
            book.setBookTitle(request.getParameter("booktitle"));
            book.setBookDescription(request.getParameter("bookDescription"));
            book.setBookAuthor(request.getParameter("author"));
            String errorMessage = "";
            if (book.getBookTitle().equals(null) || book.getBookTitle().equals("")) {
                errorMessage = "Sorry not your file added field empty.";
                // Set error message in request attribute
                request.setAttribute("errorMessage", errorMessage);
                
                LoadBooks bkso = new LoadBooks();
                bkso.listBooks();
                request.setAttribute("books", bkso.books);
                RequestDispatcher dispatcher = request.getRequestDispatcher("DashBoard.jsp");
                dispatcher.forward(request, response);

            } else {
                AddBook adBk = new AddBook();
                boolean result = adBk.setCreateBook(book);
                if (result) {

                    LoadBooks bkso = new LoadBooks();
                    bkso.listBooks();
                    request.setAttribute("books", bkso.books);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DashBoard.jsp");
                    dispatcher.forward(request, response);
                    /*response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n"
                    + "<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js' integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>");
            out.println("<title></title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Your Product is SuccessFully Added</h1>");
            out.println("<p>Continue to show your product</p>");
            out.println("<a href='DashBoard.jsp' class='btn btn-primary'>Continue</a>");
            out.println("</body>");
            out.println("</html>");
            errorMessage = "";*/
                } else {
                    errorMessage = "Sorry not your file added.";
                    // Set error message in request attribute
                    request.setAttribute("errorMessage", errorMessage);

                    // Forward the request to the JSP for displaying the message
                    request.getRequestDispatcher("DashBoard.jsp").forward(request, response);
                }
            }

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
