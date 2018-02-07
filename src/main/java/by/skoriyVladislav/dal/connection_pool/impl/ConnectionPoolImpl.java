package by.skoriyVladislav.dal.connection_pool.impl;

import by.skoriyVladislav.dal.connection_pool.ConnectionPool;
import by.skoriyVladislav.dal.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private Logger logger = Logger.getLogger(ConnectionPool.class);

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";


    private String url;
    private String username;
    private String password;
    private int capacity;
    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> usedConnections;

    public ConnectionPoolImpl() {
        try {
            init();
        } catch (ConnectionPoolException ex) {

        }
    }

    @Override
    public void init() throws ConnectionPoolException {

        url = "jdbc:mysql://localhost:3306/firebets"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";
        username = "root";
        password = "root";
        capacity = 10;
        availableConnections = new LinkedBlockingQueue<>(capacity);
        usedConnections = new LinkedBlockingQueue<>(capacity);

        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("Cannot load driver. Class not found.", e);
            throw new ConnectionPoolException("Cannot load driver. Class not found.", e);
        }

        for(int i = 1; i <= capacity; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                availableConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Cannot retrieve connection.", e);
                throw new ConnectionPoolException("Cannot retrieve connection.", e);
            } catch (SQLException e) {
                logger.error("Cannot retrieve new connection to database.", e);
                throw new ConnectionPoolException("Cannot retrieve new connection to database.", e);
            }
            logger.debug("Connection " + i + " was created.");
        }

        logger.info("Connection pool has been successfully initialized.");
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Cannot get connection.", e);
            throw new ConnectionPoolException("Cannot get connection.", e);
        }
        logger.debug("Connection was given to caller.");
        return connection;
    }

    @Override
    public void close(Connection connection, Statement statement, ResultSet resultSet) throws ConnectionPoolException {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Cannot close ResultSet.", e);
            }
        }
        close(connection, statement);
    }

    @Override
    public void close(Connection connection, Statement statement) throws ConnectionPoolException {
        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Cannot close Statement.", e);
            }
        }
        close(connection);
    }

    @Override
    public void close(Connection connection) throws ConnectionPoolException {
        if (connection == null) {
            logger.error("Connection is null.");
            throw new ConnectionPoolException("Connection is null.");
        }

        boolean isOwnConnection = usedConnections.remove(connection);
        if(!isOwnConnection) {
            logger.error("Attempt to close strange connection.");
            throw new ConnectionPoolException("Attempt to close strange connection.");
        }

        try {
            availableConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Cannot return connection into connection pool.");
            throw new ConnectionPoolException("Cannot return connection into connection pool.", e);
        }
        logger.info("Connection was returned into connection pool successfully.");
    }

    @Override
    public void destroy() {
        closeConnections(availableConnections);
        closeConnections(usedConnections);
        logger.info("Connection pool has been successfully destroyed.");
    }

    private void closeConnections(BlockingQueue<Connection> queue) {
        for(Connection connection : queue) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection.", e);
            }
        }
    }
}
