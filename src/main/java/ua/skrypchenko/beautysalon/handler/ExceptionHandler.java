package ua.skrypchenko.beautysalon.handler;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler {
    private static final String DEFAULT_REQUEST_TYPE = "forward";

    private static final Logger LOGGER = LogManager.getLogger(ExceptionHandler.class);

    private final Exception exception;
    private final String TEXT;
    private final String TYPE;
    private final String PAGE;


    public ExceptionHandler(Exception exception, String TEXT, String TYPE, String PAGE) {
        this.exception = exception;
        this.TYPE = TYPE;
        this.TEXT = TEXT;
        this.PAGE = PAGE;
    }

    public void handling(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("message", TEXT);
        req.getSession().setAttribute("type", TYPE);
        resp.sendRedirect(PAGE);
        LOGGER.info(exception.getMessage());
    }
}
