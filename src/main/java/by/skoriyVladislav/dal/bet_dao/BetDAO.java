package by.skoriyVladislav.dal.bet_dao;

import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface BetDAO {
    List<Bet> createBet(String userLogin);
    Bet createBet(String userLogin, int idMatch);
    boolean registrationBet(Bet bet, User user) throws SQLException;
    boolean deleteBet(User user, Bet bet );
}
