package by.skoriyVladislav.dal.connection_pool;

import by.skoriyVladislav.dal.exception.ConnectionPoolException;

import java.sql.*;

public interface ConnectionPool {
    void init() throws ConnectionPoolException;

    Connection getConnection() throws ConnectionPoolException;

    void close(Connection connection, Statement statement, ResultSet resultSet) throws ConnectionPoolException;

    void close(Connection connection, Statement statement) throws ConnectionPoolException;

    void close(Connection connection) throws ConnectionPoolException;

    void destroy();

}
