package ua.skrypchenko.beautysalon.servlet;

import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.entity.Comment;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

@WebServlet("/commentPage")
public class CommentServlet extends HttpServlet {
    CommentService commentService = new CommentService();
    Comment comment = new Comment();
    private static final Logger LOGGER = Logger.getLogger(CommentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        req.setAttribute("clientName", clientName);

        String masterName = req.getParameter("masterName");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date currentDay = new Date(System.currentTimeMillis());

        req.setAttribute("marks", commentService.getMarks());

        comment.setMaster(new User(masterName));
        comment.setCommentator(new User(clientName));
        comment.setCommentDate(currentDay);

        req.getServletContext().getRequestDispatcher("/jsp/commentPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer mark = Integer.parseInt(req.getParameter("mark"));
        String commentText = req.getParameter("commentText");

        comment.setServiceMark(mark);
        comment.setCommentText(commentText);

        commentService.insertComment(comment);
        LOGGER.info("The request was added");
        resp.sendRedirect("/logIn");
    }
}
