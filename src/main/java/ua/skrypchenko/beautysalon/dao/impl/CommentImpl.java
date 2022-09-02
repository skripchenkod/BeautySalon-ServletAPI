package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.CommentDao;
import ua.skrypchenko.beautysalon.entity.Comment;
import ua.skrypchenko.beautysalon.entity.User;
import ua.skrypchenko.beautysalon.exeption.DBConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentImpl implements CommentDao {

    private final String SQL_INSERT_COMMENT = "INSERT INTO comments (comment_text, comment_date, service_mark, commentator_id, master_id) VALUES (?, ?, ?, (SELECT user_id FROM users WHERE username = ?), (SELECT user_id FROM users WHERE username = ?))";
    private final String SQL_GET_ALL_COMMENTS = "SELECT comment_text, service_mark, comment_date, u.username as client, u2.username as master  FROM comments left join users u on comments.commentator_id = u.user_id left join users u2 on u2.user_id = comments.master_id";

    private final PostgresConfig postgresConfig = new PostgresConfig();

    @Override
    public void insertComment(Comment comment) {
        try (Connection connection = postgresConfig.getСonnection();
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT_COMMENT)) {

            ps.setString(1, comment.getCommentText());
            ps.setDate(2, comment.getCommentDate());
            ps.setInt(3, comment.getServiceMark());
            ps.setString(4, comment.getCommentator().getUsername());
            ps.setString(5, comment.getMaster().getUsername());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = postgresConfig.getСonnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(SQL_GET_ALL_COMMENTS);

            while (rs.next()) {
                Comment comment = new Comment(new User(rs.getString("master")),
                        new User(rs.getString("client")),
                        rs.getString("comment_text"),
                        rs.getInt("service_mark"),
                        rs.getDate("comment_date"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new DBConnectionException(e);
        }
        return comments;
    }
}
