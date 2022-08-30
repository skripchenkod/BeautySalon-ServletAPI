package ua.skrypchenko.beautysalon.servlet.clientservlets;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.entity.Procedure;
import ua.skrypchenko.beautysalon.entity.Reservation;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.ProcedureService;
import ua.skrypchenko.beautysalon.service.ReservationService;
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

@WebServlet("/clientPage/chooseFreeSlots")
public class ClientChooseFreeSlotsServlet extends HttpServlet {
    private final ProcedureService procedureService = new ProcedureService();
    private final MasterScheduleService masterScheduleService = new MasterScheduleService();
    private final ReservationService reservationService = new ReservationService();
    private final Reservation reservation = new Reservation();
    private static final Logger LOGGER = Logger.getLogger(ClientChooseFreeSlotsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProcedureDto> procedures = procedureService.getAll();
        req.setAttribute("procedures", procedures);
        req.setAttribute("procedure", procedures);

        String nameProcedure = req.getParameter("nameProcedure");

        String date = req.getParameter("date");
        String masterName = req.getParameter("mastername");

        List<String> freeSlots = masterScheduleService.getFreeTimeSlot(masterName, date);
        req.setAttribute("freeSlots", freeSlots);

        reservation.setBeautyMaster(new User(masterName));
        reservation.setData(Date.valueOf(date));
        reservation.setProcedure(new Procedure(nameProcedure));

        req.getServletContext().getRequestDispatcher("/jsp/clientChooseFreeSlots.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userName = (String) session.getAttribute("userName");
        System.out.println(userName);
        String timeOfProcedure = req.getParameter("freeslots");
        reservation.setStart(Time.valueOf(timeOfProcedure));
        reservation.setClient(new User(userName));
        reservationService.setReservation(reservation);
        LOGGER.info("The request was accepted");
        resp.sendRedirect("/clientPage");
    }
}
