package by.skoriyVladislav.dal;

import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.dal.bet_dao.impl.BetDAOImpl;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.dal.match_dao.impl.MatchDAOImpl;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.dal.user_dao.impl.UserDAOImpl;
import by.skoriyVladislav.entity.match.Match;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();

    private final MatchDAO matchDAO = new MatchDAOImpl();

    private final BetDAO betDAO = new BetDAOImpl();

    private DAOFactory() {}

    public MatchDAO getMatchDAO() {
        return matchDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public BetDAO getBetDAO() {
        return betDAO;
    }
}
