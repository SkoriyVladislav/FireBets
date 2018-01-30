package by.skoriyVladislav.dal.match_dao.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.entity.match.Match;

import java.sql.*;
import java.util.*;

public class MatchDAOImpl implements MatchDAO {

    private final static String URL = "jdbc:mysql://localhost:3306/firebets";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";

    private final static String SELECT_FROM_MATCHES = "SELECT * FROM matches";
    private final static String SELECT_FROM_COEFFICIENT_WHERE_MATCHES_ID_MATCHS = "SELECT * FROM coefficient WHERE Matches_idMatchs = ?";
    private final static String SELECT_FROM_MATCHES_WHERE_ID_MATCHS = "SELECT * FROM matches WHERE idMatches = ?";
    private final static String SELECT_FROM_MATCHES_WHERE_TEAM1_AND_TEAM2_AND_DATE_TIME = "SELECT * FROM matches WHERE Team1 = ? AND Team2 = ? AND DateTime = ?";

    private final static String INSERT_MATCHES = "INSERT INTO matches (Team1, Team2, DateTime) VALUES (?, ?, ?)";
    private final static String INSERT_COEFFICIENT = "INSERT INTO coefficient (Matches_idMatchs, CoefTEAM1, CoefTEAM2, CoefDRAW, CoefExAcc) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Match> createMatches() {
        List<Match> matches = new ArrayList<>();
        Match match = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return matches;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_MATCHES)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                match = createMatch(resultSet, connection);
                matches.add(match);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        matches.sort(Comparator.comparing(Match::getData).thenComparing(Match::getTime).reversed());
        return matches;
    }

    @Override
    public Match createMatch(int fId) {
        Match match = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return match;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_MATCHES_WHERE_ID_MATCHS)) {
            preparedStatement.setInt(1, fId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                match = createMatch(resultSet, connection);
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return match;
    }

    @Override
    public boolean registrMatch(String team1, String team2, String dataTime, double[] coef) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MATCHES)) {
            preparedStatement.setString(1, team1);
            preparedStatement.setString(2, team2);
            preparedStatement.setString(3, dataTime);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        Integer matchId = null;
        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_MATCHES_WHERE_TEAM1_AND_TEAM2_AND_DATE_TIME)) {
            preparedStatement.setString(1, team1);
            preparedStatement.setString(2, team2);
            preparedStatement.setString(3, dataTime);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                matchId = resultSet.getInt("idMatchs");
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        return registrCoeff(matchId, coef);
    }

    @Override
    public boolean registrCoeff(int id, double[] coef) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("No have database");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(URL, DAOFactory.getProperties());
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COEFFICIENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setDouble(2, coef[0]);
            preparedStatement.setDouble(3, coef[1]);
            preparedStatement.setDouble(4, coef[2]);
            preparedStatement.setDouble(5, coef[3]);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return true;
    }

    private Match createMatch(ResultSet resultSet, Connection connection) throws SQLException {
        Match match = null;

        String team1 = resultSet.getString("team1");
        String team2 = resultSet.getString("team2");
        String[] dateTime = resultSet.getString("datetime").replace(".", " ").split(" ");
        int id = resultSet.getInt("idMatches");

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

        double coefTeam1 = 0.0;
        double coefTeam2 = 0.0;
        double coefDraw = 0.0;
        double coefExAcc = 0.0;

        try (PreparedStatement preparedStatement1 = connection.prepareStatement(SELECT_FROM_COEFFICIENT_WHERE_MATCHES_ID_MATCHS)) {
            preparedStatement1.setInt(1, id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet1.next()) {
                coefTeam1 = resultSet1.getDouble("CoefTEAM1");
                coefTeam2 = resultSet1.getDouble("CoefTEAM2");
                coefDraw = resultSet1.getDouble("CoefDRAW");
                coefExAcc = resultSet1.getDouble("CoefExAcc");
            }
        }
        match = new Match(id, team1, team2, dateTime[0], dateTime[1], coefTeam1, coefTeam2, coefDraw, coefExAcc, goalsTeam1, goalsTeam2);

        return match;
    }
}
