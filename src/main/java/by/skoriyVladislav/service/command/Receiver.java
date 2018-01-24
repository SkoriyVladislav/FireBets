package by.skoriyVladislav.service.command;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.bet_dao.BetDAO;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.command.command_type.TypeCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;

public class Receiver {
    public void action(TypeCommand cmd, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(cmd){
            case GO_TO_MAIN:
                DAOFactory daoFactory = DAOFactory.getInstance();
                MatchDAO matchDAO = daoFactory.getMatchDAO();
                List<Match> matches = matchDAO.createMatches();
                request.getSession().setAttribute("matches", matches);
                request.getRequestDispatcher("main.jsp").forward(request, response);
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
                //request.getRequestDispatcher("/WEB-INF/jsp/login.jsp?from=" + from).forward(request, response);
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
                int matchId = Integer.getInteger(request.getParameter("match"));
                String userLogin = ((User)request.getSession().getAttribute("user")).getLogin();
                int size = Integer.getInteger(request.getParameter("size"));
                String typeOfBet = request.getParameter("type");

                DAOFactory factory2 = DAOFactory.getInstance();
                BetDAO betDAO = factory2.getBetDAO();
                betDAO.registrationBet(userLogin, matchId, size, typeOfBet);
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
                    //String from1 = ServiceFactory.getInstance().getLastCommand();
                } catch (NullPointerException ex) {
                    response.sendRedirect("index.jsp");
                }
                //request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case LOGOUT:
                request.getSession().setAttribute("user", null);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }
}
