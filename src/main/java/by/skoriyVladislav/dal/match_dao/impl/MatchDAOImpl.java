package by.skoriyVladislav.dal.match_dao.impl;

import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.UserRole;

import java.sql.*;

public class MatchDAOImpl implements MatchDAO {

    private final static String URL = "jdbc:mysql://localhost:3306/firebets";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String FIND_USER = "SELECT * FROM users WHERE Login = ? AND Password = ?";

    @Override
    public Match createMatch(String fLogin, String fPassword) {
        Match match = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return match;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER)) {
            preparedStatement.setString(1, fLogin);
            preparedStatement.setString(2, fPassword);

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String login = resultSet.getString("Login");
                String name = resultSet.getString("Name");
                String surname = resultSet.getString("SurName");
                String password = resultSet.getString("Password");
                double money = resultSet.getDouble("Balance");
                String email = resultSet.getString("Email");
                UserRole role = UserRole.valueOf(resultSet.getString("Role").toUpperCase());
                //match = new User(login, name, surname, password, money, email, role);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return match;
    }
}
