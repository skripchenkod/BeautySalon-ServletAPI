package ua.skrypchenko.beautysalon.servlet.guestservlets;

import ua.skrypchenko.beautysalon.dto.ProcedureDto;
import ua.skrypchenko.beautysalon.entity.Rating;
import ua.skrypchenko.beautysalon.service.ProcedureService;
import ua.skrypchenko.beautysalon.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/guestPage")
public class GuestServlet extends HttpServlet {

    ProcedureService guestService = new ProcedureService();
    UserService userService = new UserService();

    List<Rating> masters = userService.getMasters();
    List<ProcedureDto> procedures = guestService.getAll();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("procedures", procedures);
        req.setAttribute("masters", masters);

        req.getServletContext().getRequestDispatcher("/jsp/guestPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name") != null) {
            masters = userService.getSortMastersByName();
            doGet(req,resp);
        }

        if (req.getParameter("rating") != null) {
            masters = userService.getSortMastersByRating();
            doGet(req,resp);
        }

        String masterName = req.getParameter("master");
        if (masterName != null){
            procedures = guestService.getProcedureByNameOfMaster(masterName);
            doGet(req, resp);
        }
    }
}
