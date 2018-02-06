package by.skoriyVladislav.service.match_service.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.service.exception.ServiceException;
import by.skoriyVladislav.service.match_service.MatchService;
import java.util.List;

public class MatchServiceImpl implements MatchService {
    @Override
    public List<Match> getMatches() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MatchDAO matchDAO = daoFactory.getMatchDAO();
        try {
            return matchDAO.getMatches();
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public Match getMatch(int id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getMatchDAO().getMatch(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean registrationMatch(String team1, String team2, String dataTime, double[] coeff) throws ServiceException {
        try {
            DAOFactory.getInstance().getMatchDAO().registrationMatch(team1, team2, dataTime, coeff);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return true;
    }

    @Override
    public boolean changeCoefficients(int id, double[] coeff) throws ServiceException {
        try {
            DAOFactory.getInstance().getMatchDAO().changeCoefficients(id, coeff);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return true;
    }

    @Override
    public boolean setResult(int id, int goalsTeam1, int goalsTeam2) throws ServiceException {
        try {
            DAOFactory.getInstance().getMatchDAO().setResult(id, goalsTeam1, goalsTeam2);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
        return true;
    }

    @Override
    public boolean checkTimeForBet(int id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getMatchDAO().checkTimeForBet(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }
}
