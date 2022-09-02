package ua.skrypchenko.beautysalon.servlet.adminservlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPage")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/adminPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("editReservation") != null){
            resp.sendRedirect("/adminPage/editReservation");
        }
        if(req.getParameter("showComments") != null){
            resp.sendRedirect("/adminPage/allComments");
        }
        if(req.getParameter("editSchedule") != null){
            resp.sendRedirect("/adminPage/editSchedule");
        }
        if(req.getParameter("registration") != null){
            resp.sendRedirect("/registration");
        }
    }
}
