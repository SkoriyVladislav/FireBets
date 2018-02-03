package by.skoriyVladislav.dal.user_dao;

import by.skoriyVladislav.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDAO {
    List<User> createUsers(String criteria);
    User createUser(String login, String pass);
    User createUser(String login);
    boolean registerUser(String login, String password, String name,
                         String surname, String role, BigDecimal balance, String email);
    boolean loginInDataBase(String login);
    boolean checkBalanceForBet(String login, BigDecimal size);
    boolean transaktion(User user, BigDecimal size);
    boolean changeRole(String login, String role);
}
