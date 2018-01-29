package by.skoriyVladislav.dal.bet_dao.impl;

import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.entity.bets.Bet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BetDAOImpl implements BetDAO {
    private final static String URL = "jdbc:mysql://localhost:3306/firebets";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String INSERT_BETS = "INSERT INTO bets (Users_Login, Matches_idMatchs, Size, Type, goalsTeam1, goalsTeam2) VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public boolean registrationBet(Bet bet) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BETS)) {
            preparedStatement.setString(1, bet.getLoginUser());
            preparedStatement.setInt(2, bet.getIdMatches());
            preparedStatement.setDouble(3, bet.getSize());
            preparedStatement.setString(4, bet.getType().getName());
            preparedStatement.setObject(5, bet.getGoalsTeam1());
            preparedStatement.setObject(6, bet.getGoalsTeam2());
            //preparedStatement.setInt(5, bet.getGoalsTeam1());
            //preparedStatement.setInt(6, bet.getGoalsTeam2());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return true;
    }

    @Override
    public List<Bet> createBet(String userLogin, String matchId) {


        return null;
    }
}
