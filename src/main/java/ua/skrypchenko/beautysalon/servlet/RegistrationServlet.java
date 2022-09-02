package ua.skrypchenko.beautysalon.servlet;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.Role;
import ua.skrypchenko.beautysalon.exeption.AlreadyRegisteredUserException;
import ua.skrypchenko.beautysalon.exeption.InvalidUserParameterException;
import ua.skrypchenko.beautysalon.handler.ExceptionHandler;
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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", userService.getRoles());
        req.getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        UserDto userDto;

        if(session != null && session.getAttribute("role").equals("ADMIN")){
            String role =  req.getParameter("role");
            userDto = new UserDto(userName, password, email, Role.valueOf(role.toUpperCase(Locale.ROOT)));
        }
        else {
            userDto = new UserDto(userName, password, email, Role.valueOf("CLIENT"));
        }
        try {
            userService.saveUser(userDto);
            session.setAttribute("message", "You have successfully registered user!");
            session.setAttribute("type", "success fade show");
            LOGGER.info("User " + userName + " is registered successfully");
            resp.sendRedirect("/logIn");
        } catch (InvalidUserParameterException e) {
            ExceptionHandler exceptionHandler = new ExceptionHandler(e, e.getMessage(), "danger fade show", "/registration");
            exceptionHandler.handling(req,resp);

        } catch (AlreadyRegisteredUserException e) {
            ExceptionHandler exceptionHandler = new ExceptionHandler(e, "User already exist. Try another username", "danger fade show", "/registration");
            exceptionHandler.handling(req,resp);
        }

    }
}
