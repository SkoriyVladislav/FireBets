package by.skoriyVladislav.service.bet_service.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.bet_service.BetService;
import by.skoriyVladislav.service.exception.ServiceException;
import java.util.List;

public class BetServiceImpl implements BetService {
    @Override
    public List<Bet> getBets(String userLogin) throws ServiceException {
        if (ServiceFactory.isValidSting(userLogin)) {
            try {
                return DAOFactory.getInstance().getBetDAO().getBet(userLogin);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public Bet getBet(String userLogin, int idMatch) throws ServiceException {
        if (ServiceFactory.isValidSting(userLogin) && ServiceFactory.isValidInteger(idMatch)) {
            try {
                return DAOFactory.getInstance().getBetDAO().getBet(userLogin, idMatch);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean registrationBet(Bet bet, User user) throws ServiceException {
        if (ServiceFactory.isValidObj(bet) && ServiceFactory.isValidObj(user)) {
            try {
                return DAOFactory.getInstance().getBetDAO().registrationBet(bet, user);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean deleteBet(User user, Bet bet) throws ServiceException {
        if (ServiceFactory.isValidObj(bet) && ServiceFactory.isValidObj(user)) {
            try {
                return DAOFactory.getInstance().getBetDAO().deleteBet(user, bet);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }
}
