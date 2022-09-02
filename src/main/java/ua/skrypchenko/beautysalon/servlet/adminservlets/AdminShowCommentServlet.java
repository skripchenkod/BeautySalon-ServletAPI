package ua.skrypchenko.beautysalon.servlet.adminservlets;

import ua.skrypchenko.beautysalon.service.CommentService;
import ua.skrypchenko.beautysalon.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPage/allComments")
public class AdminShowCommentServlet extends HttpServlet {

    private final CommentService service = new CommentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("comments", service.getAllComments());

        req.getServletContext().getRequestDispatcher("/jsp/adminShowComment.jsp").forward(req, resp);
    }
}
