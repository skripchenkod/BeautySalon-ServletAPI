package ua.skrypchenko.beautysalon.servlet;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.exeption.AlreadyRegisteredUserException;
import ua.skrypchenko.beautysalon.exeption.InvalidUserParameterException;
import ua.skrypchenko.beautysalon.service.PasswordEncoderService;
import ua.skrypchenko.beautysalon.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    UserService userService = new UserService();
    PasswordEncoderService passwordEncoderService = new PasswordEncoderService();
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserDto userDto = new UserDto(userName, password, email);

        try {
            userService.saveUser(userDto);
        } catch (InvalidUserParameterException e) {
            LOGGER.warn("Validation error " + e);

            resp.sendRedirect("registration");

        } catch (AlreadyRegisteredUserException e) {
            LOGGER.warn("User with such email already exist " + e);

            resp.sendRedirect("registration");;
        }

        resp.sendRedirect("logIn");
    }
}
