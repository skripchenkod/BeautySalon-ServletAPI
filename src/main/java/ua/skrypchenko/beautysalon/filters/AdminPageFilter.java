package ua.skrypchenko.beautysalon.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/adminPage/*")
public class AdminPageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("role") == null) {

            //servletRequest.getServletContext().getRequestDispatcher("/logIn").forward(request,response);
            response.sendRedirect("logIn");
        }
        else if(!session.getAttribute("role").equals("ADMIN")){
            servletRequest.getServletContext().getRequestDispatcher("/logIn").forward(request,response);

        }

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
