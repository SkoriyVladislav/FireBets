package by.skoriyVladislav.service.match_service.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.service.ServiceFactory;
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

        if (ServiceFactory.isValidSting(team1) && ServiceFactory.isValidSting(team2) &&
                ServiceFactory.isValidSting(team2) && isValidateCoeff(coeff)) {
            try {
                DAOFactory.getInstance().getMatchDAO().registrationMatch(team1, team2, dataTime, coeff);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            return true;
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean changeCoefficients(int id, double[] coeff) throws ServiceException {
        if (ServiceFactory.isValidInteger(id) && isValidateCoeff(coeff)) {
            try {
                DAOFactory.getInstance().getMatchDAO().changeCoefficients(id, coeff);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            return true;
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean setResult(int id, int goalsTeam1, int goalsTeam2) throws ServiceException {
        if (ServiceFactory.isValidInteger(id) && ServiceFactory.isValidInteger(goalsTeam1) && ServiceFactory.isValidInteger(goalsTeam2)) {
            try {
                DAOFactory.getInstance().getMatchDAO().setResult(id, goalsTeam1, goalsTeam2);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            return true;
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean checkTimeForBet(int id) throws ServiceException {
        try {
            return DAOFactory.getInstance().getMatchDAO().checkTimeForBet(id);
        } catch (DAOException ex) {
            throw new ServiceException(ex);
        }
    }

    private boolean isValidateCoeff(double[] coeff) {
        return ServiceFactory.isValidDouble(coeff[0]) && ServiceFactory.isValidDouble(coeff[1]) &&
                ServiceFactory.isValidDouble(coeff[2]) && ServiceFactory.isValidDouble(coeff[3]);
    }
}
