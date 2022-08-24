package ua.skrypchenko.beautysalon.servlet.masterservlets;

import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.entity.Reservation;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.ReservationService;

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

@WebServlet("/masterPage")
public class MasterServlet extends HttpServlet {
    MasterScheduleService masterScheduleService = new MasterScheduleService();
    ReservationService reservationService = new ReservationService();
    Reservation reservation = new Reservation();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session  = req.getSession(false);
        String masterName = (String) session.getAttribute("userName");

        List<MastersScheduleDto> schedule = masterScheduleService.getScheduleForMaster(masterName);
        req.setAttribute("workdays", schedule);

        req.getServletContext().getRequestDispatcher("/jsp/masterPage.jsp").forward(req, resp);
    }
}
