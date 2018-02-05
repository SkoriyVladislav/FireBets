package by.skoriyVladislav.dal.user_dao;

import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDAO {
    List<User> getUsers(String criteria) throws DAOException;
    User getUser(String login, String pass) throws DAOException;
    User getUser(String login) throws DAOException;
    boolean registerUser(String login, String password, String name,
                         String surname, String role, BigDecimal balance, String email) throws DAOException;
    boolean loginInDataBase(String login) throws DAOException;
    boolean checkBalanceForBet(String login, BigDecimal size) throws DAOException;
    boolean transaktion(User user, BigDecimal size) throws DAOException;
    boolean transaktion(String login, BigDecimal size) throws DAOException;
    boolean changeRole(String login, String role) throws DAOException;
}
