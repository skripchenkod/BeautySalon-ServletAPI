package ua.skrypchenko.beautysalon.servlet.clientservlets;

import ua.skrypchenko.beautysalon.dto.MastersScheduleDto;
import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.ProcedureService;
import ua.skrypchenko.beautysalon.service.ReservationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/clientPage/chooseDate")
public class ClientChooseDateServlet extends HttpServlet {
    private final ProcedureService procedureService = new ProcedureService();
    private final MasterScheduleService masterScheduleService = new MasterScheduleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProcedureDto> procedures = procedureService.getAll();
        req.setAttribute("procedures", procedures);

        String procedureName = req.getParameter("procedureName");
        req.setAttribute("nameProcedure", procedureName);
        List<MastersScheduleDto> mastersScheduleDtos = masterScheduleService.getScheduleForClient(procedureName);
        req.setAttribute("schedule", mastersScheduleDtos);

        req.getServletContext().getRequestDispatcher("/jsp/clientChooseDate.jsp").forward(req, resp);
    }
}
