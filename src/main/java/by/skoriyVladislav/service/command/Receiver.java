package by.skoriyVladislav.service.command;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.entity.bets.Bet;
import by.skoriyVladislav.entity.bets.BetType;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.command.command_type.TypeCommand;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

public class Receiver {
    public void action(TypeCommand cmd, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(cmd){
            case GO_TO_MAIN:
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

            case GO_TO_MAKE_BET:
                DAOFactory daoFactory3 = DAOFactory.getInstance();
                MatchDAO matchDAO3 = daoFactory3.getMatchDAO();
                Match match = matchDAO3.createMatch(Integer.parseInt(request.getParameter("match")));
                request.getSession().setAttribute("match", match);
                request.getRequestDispatcher("/WEB-INF/jsp/make_bet.jsp").forward(request, response);
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

            case REGISTRATION:
                String login = request.getParameter("login");
                String password = request.getParameter("pwd1");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");

                DAOFactory factory = DAOFactory.getInstance();
                UserDAO userDAO = factory.getUserDAO();
                userDAO.registerUser(login, password, name, surname, "user", 0.0, email);

                User user = userDAO.createUser(login, password);
                request.getSession().setAttribute("user", user);
                response.sendRedirect("index.jsp");
                break;

            case MAKE_BET:
                int matchId = ((Match)request.getSession().getAttribute("match")).getId();
                String userLogin = ((User)request.getSession().getAttribute("user")).getLogin();
                BetType typeOfBet = BetType.valueOf(request.getParameter("betType").toUpperCase());
                Integer size = Integer.valueOf(request.getParameter("betVal"));
                Integer goalsTeam1 = null;
                Integer goalsTeam2 = null;
                if (typeOfBet == BetType.EXACC) {
                    goalsTeam1 = Integer.valueOf(request.getParameter("exAccVal1"));
                    goalsTeam2 = Integer.valueOf(request.getParameter("exAccVal2"));
                }
                Bet bet = new Bet(userLogin, matchId, size, typeOfBet, goalsTeam1, goalsTeam2);

                DAOFactory factory2 = DAOFactory.getInstance();
                BetDAO betDAO = factory2.getBetDAO();
                try {
                    betDAO.registrationBet(bet);
                } catch (SQLException ex) {
                    throw new IOException(ex);
                }
                response.sendRedirect("index.jsp");
                break;

            case LOGIN:
                String login1 = request.getParameter("login");
                String password1 = request.getParameter("password");

                DAOFactory factory1 = DAOFactory.getInstance();
                UserDAO userDAO1 = factory1.getUserDAO();
                User user1 = userDAO1.createUser(login1, password1);
                request.getSession().setAttribute("user", user1);

                try {
                    String from1 = request.getParameter("from");
                    response.sendRedirect(from1);
                } catch (NullPointerException ex) {
                    response.sendRedirect("index.jsp");
                }
                break;

            case LOGOUT:
                request.getSession().setAttribute("user", null);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case CHECK_LOGIN_AJAX:
                response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
                response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
                try (PrintWriter out = response.getWriter()) {
                    JSONObject jsonEnt = new JSONObject();
                    if(DAOFactory.getInstance().getUserDAO().loginInDataBase(request.getParameter("login"))) {
                        jsonEnt.put("serverInfo", "true");
                    }else {
                        jsonEnt.put("serverInfo", "false");
                    }
                    out.print(jsonEnt.toString());
                }
                break;
        }
    }
}
