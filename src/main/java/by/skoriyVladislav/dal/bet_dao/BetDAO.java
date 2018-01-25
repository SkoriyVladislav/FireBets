package by.skoriyVladislav.dal.bet_dao;

import by.skoriyVladislav.entity.bets.Bet;

import java.sql.SQLException;

public interface BetDAO {
    boolean registrationBet(Bet bet) throws SQLException;
}
