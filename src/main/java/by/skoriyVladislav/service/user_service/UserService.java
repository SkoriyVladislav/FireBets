package by.skoriyVladislav.service.user_service;

import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    List<User> getUsers(String criteria) throws ServiceException;
    User getUser(String login, String pass) throws ServiceException;
    User getUser(String login) throws ServiceException;
    boolean registerUser(String login, String password, String name,
                         String surname, String role, BigDecimal balance, String email) throws ServiceException;
    boolean loginInDataBase(String login) throws ServiceException;
    boolean checkBalanceForBet(String login, BigDecimal size) throws ServiceException;
    boolean changeRole(String login, String role) throws ServiceException;
}
