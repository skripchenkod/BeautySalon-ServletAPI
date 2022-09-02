package ua.skrypchenko.beautysalon.dao;

import ua.skrypchenko.beautysalon.entity.Comment;

import java.util.List;

public interface CommentDao {
    void insertComment(Comment comment);

    public List<Comment> getAllComments();
}
