package by.skoriyVladislav.dal.bet_dao.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.bet.BetType;
import by.skoriyVladislav.entity.user.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BetDAOImpl implements BetDAO {
    private final static String URL = "jdbc:mysql://localhost:3306/firebets"+
            "?verifyServerCertificate=false"+
            "&useSSL=false"+
            "&requireSSL=false"+
            "&useLegacyDatetimeCode=false"+
            "&amp"+
            "&serverTimezone=UTC";

    private final static String INSERT_BETS = "INSERT INTO bets (Users_Login, Matches_idMatches, Size, Type, goalsTeam1, goalsTeam2) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SELECT_FROM_BETS_WHERE_USERS_LOGIN = "SELECT * FROM bets WHERE Users_Login = ?";
    private final static String SELECT_FROM_BETS_WHERE_USERS_LOGIN_AND_MATCHES_ID_MATCHES = "SELECT * FROM bets WHERE Users_Login = ? AND Matches_idMatches = ?";
    private final static String DELETE_FROM_BETS_WHERE_USERS_LOGIN_AND_MATCHES_ID_MATCHES = "DELETE FROM bets WHERE Users_login = ? AND Matches_idMatches = ?";
    private final static String UPDATE_BETS_SET_STATUS_WHERE_MATCHES_ID_MATCHES = "UPDATE bets SET status = ? WHERE Matches_idMatches = ?";
    private final static String SELECT_FROM_BETS_WHERE_MATCHES_ID_MATCHES = "SELECT * FROM matches LEFT JOIN bets ON matches.idMatches = bets.Matches_idMatches LEFT JOIN coefficient ON matches.idMatches = coefficient.Matches_idMatchs WHERE idMatches = ?";

    @Override
    public boolean registrationBet(Bet bet, User user) throws DAOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_BETS);

            preparedStatement.setString(1, bet.getLoginUser());
            preparedStatement.setInt(2, bet.getIdMatches());
            preparedStatement.setBigDecimal(3, bet.getSize());
            preparedStatement.setString(4, bet.getType().getType());
            preparedStatement.setObject(5, bet.getGoalsTeam1());
            preparedStatement.setObject(6, bet.getGoalsTeam2());

            if (DAOFactory.getInstance().getUserDAO().transaktion(user, bet.getSize().negate())) {
                preparedStatement.execute();
            } else {
                throw new DAOException();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return true;
    }

    @Override
    public boolean setResult(int idMatches, int goalsTeam1, int goalsTeam2) throws DAOException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_BETS_WHERE_MATCHES_ID_MATCHES);

            preparedStatement.setInt(1, idMatches);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Connection connection2 = null;
                PreparedStatement preparedStatement2 = null;
                String status = null;
                BetType betType = BetType.valueOf(resultSet.getString("Type").toUpperCase());
                BigDecimal sizeTranz = BigDecimal.ZERO;

                if (betType == BetType.TEAM1) {
                    status = (goalsTeam1 - goalsTeam2) > 0 ? "win" : "lose";
                    sizeTranz = resultSet.getBigDecimal("Size").multiply(new BigDecimal(resultSet.getDouble("CoefTEAM1")));
                } else if (betType == BetType.TEAM2) {
                    status = (goalsTeam1 - goalsTeam2) < 0 ? "win" : "lose";
                    sizeTranz = resultSet.getBigDecimal("Size").multiply(new BigDecimal(resultSet.getDouble("CoefTEAM2")));
                } else if (betType == BetType.DRAW) {
                    status = (goalsTeam1 - goalsTeam2) == 0 ? "win" : "lose";
                    sizeTranz = resultSet.getBigDecimal("Size").multiply(new BigDecimal(resultSet.getDouble("CoefDRAW")));
                } else {
                    if (resultSet.getString("bets.goalsTeam1").equals(resultSet.getString("matches.goalsTeam1")) && resultSet.getString("bets.goalsTeam2").equals(resultSet.getString("matches.goalsTeam2"))) {
                        status = "win";
                        sizeTranz = resultSet.getBigDecimal("Size").multiply(new BigDecimal(resultSet.getDouble("CoefExAcc")));
                    } else {
                        status = "lose";
                    }
                }

                try {
                    connection2 = DAOFactory.getInstance().getConnectionPool().getConnection();
                    preparedStatement2 = connection2.prepareStatement(UPDATE_BETS_SET_STATUS_WHERE_MATCHES_ID_MATCHES);

                    if (DAOFactory.getInstance().getUserDAO().transaktion(resultSet.getString("Users_Login"), sizeTranz)) {

                        preparedStatement2.setString(1, status);
                        preparedStatement2.setInt(2, idMatches);
                        preparedStatement2.execute();
                    } else {
                        throw new DAOException();
                    }
                } catch (SQLException e) {
                    throw new DAOException("Cannot connect the database!", e);
                } finally {
                    DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement);
                }



            }

        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public List<Bet> getBet(String userLogin) throws DAOException{
        List<Bet> bets = new ArrayList<>();
        Bet bet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_BETS_WHERE_USERS_LOGIN);
            preparedStatement.setString(1 , userLogin);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bet = getBet(resultSet);
                bets.add(bet);
            }

        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }
        bets.sort(Comparator.comparing(Bet::getIdMatches).reversed());
        return bets;
    }

    private Bet getBet(ResultSet resultSet) throws SQLException {
        Bet bet = null;

        String loginUser = resultSet.getString("Users_Login");
        int idMatches = resultSet.getInt("Matches_idMatches");
        BigDecimal size = resultSet.getBigDecimal("Size");
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


    @Override
    public Bet getBet(String userLogin, int idMatch) throws DAOException{
        Bet bet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_FROM_BETS_WHERE_USERS_LOGIN_AND_MATCHES_ID_MATCHES);
            preparedStatement.setString(1 , userLogin);
            preparedStatement.setInt(2 , idMatch);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bet = getBet(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return bet;
    }

    @Override
    public boolean deleteBet(User user, Bet bet) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DAOFactory.getInstance().getConnectionPool().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FROM_BETS_WHERE_USERS_LOGIN_AND_MATCHES_ID_MATCHES);
            preparedStatement.setString(1 , user.getLogin());
            preparedStatement.setInt(2 , bet.getIdMatches());

            if (DAOFactory.getInstance().getUserDAO().transaktion(user, bet.getSize())) {
                preparedStatement.execute();
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot connect the database!", e);
        } finally {
            DAOFactory.getInstance().getConnectionPool().close(connection, preparedStatement, resultSet);
        }

        return true;
    }
}
