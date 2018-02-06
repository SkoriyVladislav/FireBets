package by.skoriyVladislav.service.match_service;

import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.service.exception.ServiceException;

import java.util.List;

public interface MatchService {
    List<Match> getMatches() throws ServiceException;
    Match getMatch(int id) throws ServiceException;
    boolean registrationMatch(String team1, String team2, String dataTime, double[] coeff) throws ServiceException;
    boolean changeCoefficients(int id, double[] coeff) throws ServiceException;
    boolean setResult(int id, int goalsTeam1, int goalsTeam2) throws ServiceException;
    boolean checkTimeForBet(int id) throws ServiceException;
}
