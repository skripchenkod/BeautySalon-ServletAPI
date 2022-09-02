package ua.skrypchenko.beautysalon.servlet.masterservlets;

import ua.skrypchenko.beautysalon.service.MasterScheduleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/masterPage/showSchedule")
public class MasterShowScheduleServlet extends HttpServlet {

    private final MasterScheduleService service = new MasterScheduleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("schedules", service.getScheduleForMaster((String) req.getSession().getAttribute("userName")));

        req.getServletContext().getRequestDispatcher("/jsp/masterShowSchedule.jsp").forward(req, resp);
    }
}
