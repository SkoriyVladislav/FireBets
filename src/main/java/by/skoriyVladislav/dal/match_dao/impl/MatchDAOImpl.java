package by.skoriyVladislav.dal.match_dao.impl;

import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.UserRole;

import java.sql.*;
import java.util.*;

public class MatchDAOImpl implements MatchDAO {

    private final static String URL = "jdbc:mysql://localhost:3306/firebets";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String FIND_MATCH = "SELECT * FROM matches";
    private final static String FIND_COEFF = "SELECT * FROM coefficient WHERE Matches_idMatchs = ?";

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

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_MATCH)) {

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String team1 = resultSet.getString("team1");
                String team2 = resultSet.getString("team2");
                String[] dateTime = resultSet.getString("datetime").replace(".", " ").split(" ");
                int id = resultSet.getInt("idMatchs");
                int goalsTeam1 = resultSet.getInt("goalsTeam1");
                int goalsTeam2 = resultSet.getInt("goalsTeam2");

                double coefTeam1 = 0.0;
                double coefTeam2 = 0.0;
                double coefDraw = 0.0;
                double coefExAcc = 0.0;

                try (PreparedStatement preparedStatement1 = connection.prepareStatement(FIND_COEFF)) {
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
                matches.add(match);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        matches.sort(Comparator.comparing(Match::getData).thenComparing(Match::getTime).reversed());
        return matches;
    }
}
