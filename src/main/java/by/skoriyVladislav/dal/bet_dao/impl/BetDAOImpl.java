package by.skoriyVladislav.dal.bet_dao.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.bet.BetType;
import by.skoriyVladislav.entity.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BetDAOImpl implements BetDAO {
    private final static String URL = "jdbc:mysql://localhost:3306/firebets";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String INSERT_BETS = "INSERT INTO bets (Users_Login, Matches_idMatches, Size, Type, goalsTeam1, goalsTeam2) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SELECT_FROM_BETS_WHERE_USERS_LOGIN = "SELECT * FROM bets WHERE Users_Login = ?";

    @Override
    public boolean registrationBet(Bet bet, User user) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BETS)) {
            preparedStatement.setString(1, bet.getLoginUser());
            preparedStatement.setInt(2, bet.getIdMatches());
            preparedStatement.setDouble(3, bet.getSize());
            preparedStatement.setString(4, bet.getType().getType());
            preparedStatement.setObject(5, bet.getGoalsTeam1());
            preparedStatement.setObject(6, bet.getGoalsTeam2());
            //preparedStatement.setInt(5, bet.getGoalsTeam1());
            //preparedStatement.setInt(6, bet.getGoalsTeam2());
            if (DAOFactory.getInstance().getUserDAO().transaktion(user, -bet.getSize())) {
                preparedStatement.execute();
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return true;
    }

    @Override
    public List<Bet> createBet(String userLogin) {
        List<Bet> bets = new ArrayList<>();
        Bet bet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return bets;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_BETS_WHERE_USERS_LOGIN)) {
            preparedStatement.setString(1 , userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bet = createBet(resultSet);
                bets.add(bet);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        bets.sort(Comparator.comparing(Bet::getIdMatches).reversed());
        return bets;
    }

    private Bet createBet(ResultSet resultSet) throws SQLException {
        Bet bet = null;

        String loginUser = resultSet.getString("Users_Login");
        int idMatches = resultSet.getInt("Matches_idMatches");
        int size = resultSet.getInt("Size");
        BetType betType = BetType.valueOf(resultSet.getString("Type").toUpperCase());
        String status = resultSet.getString("status");

        Integer goalsTeam1;
        try {
            goalsTeam1 = Integer.valueOf(resultSet.getString("goalsTeam1"));
        } catch (Exception ex) {
            goalsTeam1 = null;
        }

        Integer goalsTeam2;
        try {
            goalsTeam2 = Integer.valueOf(resultSet.getString("goalsTeam2"));
        } catch (Exception ex) {
            goalsTeam2 = null;
        }

        bet = new Bet(loginUser, idMatches, size, betType, goalsTeam1, goalsTeam2, status);

        return bet;
    }
}
