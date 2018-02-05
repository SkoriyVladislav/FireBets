package by.skoriyVladislav.dal.bet_dao;

import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface BetDAO {
    List<Bet> getBet(String userLogin) throws  DAOException;
    Bet getBet(String userLogin, int idMatch) throws DAOException;
    boolean registrationBet(Bet bet, User user) throws DAOException;
    boolean deleteBet(User user, Bet bet ) throws DAOException;
    boolean setResult(int idMatches, int goalsTeam1, int goalsTeam2) throws DAOException;
}
