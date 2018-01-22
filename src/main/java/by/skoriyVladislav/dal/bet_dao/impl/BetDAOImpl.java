package by.skoriyVladislav.dal.bet_dao.impl;

import by.skoriyVladislav.dal.bet_dao.BetDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BetDAOImpl implements BetDAO {
    private final static String URL = "jdbc:mysql://localhost:3306/firebets";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String REGISTRATION_BET = "INSERT INTO bets (Login, idMatchs, Size, Type) VALUES (?, ?, ?, ?)";

    @Override
    public boolean registrationBet(String login, int matchID, double size, String type) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(REGISTRATION_BET)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, matchID);
            preparedStatement.setDouble(3, size);
            preparedStatement.setString(4, type);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return true;
    }
}
