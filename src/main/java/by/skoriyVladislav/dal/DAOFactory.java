package by.skoriyVladislav.dal;

import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.dal.bet_dao.impl.BetDAOImpl;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.dal.match_dao.impl.MatchDAOImpl;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.dal.user_dao.impl.UserDAOImpl;
import by.skoriyVladislav.entity.match.Match;

import java.util.Properties;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();

    private final MatchDAO matchDAO = new MatchDAOImpl();

    private final BetDAO betDAO = new BetDAOImpl();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public MatchDAO getMatchDAO() {
        return matchDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public BetDAO getBetDAO() {
        return betDAO;
    }

    private final static Properties properties = new Properties();
    static {
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
    }

    public static Properties getProperties() {
        return properties;
    }
}
