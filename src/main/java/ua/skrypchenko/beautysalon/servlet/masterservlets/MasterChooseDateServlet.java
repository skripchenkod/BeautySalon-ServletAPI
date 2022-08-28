package ua.skrypchenko.beautysalon.servlet.masterservlets;

import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.entity.Reservation;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.ReservationService;
import ua.skrypchenko.beautysalon.service.CommentService;
import ua.skrypchenko.beautysalon.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.sql.Date;
import java.util.List;

@WebServlet("/masterPage/chooseDate")
public class MasterChooseDateServlet extends HttpServlet {
    MasterScheduleService masterScheduleService = new MasterScheduleService();
    UserService userService = new UserService();
    ReservationService reservationService = new ReservationService();
    Reservation reservation = new Reservation();
    CommentService service = new CommentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session  = req.getSession(false);
        String masterName = (String) session.getAttribute("userName");

        List<MastersScheduleDto> schedule = masterScheduleService.getScheduleForMaster(masterName);
        req.setAttribute("workdays", schedule);

        String date = req.getParameter("workday");

        List<String> freeTimeSlots = masterScheduleService.getFreeTimeSlot(masterName, date);
        req.setAttribute("freeSlots", freeTimeSlots);
        List<String> busyTimeSlots = masterScheduleService.getBusyTimeSlots(masterName, date);
        req.setAttribute("busySlots", busyTimeSlots);

        reservation.setBeautyMaster(new User(masterName));
        reservation.setData(Date.valueOf(date));

        req.getServletContext().getRequestDispatcher("/jsp/masterPageChooseDate.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timeCancel = req.getParameter("busyslots");
        reservation.setStart(Time.valueOf(timeCancel));

        String clientName = reservationService.getClientName(reservation);

        service.send(userService.getEmail(clientName), reservation.getBeautyMaster().getUsername(), clientName);
        reservationService.deleteProcedure(reservation);

        resp.sendRedirect("/masterPage");
    }
}
