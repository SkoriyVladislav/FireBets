package by.skoriyVladislav.service;

import by.skoriyVladislav.service.bet_service.BetService;
import by.skoriyVladislav.service.bet_service.impl.BetServiceImpl;
import by.skoriyVladislav.service.match_service.MatchService;
import by.skoriyVladislav.service.match_service.impl.MatchServiceImpl;
import by.skoriyVladislav.service.user_service.UserService;
import by.skoriyVladislav.service.user_service.impl.UserServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();

    private final MatchService matchService = new MatchServiceImpl();

    private final BetService betService = new BetServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public MatchService getMatchService() {
        return matchService;
    }

    public BetService getBetService() {
        return betService;
    }
}
