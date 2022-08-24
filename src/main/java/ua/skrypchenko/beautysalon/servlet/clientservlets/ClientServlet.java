package ua.skrypchenko.beautysalon.servlet.clientservlets;

import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.entity.Procedure;
import ua.skrypchenko.beautysalon.entity.Reservation;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.ProcedureService;
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

@WebServlet("/clientPage")
public class ClientServlet extends HttpServlet {
    private final ProcedureService procedureService = new ProcedureService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProcedureDto> procedures = procedureService.getAll();
        req.setAttribute("procedures", procedures);

        req.getServletContext().getRequestDispatcher("/jsp/clientPage.jsp").forward(req, resp);
    }
}
