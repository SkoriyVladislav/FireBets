package by.skoriyVladislav.dal.user_dao.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.entity.user.UserRole;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final static String URL = "jdbc:mysql://localhost:3306/firebets"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    private final static String SELECT_FROM_USERS_WHERE_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE Login = ? AND Password = ?";
    private final static String INSERT_USERS = "INSERT INTO users (Login, Password, Name, SurName, Role, Balance, Email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SELECT_FROM_USERS_WHERE_LOGIN = "SELECT * FROM users WHERE Login = ?";
    private final static String SELECT_BALANCE_FROM_USERS_WHERE_LOGIN = "SELECT Balance FROM users WHERE Login = ?";
    private final static String UPDATE_USERS_SET_BALANCE_BALANCE_WHERE_LOGIN = "UPDATE users SET Balance = Balance + ? WHERE Login = ?";
    private final static String SELECT_FROM_USERS_WHERE_CRITERIA_IS_LOGIN = "SELECT * FROM users WHERE Login LIKE ?";
    private final static String SELECT_FROM_USERS_WHERE_CRITERIA_IS_NAME = "SELECT * FROM users WHERE Name LIKE ?";
    private final static String SELECT_FROM_USERS_WHERE_CRITERIA_IS_SURNAME = "SELECT * FROM users WHERE SurName LIKE ?";
    private final static String UPDATE_USERS_SET_ROLE_WHERE_LOGIN = "UPDATE users SET Role = ? WHERE Login = ?";

    @Override
    public List<User> getUsers(String criteria) throws DAOException {
        List<User> setUsers = new ArrayList<>();
        HashSet<User> hashSetUsers = new HashSet<>();

        hashSetUsers.addAll(createUsersByCriteria(SELECT_FROM_USERS_WHERE_CRITERIA_IS_LOGIN,'%' + criteria + '%'));
        hashSetUsers.addAll(createUsersByCriteria(SELECT_FROM_USERS_WHERE_CRITERIA_IS_NAME,'%' + criteria + '%'));
        hashSetUsers.addAll(createUsersByCriteria(SELECT_FROM_USERS_WHERE_CRITERIA_IS_SURNAME,'%' + criteria + '%'));

        setUsers.addAll(hashSetUsers);
        return setUsers;
    }

    private HashSet<User> createUsersByCriteria(String statement, String criteria) throws DAOException{
        HashSet<User> setUsers = new HashSet<>();
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1, criteria);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUser(resultSet);
                setUsers.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        return setUsers;
    }

    @Override
    public User getUser(String fLogin, String fPassword) throws DAOException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN_AND_PASSWORD);

            preparedStatement.setString(1, fLogin);
            preparedStatement.setString(2, fPassword);

            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                user = getUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public User getUser(String fLogin) throws DAOException{
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN);

            preparedStatement.setString(1, fLogin);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = getUser(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return user;
    }

    @Override
    public boolean registerUser(String login, String password, String name, String surname,
                                String role, BigDecimal balance, String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USERS);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, role);
            preparedStatement.setBigDecimal(6, balance);
            preparedStatement.setString(7, email);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return true;
    }

    @Override
    public boolean loginInDataBase(String login) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return false;
    }

    @Override
    public boolean checkBalanceForBet(String login, BigDecimal size) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BALANCE_FROM_USERS_WHERE_LOGIN);

            preparedStatement.setString(1, login);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                BigDecimal balance = resultSet.getBigDecimal("Balance");
                return balance.compareTo(size) == 1 || balance.compareTo(size) == 0;
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public boolean transaktion(User user, BigDecimal size) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USERS_SET_BALANCE_BALANCE_WHERE_LOGIN);

            preparedStatement.setBigDecimal(1, size);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.execute();

            user.setBalance(user.getBalance().add(size));
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return true;
    }

    @Override
    public boolean transaktion(String login, BigDecimal size) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USERS_SET_BALANCE_BALANCE_WHERE_LOGIN);

            preparedStatement.setBigDecimal(1, size);
            preparedStatement.setString(2, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return true;
    }

    @Override
    public boolean changeRole(String login, String role) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_USERS_SET_ROLE_WHERE_LOGIN);

            preparedStatement.setString(1, role);
            preparedStatement.setString(2, login);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return true;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet != null) {
            String login = resultSet.getString("Login");
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("SurName");
            BigDecimal money = resultSet.getBigDecimal("Balance");
            String email = resultSet.getString("Email");
            UserRole role = UserRole.valueOf(resultSet.getString("Role").toUpperCase());
            user = new User(login, name, surname, money, email, role);
        }
        return user;
    }
}