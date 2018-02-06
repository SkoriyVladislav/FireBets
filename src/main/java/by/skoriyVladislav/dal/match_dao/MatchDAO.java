package by.skoriyVladislav.dal.match_dao;

import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.match.Match;

import java.util.List;

public interface MatchDAO {
    List<Match> getMatches() throws DAOException;
    Match getMatch(int id) throws DAOException;
    boolean registrationMatch(String team1, String team2, String dataTime, double[] coeff) throws DAOException;
    boolean changeCoefficients(int id, double[] coeff) throws DAOException;
    boolean setResult(int id, int goalsTeam1, int goalsTeam2) throws DAOException;
    boolean checkTimeForBet(int id) throws DAOException;
}
