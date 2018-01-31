package by.skoriyVladislav.dal.user_dao.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.entity.user.UserRole;

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

    @Override
    public List<User> createUsers(String criteria) {
        List<User> setUsers = new ArrayList<>();
        HashSet<User> hashSetUsers = new HashSet<>();

        hashSetUsers.addAll(createUsersByCriteria(SELECT_FROM_USERS_WHERE_CRITERIA_IS_LOGIN,'%' + criteria + '%'));

        hashSetUsers.addAll(createUsersByCriteria(SELECT_FROM_USERS_WHERE_CRITERIA_IS_NAME,'%' + criteria + '%'));

        hashSetUsers.addAll(createUsersByCriteria(SELECT_FROM_USERS_WHERE_CRITERIA_IS_SURNAME,'%' + criteria + '%'));

        setUsers.addAll(hashSetUsers);
        return setUsers;
    }

    private HashSet<User> createUsersByCriteria(String statement, String criteria) {
        HashSet<User> setUsers = new HashSet<>();
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
            preparedStatement.setString(1, criteria);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = createUser(resultSet);
                setUsers.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return setUsers;
    }

    @Override
    public User createUser(String fLogin, String fPassword) {
        User user = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return user;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, fLogin);
            preparedStatement.setString(2, fPassword);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                user = createUser(resultSet);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return user;
    }

    @Override
    public boolean registerUser(String login, String password, String name, String surname,
                                String role, double balance, String email) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, role);
            preparedStatement.setDouble(6, balance);
            preparedStatement.setString(7, email);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return true;
    }

    @Override
    public boolean loginInDataBase(String login) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_LOGIN)) {
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return false;
    }

    @Override
    public boolean checkBalanceForBet(String login, double size) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BALANCE_FROM_USERS_WHERE_LOGIN)) {
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int balance = resultSet.getInt("Balance");
                return balance > size;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return false;
    }

    @Override
    public boolean transaktion(User user, double size) {
        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SET_BALANCE_BALANCE_WHERE_LOGIN)) {

            preparedStatement.setDouble(1, size);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.execute();

            user.setBalance(user.getBalance() + size);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return true;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet != null) {
            String login = resultSet.getString("Login");
            String name = resultSet.getString("Name");
            String surname = resultSet.getString("SurName");
            double money = resultSet.getDouble("Balance");
            String email = resultSet.getString("Email");
            UserRole role = UserRole.valueOf(resultSet.getString("Role").toUpperCase());
            user = new User(login, name, surname, money, email, role);
        }
        return user;
    }
}