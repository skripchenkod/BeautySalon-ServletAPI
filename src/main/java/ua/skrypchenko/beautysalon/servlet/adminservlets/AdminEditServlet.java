package ua.skrypchenko.beautysalon.servlet.adminservlets;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.entity.Procedure;
import ua.skrypchenko.beautysalon.entity.Reservation;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.exeption.NoFreeTimeSlotsException;
import ua.skrypchenko.beautysalon.handler.ExceptionHandler;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.ReservationService;
import ua.skrypchenko.beautysalon.service.UserService;
import ua.skrypchenko.beautysalon.servlet.masterservlets.MasterChooseDateServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/adminPage/edit")
public class AdminEditServlet extends HttpServlet {
    private final Reservation reservation = new Reservation();
    private final MasterScheduleService masterScheduleService = new MasterScheduleService();
    private final UserService userService = new UserService();
    private final ReservationService reservationService = new ReservationService();
    private static final Logger LOGGER = Logger.getLogger(AdminEditServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("masters", userService.getMasterWithClient());
        String masterName = req.getParameter("mastername");
        String date = req.getParameter("date");
        String reservationId =req.getParameter("reservationId");
        HttpSession session = req.getSession();

        try {
            List<String> freeSlots = masterScheduleService.getFreeTimeSlot(masterName, date);
            req.setAttribute("freeslots", freeSlots);

            reservation.setId(Integer.parseInt(reservationId));
            reservation.setProcedure(new Procedure(req.getParameter("procedureName")));
            req.getServletContext().getRequestDispatcher("/jsp/adminEdit.jsp").forward(req, resp);
        }
        catch (NoFreeTimeSlotsException e){
            ExceptionHandler exceptionHandler = new ExceptionHandler(e, "No free time slots to sign up, choose another day", "danger fade show", "/clientPage");
            exceptionHandler.handling(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String freeTime = req.getParameter("freeTime");
        reservation.setStart(Time.valueOf(freeTime));
        reservationService.updateReservation(reservation);
        LOGGER.info("The request was accepted");
        session.setAttribute("message", "You have successfully edit reservation!");
        session.setAttribute("type", "success fade show");

        resp.sendRedirect("/adminPage/editReservation");
    }
}
