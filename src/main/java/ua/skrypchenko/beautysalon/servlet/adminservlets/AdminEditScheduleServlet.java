package ua.skrypchenko.beautysalon.servlet.adminservlets;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.entity.MasterSchedule;
import ua.skrypchenko.beautysalon.exeption.InvalidDateParameterException;
import ua.skrypchenko.beautysalon.handler.ExceptionHandler;
import ua.skrypchenko.beautysalon.service.CommentService;
import ua.skrypchenko.beautysalon.service.MasterScheduleService;
import ua.skrypchenko.beautysalon.service.UserService;

import javax.crypto.MacSpi;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPage/editSchedule")
public class AdminEditScheduleServlet extends HttpServlet {

    private final UserService service = new UserService();
    private final MasterScheduleService masterScheduleService = new MasterScheduleService();
    private static final Logger LOGGER = Logger.getLogger(AdminEditScheduleServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("masters", service.getMasters());


        req.getServletContext().getRequestDispatcher("/jsp/adminEditSchedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workDay = req.getParameter("workDay");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        String masterName = req.getParameter("masterName");

        try {
            masterScheduleService.setMasterSchedule(workDay, startTime, endTime, masterName);
            LOGGER.info("The request was accepted");
            req.getSession().setAttribute("message", "You have successfully set new work day!");
            req.getSession().setAttribute("type", "success fade show");
            resp.sendRedirect("/adminPage/editSchedule");
        }
        catch (InvalidDateParameterException e){
            ExceptionHandler exceptionHandler = new ExceptionHandler(e, e.getMessage(), "danger fade show", "/adminPage/editSchedule");
            exceptionHandler.handling(req,resp);
        }
    }
}
