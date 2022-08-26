package ua.skrypchenko.beautysalon.servlet.adminservlets;

import ua.skrypchenko.beautysalon.entity.Procedure;
import ua.skrypchenko.beautysalon.entity.Reservation;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@WebServlet("/adminPage/edit")
public class AdminEditServlet extends HttpServlet {
    private final Reservation reservation = new Reservation();
    private final MasterScheduleService masterScheduleService = new MasterScheduleService();
    private final ReservationService reservationService = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String masterName = req.getParameter("mastername");
        String date = req.getParameter("date");
        String reservationId =req.getParameter("reservationId");

        System.out.println(masterName + date);

        List<String> freeSlots = masterScheduleService.getFreeTimeSlot(masterName, date);
        req.setAttribute("freeslots", freeSlots);

        reservation.setId(Integer.parseInt(reservationId));
        reservation.setProcedure(new Procedure(req.getParameter("procedureName")));

        req.getServletContext().getRequestDispatcher("/jsp/adminEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String freeTime = req.getParameter("freeTime");
        reservation.setStart(Time.valueOf(freeTime));
        reservationService.updateReservation(reservation);
        resp.sendRedirect("/adminPage/editReservation");
    }
}
