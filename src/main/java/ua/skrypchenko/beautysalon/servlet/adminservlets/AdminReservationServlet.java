package ua.skrypchenko.beautysalon.servlet.adminservlets;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.entity.Reservation;
import ua.skrypchenko.beautysalon.service.ReservationService;
import ua.skrypchenko.beautysalon.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/adminPage/reservation")
public class AdminReservationServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final ReservationService reservationService = new ReservationService();
    private static final Logger LOGGER = Logger.getLogger(AdminReservationServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("masters", userService.getMasterWithClient());
        String clientName = req.getParameter("client");


        List<Reservation> reservations = reservationService.getReservationByClient(clientName);
        req.setAttribute("reservations", reservations);
        req.setAttribute("clientName", clientName);
        req.getServletContext().getRequestDispatcher("/jsp/adminReservation.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reservationId = req.getParameter("reservationId");
        HttpSession session = req.getSession();
        reservationService.deleteReservation(Integer.parseInt(reservationId));
        LOGGER.info("The request was accepted");
        session.setAttribute("message", "You have successfully delete reservation!");
        session.setAttribute("type", "success fade show");
        resp.sendRedirect("/adminPage/editReservation");
    }
}
