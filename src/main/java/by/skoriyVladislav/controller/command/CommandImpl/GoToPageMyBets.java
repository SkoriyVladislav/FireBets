package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoToPageMyBets implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user1 = (User) request.getSession().getAttribute("user");
        List<Bet> bets = null;

        try {
            bets = ServiceFactory.getInstance().getBetService().getBets(user1.getLogin());
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }

        List<Match> matches1 = new ArrayList<>();
        try {
            for (Bet bet : bets) {
                matches1.add(ServiceFactory.getInstance().getMatchService().getMatch(bet.getIdMatches()));
            }
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }

        Map<Match, Bet> matchBetMap = new HashMap<>();
        for (int i = 0; i < bets.size(); i++) {
            matchBetMap.put(matches1.get(i), bets.get(i));
        }

        request.getSession().setAttribute("matchBetMap", matchBetMap);
        request.getRequestDispatcher("/WEB-INF/jsp/my_bets.jsp").forward(request, response);
    }
}