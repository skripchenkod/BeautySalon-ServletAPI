package ua.skrypchenko.beautysalon.dao.impl;

import ua.skrypchenko.beautysalon.config.PostgresConfig;
import ua.skrypchenko.beautysalon.dao.CommentDao;
import ua.skrypchenko.beautysalon.entity.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentImpl implements CommentDao {

    private final String SQL_INSERT_COMMENT= "INSERT INTO comments (comment_text, comment_date, service_mark, commentator_id, master_id) VALUES (?, ?, ?, (SELECT user_id FROM users WHERE username = ?), (SELECT user_id FROM users WHERE username = ?))";

    PostgresConfig postgresConfig = new PostgresConfig();
    private Connection connection;

    @Override
    public void insertComment(Comment comment) {
        try {
            this.connection = postgresConfig.getСonnection();

            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_COMMENT);
            ps.setString(1, comment.getCommentText());
            ps.setDate(2, comment.getCommentDate());
            ps.setInt(3, comment.getServiceMark());
            ps.setString(4, comment.getCommentator().getUsername());
            ps.setString(5, comment.getMaster().getUsername());

            ps.executeQuery();
        }
        catch (SQLException s){
            s.printStackTrace();
        }

    }
}
