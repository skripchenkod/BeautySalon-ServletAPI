package ua.skrypchenko.beautysalon.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.skrypchenko.beautysalon.dto.UserDto;
import ua.skrypchenko.beautysalon.entity.User;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        String result = userService.chekUser(new UserDto(userName, password));

        if(result.equals("error")){
            resp.sendRedirect("logIn");
        }
        else {
            session.setAttribute("userName", req.getParameter("username"));
            session.setAttribute("role", result);
            resp.sendRedirect(result.toLowerCase(Locale.ROOT)+"Page");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/logIn.jsp").forward(req, resp);

    }
}
