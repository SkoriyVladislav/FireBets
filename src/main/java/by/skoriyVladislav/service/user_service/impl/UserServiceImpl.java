package by.skoriyVladislav.service.user_service.impl;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;
import by.skoriyVladislav.service.user_service.UserService;
import com.sun.xml.internal.ws.server.ServerRtException;

import java.math.BigDecimal;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUsers(String criteria) throws ServiceException {
        try {
            return DAOFactory.getInstance().getUserDAO().getUsers(criteria);
        } catch (DAOException ex) {
            throw new ServerRtException(ex);
        }
    }

    @Override
    public User getUser(String login, String pass) throws ServiceException {
        if (ServiceFactory.isValidSting(login) && ServiceFactory.isValidSting(pass)) {
            try {
                return DAOFactory.getInstance().getUserDAO().getUser(login, pass);
            } catch (DAOException ex) {
                throw new ServerRtException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public User getUser(String login) throws ServiceException {
        if (ServiceFactory.isValidSting(login)) {
            try {
                return DAOFactory.getInstance().getUserDAO().getUser(login);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean registerUser(String login, String password, String name, String surname, String role, BigDecimal balance, String email) throws ServiceException {
        if (ServiceFactory.isValidSting(login) && ServiceFactory.isValidSting(password) && ServiceFactory.isValidSting(name) &&
                ServiceFactory.isValidSting(surname) && ServiceFactory.isValidSting(role) && ServiceFactory.isValidSting(email) &&
                    balance.compareTo(BigDecimal.ZERO) >= 0) {

            try {
                DAOFactory.getInstance().getUserDAO().registerUser(login, password, name, surname, role, balance, email);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
        return true;
    }

    @Override
    public boolean loginInDataBase(String login) throws ServiceException {
        if (ServiceFactory.isValidSting(login)) {
            try {
                return DAOFactory.getInstance().getUserDAO().loginInDataBase(login);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean checkBalanceForBet(String login, BigDecimal size) throws ServiceException {
        if (ServiceFactory.isValidSting(login) && size.compareTo(BigDecimal.ZERO) >= 0) {
            try {
                return DAOFactory.getInstance().getUserDAO().checkBalanceForBet(login, size);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }

    @Override
    public boolean changeRole(String login, String role) throws ServiceException {
        if (ServiceFactory.isValidSting(login) && ServiceFactory.isValidSting(login)) {
                try {
                DAOFactory.getInstance().getUserDAO().changeRole(login, role);
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            return true;
        } else {
            throw new ServiceException("Invalid entered parameters.");
        }
    }
}