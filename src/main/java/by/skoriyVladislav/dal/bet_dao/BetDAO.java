package by.skoriyVladislav.dal.bet_dao;

import by.skoriyVladislav.entity.bet.Bet;

import java.sql.SQLException;
import java.util.List;

public interface BetDAO {
    List<Bet> createBet(String userLogin);

    boolean registrationBet(Bet bet) throws SQLException;
}
