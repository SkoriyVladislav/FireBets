package by.skoriyVladislav.dal.match_dao.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.connection_pool.ConnectionPool;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.entity.match.Match;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class MatchDAOImpl implements MatchDAO {
    private Logger logger = Logger.getLogger(ConnectionPool.class);

    private final static String SELECT_FROM_MATCHES = "SELECT * FROM matches";
    private final static String SELECT_FROM_COEFFICIENT_WHERE_MATCHES_ID_MATCHS = "SELECT * FROM coefficient WHERE Matches_idMatches = ?";
    private final static String SELECT_FROM_MATCHES_WHERE_ID_MATCHS = "SELECT * FROM matches WHERE idMatches = ?";
    private final static String SELECT_FROM_MATCHES_WHERE_TEAM1_AND_TEAM2_AND_DATE_TIME = "SELECT * FROM matches WHERE Team1 = ? AND Team2 = ? AND DateTime = ?";
    private final static String str = "SELECT DateTime FROM matches WHERE idMatches = ?";

    private final static String INSERT_MATCHES = "INSERT INTO matches (Team1, Team2, DateTime) VALUES (?, ?, ?)";
    private final static String INSERT_COEFFICIENT = "INSERT INTO coefficient (Matches_idMatches, CoefTEAM1, CoefTEAM2, CoefDRAW, CoefExAcc) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE_COEFFICIENT = "UPDATE coefficient SET CoefTEAM1 = ?, CoefTEAM2 = ?, CoefDRAW = ?, CoefExAcc = ? WHERE Matches_idMatches = ?";
    private final static String UPDATE_MATCHES_SET_GOALS_TEAM1_GOALS_TEAM2_WHERE_ID_MATCHES = "UPDATE matches SET goalsTeam1 = ?, goalsTeam2 = ? WHERE idMatches = ?";

    @Override
    public List<Match> getMatches() throws DAOException {
        List<Match> matches = new ArrayList<>();
        Match match = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_MATCHES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                match = createMatch(resultSet, connection);
                matches.add(match);
            }

        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        matches.sort(Comparator.comparing(Match::getData).thenComparing(Match::getTime).reversed());
        logger.info("The list of matches was given to caller.");
        return matches;
    }

    @Override
    public Match getMatch(int fId) throws DAOException {
        Match match = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_MATCHES_WHERE_ID_MATCHS);
            preparedStatement.setInt(1, fId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                match = createMatch(resultSet, connection);
            }

        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        logger.info("Match was given to caller.");
        return match;
    }

    @Override
    public boolean setResult(int idMatches, int goalsTeam1, int goalsTeam2) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_MATCHES_SET_GOALS_TEAM1_GOALS_TEAM2_WHERE_ID_MATCHES);
            preparedStatement.setInt(1, goalsTeam1);
            preparedStatement.setInt(2, goalsTeam2);
            preparedStatement.setInt(3, idMatches);
            preparedStatement.execute();

            DAOFactory.getInstance().getBetDAO().setResult(idMatches, goalsTeam1, goalsTeam2);
        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement);
        }
        logger.info("Set result of match.");
        return true;
    }

    @Override
    public boolean registrationMatch(String team1, String team2, String dataTime, double[] coef) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_MATCHES);

            preparedStatement.setString(1, team1);
            preparedStatement.setString(2, team2);
            preparedStatement.setString(3, dataTime);

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        Integer matchId = null;
        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_MATCHES_WHERE_TEAM1_AND_TEAM2_AND_DATE_TIME);

            preparedStatement.setString(1, team1);
            preparedStatement.setString(2, team2);
            preparedStatement.setString(3, dataTime);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                matchId = resultSet.getInt("idMatches");
            }
        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        logger.info("The match successfully registered.");
        return registrationCoefficients(matchId, coef);
    }

    private boolean registrationCoefficients(int id, double[] coef) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_COEFFICIENT);

            preparedStatement.setInt(1, id);
            preparedStatement.setDouble(2, coef[0]);
            preparedStatement.setDouble(3, coef[1]);
            preparedStatement.setDouble(4, coef[2]);
            preparedStatement.setDouble(5, coef[3]);

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        logger.info("The coefficient of successfully registered.");
        return true;
    }

    @Override
    public boolean changeCoefficients(int id, double[] coeff) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_COEFFICIENT);

            preparedStatement.setDouble(1, coeff[0]);
            preparedStatement.setDouble(2, coeff[1]);
            preparedStatement.setDouble(3, coeff[2]);
            preparedStatement.setDouble(4, coeff[3]);
            preparedStatement.setInt(5, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        logger.info("The coefficient if match successfully changed");
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

        logger.info("The match successfully created.");
        return match;
    }

    @Override
    public boolean checkTimeForBet(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String[] dateTime = {null, null};
        String dateNow;
        String timeNow;
        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(str);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 dateTime = resultSet.getString("datetime").replace(".", " ").split(" ");

            }
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("kk.mm.ss");

            dateNow = dateFormat.format(date);
            timeNow = timeFormat.format(date);

            if (dateTime[0].compareTo(dateNow) > 0) {
                return true;
            } else if (dateTime[0].compareTo(dateNow) == 0) {
                if (dateTime[1].compareTo(timeNow) > 0) {
                    return true;
                }
            }

            logger.info("The time of match successfully checked.");
            return false;
        } catch (SQLException e) {
            logger.error("Cannot connect the database!");
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
    }
}
