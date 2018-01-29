package by.skoriyVladislav.dal.bet_dao;

import by.skoriyVladislav.entity.bets.Bet;

import java.sql.SQLException;
import java.util.List;

public interface BetDAO {
    List<Bet> createBet(String userLogin, String matchId);

    boolean registrationBet(Bet bet) throws SQLException;
}
