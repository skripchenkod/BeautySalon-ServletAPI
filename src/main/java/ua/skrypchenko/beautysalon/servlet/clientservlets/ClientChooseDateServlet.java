package ua.skrypchenko.beautysalon.servlet.clientservlets;

import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.exeption.NoFreeDateException;
import ua.skrypchenko.beautysalon.handler.ExceptionHandler;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientPage/chooseDate")
public class ClientChooseDateServlet extends HttpServlet {
    private final ProcedureService procedureService = new ProcedureService();
    private final MasterScheduleService masterScheduleService = new MasterScheduleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String procedureName = req.getParameter("procedureName");
        req.setAttribute("nameProcedure", procedureName);
        try {
            List<MastersScheduleDto> mastersScheduleDtos = masterScheduleService.getScheduleForClient(procedureName);
            req.setAttribute("schedule", mastersScheduleDtos);
            req.getServletContext().getRequestDispatcher("/jsp/clientChooseDate.jsp").forward(req, resp);
        }
        catch (NoFreeDateException e){
            ExceptionHandler exceptionHandler = new ExceptionHandler(e, "No free days to sign up", "danger fade show", "/clientPage");
            exceptionHandler.handling(req,resp);
        }
    }
}
