package by.skoriyVladislav.dal.user_dao;

import by.skoriyVladislav.entity.user.User;

import java.util.List;

public interface UserDAO {
    List<User> createUsers(String criteria);
    User createUser(String login, String pass);
    boolean registerUser(String login, String password, String name,
                         String surname, String role, double balance, String email);
    boolean loginInDataBase(String login);
    boolean checkBalanceForBet(String login, double size);
    boolean transaktion(User user, double size);
}
