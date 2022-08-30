package ua.skrypchenko.beautysalon.servlet;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.exeption.UserNotFoundException;
import ua.skrypchenko.beautysalon.service.PasswordEncoderService;
import ua.skrypchenko.beautysalon.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {
    UserService userService = new UserService();
    PasswordEncoderService passwordEncoderService = new PasswordEncoderService();
    private static final Logger LOGGER = Logger.getLogger(LogInServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eMail = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        UserDto user = new UserDto(eMail, passwordEncoderService.encode(password));

        try {
            String result = userService.chekUser(user);
            LOGGER.info("User " + eMail + " logged in successfully");
            session.setAttribute("userName", userService.getUserName(user));
            session.setAttribute("role", result);
            resp.sendRedirect(result.toLowerCase(Locale.ROOT)+"Page");
        } catch (UserNotFoundException e) {
            LOGGER.warn("User not found with " + eMail + " email and provided password");
            resp.sendRedirect("logIn");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/logintest.jsp").forward(req, resp);
    }
}
