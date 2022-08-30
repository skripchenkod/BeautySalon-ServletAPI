package ua.skrypchenko.beautysalon.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.skrypchenko.beautysalon.exeption.DBConnectionException;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class PostgresConfig implements DbConnectionConstants{

    private static final Logger LOGGER = LogManager.getLogger(PostgresConfig.class);

    private final BasicDataSource dataSource;

    public PostgresConfig() {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_DATABASE);
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(bundle.getString(DB_URL));
        ds.setUsername(bundle.getString(DB_USER));
        ds.setPassword(bundle.getString(DB_PASSWORD));
        ds.setDriverClassName(bundle.getString(DB_DRIVER));
        ds.setMinIdle(Integer.parseInt(bundle.getString(DB_MIN_IDLE)));
        ds.setMaxIdle(Integer.parseInt(bundle.getString(DB_MAX_IDLE)));
        ds.setInitialSize(Integer.parseInt(bundle.getString(DB_INITIAL_SIZE)));
        ds.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString(DB_MAX_OPEN_STATEMENT)));
        dataSource = ds;
    }


    public  Connection getСonnection() {
        LOGGER.info("connect to db success");
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.info("connection error", e);
            throw new DBConnectionException(e);
        }
    }


}