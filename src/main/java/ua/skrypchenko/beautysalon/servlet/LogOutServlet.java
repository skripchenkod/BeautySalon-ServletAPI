package ua.skrypchenko.beautysalon.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logOut")
public class LogOutServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LogOutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("User logged out successfully");
        HttpSession httpSession = req.getSession(false);
        httpSession.invalidate();
        HttpSession session = req.getSession();
        session.setAttribute("message", "You log out!");
        session.setAttribute("type", "success fade show");
        resp.sendRedirect("logIn");
    }
}
