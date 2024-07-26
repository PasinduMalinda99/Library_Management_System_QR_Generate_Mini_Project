package Servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/admin/*")
public class AuthorizationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // Do not create session if it doesn't exist

        // Check if user is logged in
        if (session == null || session.getAttribute("username") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else {
            // User is authenticated, continue with the request
            chain.doFilter(request, response);
        }
    }

    // Other methods (init, destroy) can be left empty
}
