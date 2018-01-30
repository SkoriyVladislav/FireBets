package by.skoriyVladislav.dal.bet_dao;

import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface BetDAO {
    List<Bet> createBet(String userLogin);

    boolean registrationBet(Bet bet, User user) throws SQLException;
}
