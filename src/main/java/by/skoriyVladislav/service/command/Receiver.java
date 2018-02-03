package by.skoriyVladislav.service.command;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.bet.BetType;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.command.command_type.TypeCommand;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receiver {
    public void action(TypeCommand cmd, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(cmd) {
            case GO_TO_MAIN:
                request.setCharacterEncoding("utf-8");
                DAOFactory daoFactory = DAOFactory.getInstance();
                MatchDAO matchDAO = daoFactory.getMatchDAO();
                List<Match> matches = matchDAO.createMatches();
                request.getSession().setAttribute("matches", matches);
                response.sendRedirect("main.jsp");
                break;

            case GO_TO_PROFILE:
                request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
                break;

            case GO_TO_REGISTRATION:
                request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
                break;

            case GO_TO_USER_MANAGEMENT:
                request.getRequestDispatcher("/WEB-INF/jsp/user_management.jsp").forward(request, response);
                break;

            case GO_TO_MAKE_BET:
                Match match = DAOFactory.getInstance().getMatchDAO().createMatch(Integer.parseInt(request.getParameter("match")));
                User user6 = (User) request.getSession().getAttribute("user");
                Bet bet1 = DAOFactory.getInstance().getBetDAO().createBet(user6.getLogin(), match.getId());
                request.getSession().setAttribute("bet", bet1);
                request.setAttribute("bet", bet1);
                request.getSession().setAttribute("match", match);
                request.getRequestDispatcher("/WEB-INF/jsp/make_bet.jsp").forward(request, response);
                break;

            case GO_TO_MY_BETS:
                User user1 = (User) request.getSession().getAttribute("user");
                List<Bet> bets = DAOFactory.getInstance().getBetDAO().createBet(user1.getLogin());

                List<Match> matches1 = new ArrayList<>();
                for (Bet bet : bets) {
                    matches1.add(DAOFactory.getInstance().getMatchDAO().createMatch(bet.getIdMatches()));
                }

                Map<Match, Bet> matchBetMap = new HashMap<>();
                for (int i = 0; i < bets.size(); i++) {
                    matchBetMap.put(matches1.get(i), bets.get(i));
                }

                request.getSession().setAttribute("matchBetMap", matchBetMap);
                request.getRequestDispatcher("/WEB-INF/jsp/my_bets.jsp").forward(request, response);
                break;

            case GO_TO_LOGIN:
                String nfrom = URLEncoder.encode(request.getRequestURI(), "UTF-8");
                String from = nfrom.substring(3);
                if (request.getQueryString() != null) {
                    from += "?" + request.getQueryString();
                }

                from = from.replace("&", "%26");
                response.sendRedirect("login.jsp?from=" + from);
                break;

            case GO_TO_USER_PROFILE:
                String login4 = request.getParameter("login");
                User user4 = DAOFactory.getInstance().getUserDAO().createUser(login4);
                request.setAttribute("user", user4);
                request.getRequestDispatcher("/WEB-INF/jsp/user_profile.jsp").forward(request, response);
                break;

            case REGISTRATION:
                String login = request.getParameter("login");
                String password = request.getParameter("pwd1");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");

                DAOFactory factory = DAOFactory.getInstance();
                UserDAO userDAO = factory.getUserDAO();
                userDAO.registerUser(login, password, name, surname, "player", BigDecimal.TEN, email);

                User user = userDAO.createUser(login, password);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("index.jsp");
                break;

            case MAKE_BET:
                User user5 = (User) request.getSession().getAttribute("user");
                String userLogin = user5.getLogin();
                BigDecimal size = BigDecimal.valueOf(Double.valueOf(request.getParameter("betVal")));

                if (DAOFactory.getInstance().getUserDAO().checkBalanceForBet(userLogin, size)) {
                    int matchId = ((Match) request.getSession().getAttribute("match")).getId();
                    BetType typeOfBet = BetType.valueOf(request.getParameter("betType").toUpperCase());
                    Integer goalsTeam1 = null;
                    Integer goalsTeam2 = null;
                    if (typeOfBet == BetType.EXACTSCORE) {
                        goalsTeam1 = Integer.valueOf(request.getParameter("exAccVal1"));
                        goalsTeam2 = Integer.valueOf(request.getParameter("exAccVal2"));
                    }
                    Bet bet = new Bet(userLogin, matchId, size, typeOfBet, goalsTeam1, goalsTeam2, null);

                    try {
                        DAOFactory.getInstance().getBetDAO().registrationBet(bet, user5);
                    } catch (SQLException ex) {
                        throw new IOException(ex);
                    }
                    request.getSession().setAttribute("user", user5);
                    request.getSession().setAttribute("bet", bet);
                    request.setAttribute("bet", bet);
                    request.getRequestDispatcher("/WEB-INF/jsp/make_bet.jsp").forward(request, response);
                } else {
                    response.sendRedirect("error.jsp");
                }
                break;

            case DELETE_BET:
                User user7 = (User) request.getSession().getAttribute("user");
                Bet bet = (Bet) request.getSession().getAttribute("bet");

                try {
                    if (DAOFactory.getInstance().getBetDAO().deleteBet(user7, bet)){
                        request.getSession().setAttribute("user", user7);
                        request.setAttribute("bet", null);
                        request.getSession().setAttribute("bet", null);
                        request.getRequestDispatcher("/controller?command=go_to_make_bet&match=" + bet.getIdMatches()).forward(request, response);
                    } else {
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }

                } catch (IllegalArgumentException ex) {
                    throw new IOException(ex);
                }
                break;

            case CHANGE_USERS_ROLE:
                String login2 = request.getParameter("login");
                String role = request.getParameter("status");
                if (DAOFactory.getInstance().getUserDAO().changeRole(login2, role)) {
                    response.sendRedirect("controller?command=go_to_user_profile&login=" + login2);
                } else {
                    response.sendRedirect("error.jsp");
                }
                break;

            case MAKE_MATCH:
                String team1 = request.getParameter("team1");
                String team2 = request.getParameter("team2");

                String year = request.getParameter("year");
                String month = request.getParameter("month");
                String day = request.getParameter("day");
                String hour = request.getParameter("hour");
                String minute = request.getParameter("minute");

                double coefTeam1 = 0;
                double coefTeam2 = 0;
                double coefDraw = 0;
                double coefExAcc = 0;

                if (validateString(team1, team2, year, month, day, hour, minute)) {
                    try {
                        coefTeam1 = Double.valueOf(request.getParameter("coefTeam1"));
                        coefTeam2 = Double.valueOf(request.getParameter("coefTeam2"));
                        coefDraw = Double.valueOf(request.getParameter("coefDraw"));
                        coefExAcc = Double.valueOf(request.getParameter("coefExAcc"));
                    } catch (NullPointerException ex) {
                        System.out.println(ex.getMessage());
                        response.sendRedirect("error.jsp");
                    }

                    String dataTime = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                    double[] coef = {coefTeam1, coefTeam2, coefDraw, coefExAcc};

                    DAOFactory.getInstance().getMatchDAO().registrMatch(team1, team2, dataTime, coef);

                    request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
                } else {
                    response.sendRedirect("error.jsp");
                }


                break;

            case LOGIN:
                String login1 = request.getParameter("login");
                String password1 = request.getParameter("password");

                DAOFactory factory1 = DAOFactory.getInstance();
                UserDAO userDAO1 = factory1.getUserDAO();
                User user2 = userDAO1.createUser(login1, password1);
                request.getSession().setAttribute("user", user2);

                try {
                    String from1 = request.getParameter("from");
                    response.sendRedirect(from1);
                } catch (NullPointerException ex) {
                    response.sendRedirect("index.jsp");
                }
                break;

            case LOGOUT:
                request.setCharacterEncoding("utf-8");
                request.getSession().setAttribute("user", null);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case CHECK_LOGIN_AJAX:
                response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
                response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
                try (PrintWriter out = response.getWriter()) {
                    JSONObject jsonEnt = new JSONObject();
                    if (DAOFactory.getInstance().getUserDAO().loginInDataBase(request.getParameter("login"))) {
                        jsonEnt.put("serverInfo", "true");
                    } else {
                        jsonEnt.put("serverInfo", "false");
                    }
                    out.print(jsonEnt.toString());
                }
                break;

            case CHECK_SIZE_BETS_AJAX:
                response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
                response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
                User user3 = (User) request.getSession().getAttribute("user");
                BigDecimal betValue = BigDecimal.valueOf(Double.valueOf(request.getParameter("betVal")));
                try (PrintWriter out = response.getWriter()) {
                    JSONObject jsonEnt = new JSONObject();
                    if (DAOFactory.getInstance().getUserDAO().checkBalanceForBet(user3.getLogin(), betValue)) {
                        jsonEnt.put("serverInfo", "true");
                    } else {
                        jsonEnt.put("serverInfo", "false");
                    }
                    out.print(jsonEnt.toString());
                }
                break;

            case GET_USERS_AJAX:
                response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
                response.setCharacterEncoding("UTF-8");//Кодировка отправляемых данных

                String criteria = request.getParameter("criteria");
                List<User> products = DAOFactory.getInstance().getUserDAO().createUsers(criteria);
                String json = new Gson().toJson(products);
                response.getWriter().write(json);
                break;
        }
    }

    private boolean validateString(String... agrs) {
        for (String str : agrs) {
            if (str == null) {
                return false;
            }
        }
        return true;
    }
}
