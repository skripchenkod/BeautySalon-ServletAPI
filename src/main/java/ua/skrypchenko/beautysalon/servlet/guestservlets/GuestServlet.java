package ua.skrypchenko.beautysalon.servlet.guestservlets;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.service.ProcedureService;
import ua.skrypchenko.beautysalon.service.UserService;
import ua.skrypchenko.beautysalon.servlet.CommentServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/guestPage")
public class GuestServlet extends HttpServlet {

    private final ProcedureService guestService = new ProcedureService();
    private final UserService userService = new UserService();
    private static final Logger LOGGER = Logger.getLogger(GuestServlet.class);

    List<Rating> masters = userService.getMasters();
    List<ProcedureDto> procedures = guestService.getAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("role", "guest");

        req.setAttribute("procedures", procedures);
        req.setAttribute("masters", masters);

        req.getServletContext().getRequestDispatcher("/jsp/guestPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null) {
            masters = userService.getSortMastersByName();
            LOGGER.info("The request was accepted");
            resp.sendRedirect("guestPage");
        }

        if (req.getParameter("rating") != null) {
            masters = userService.getSortMastersByRating();
            LOGGER.info("The request was accepted");
            resp.sendRedirect("guestPage");;
        }

        String masterName = req.getParameter("master");
        if (masterName != null){
            procedures = guestService.getProcedureByNameOfMaster(masterName);
            LOGGER.info("The request was accepted");
            resp.sendRedirect("guestPage");
        }
    }
}
