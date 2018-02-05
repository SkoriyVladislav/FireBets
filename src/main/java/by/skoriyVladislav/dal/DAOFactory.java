package by.skoriyVladislav.dal;

import by.skoriyVladislav.dal.connection_pool.ConnectionPool;
import by.skoriyVladislav.dal.connection_pool.impl.ConnectionPoolImpl;
import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.dal.bet_dao.impl.BetDAOImpl;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.dal.match_dao.impl.MatchDAOImpl;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.dal.user_dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();

    private final MatchDAO matchDAO = new MatchDAOImpl();

    private final BetDAO betDAO = new BetDAOImpl();

    private final ConnectionPool connectionPool = new ConnectionPoolImpl();

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

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }
}
