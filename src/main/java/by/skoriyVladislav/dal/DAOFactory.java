package by.skoriyVladislav.dal;

import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.dal.user_dao.impl.UserDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();

    private DAOFactory() {}

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
