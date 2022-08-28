package ua.skrypchenko.beautysalon.service;

import ua.skrypchenko.beautysalon.dao.CommentDao;
import ua.skrypchenko.beautysalon.dao.impl.CommentImpl;
import ua.skrypchenko.beautysalon.entity.Comment;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class CommentService {
    final String username = "skrypchenko.dm@icloud.com";
    final String password = "1234";

    CommentDao commentDao = new CommentImpl();

    public void send(String emailTo, String masterName, String clientName){
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.mail.me.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailTo));
            message.setSubject("Leave Comment");
            message.setText("Dear, "+ clientName.toUpperCase(Locale.ROOT) + ", leave comment about our master:\n" +
                    "http://localhost:8080/commentPage?masterName=" + masterName + "&clientName=" + clientName);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer>getMarks(){
        List<Integer> marks = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            marks.add(i);
        }
        return marks;
    }

    public void insertComment (Comment comment){
        commentDao.insertComment(comment);
    }
}
