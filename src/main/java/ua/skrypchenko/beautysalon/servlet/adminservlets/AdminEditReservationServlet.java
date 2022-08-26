package ua.skrypchenko.beautysalon.servlet.adminservlets;

import ua.skrypchenko.beautysalon.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPage/editReservation")
public class AdminEditReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/adminEditReservation.jsp").forward(req, resp);
    }
}
