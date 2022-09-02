package ua.skrypchenko.beautysalon.entity;

import java.sql.Date;
import java.util.Objects;

public class Comment {

  private  Integer id;

  private User master;

  private User commentator;

  private String commentText;

  private int serviceMark;

  private Date commentDate;

  public Comment(Integer id, User master, User commentator, String commentText, int serviceMark, Date commentDate) {
    this.id = id;
    this.master = master;
    this.commentator = commentator;
    this.commentText = commentText;
    this.serviceMark = serviceMark;
    this.commentDate = commentDate;
  }

  public Comment(User master, User commentator, String commentText, int serviceMark, Date commentDate) {
    this.master = master;
    this.commentator = commentator;
    this.commentText = commentText;
    this.serviceMark = serviceMark;
    this.commentDate = commentDate;
  }

  public Comment() {
  }

  public Integer getId() {
    return id;
  }

  public User getMaster() {
    return master;
  }

  public Date getCommentDate() {
    return commentDate;
  }

  public void setCommentDate(Date commentDate) {
    this.commentDate = commentDate;
  }

  public void setMaster(User master) {
    this.master = master;
  }

  public User getCommentator() {
    return commentator;
  }

  public void setCommentator(User commentator) {
    this.commentator = commentator;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public int getServiceMark() {
    return serviceMark;
  }

  public void setServiceMark(int serviceMark) {
    this.serviceMark = serviceMark;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Comment)) return false;
    Comment comment = (Comment) o;
    return serviceMark == comment.serviceMark
        && Objects.equals(master, comment.master)
        && Objects.equals(commentator, comment.commentator)
        && Objects.equals(commentText, comment.commentText)
            && Objects.equals(commentDate, comment.commentDate);

  }

  @Override
  public int hashCode() {
    return Objects.hash( master, commentator, commentText, serviceMark,commentDate);
  }

  @Override
  public String toString() {
    return "Comment{" +
            "id=" + id +
            ", master=" + master +
            ", commentator=" + commentator +
            ", commentText='" + commentText + '\'' +
            ", serviceMark=" + serviceMark +
            ", commentDate=" + commentDate +
            '}';
  }
}
