package by.skoriyVladislav.dal.bet_dao;

public interface BetDAO {
    boolean registrationBet(String login, int matchID, double size, String type);
}
