package ua.skrypchenko.beautysalon.servlet;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.exeption.UserNotFoundException;
import ua.skrypchenko.beautysalon.handler.ExceptionHandler;
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
    private final UserService userService = new UserService();
    private static final Logger LOGGER = Logger.getLogger(LogInServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        UserDto user = new UserDto(username, password);

        try {
            String result = userService.getUserRole(user);
            LOGGER.info("User " + username + " logged in successfully");
            session.setAttribute("userName", username);
            session.setAttribute("user", username);
            resp.sendRedirect(result.toLowerCase(Locale.ROOT)+"Page");
        } catch (UserNotFoundException e) {
            ExceptionHandler exceptionHandler = new ExceptionHandler(e, "User doesn't exist. Try again!", "danger fade show", "/logIn");
            exceptionHandler.handling(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/logIn.jsp").forward(req, resp);
    }
}
