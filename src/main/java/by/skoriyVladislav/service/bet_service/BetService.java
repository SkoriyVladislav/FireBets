package by.skoriyVladislav.service.bet_service;

import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.exception.ServiceException;

import java.util.List;

public interface BetService {
    List<Bet> getBets(String userLogin) throws ServiceException;
    Bet getBet(String userLogin, int idMatch) throws ServiceException;
    boolean registrationBet(Bet bet, User user) throws ServiceException;
    boolean deleteBet(User user, Bet bet) throws ServiceException;
}
