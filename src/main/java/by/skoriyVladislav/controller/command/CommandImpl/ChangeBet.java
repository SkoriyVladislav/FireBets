package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.bet.BetType;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class ChangeBet implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Bet bet = (Bet) request.getSession().getAttribute("bet");
        try {
            if (ServiceFactory.getInstance().getBetService().deleteBet(user, bet)) {
                request.getSession().setAttribute("user", user);
                request.setAttribute("bet", null);
                request.getSession().setAttribute("bet", null);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }

        String userLogin = user.getLogin();
        BigDecimal size = BigDecimal.valueOf(Double.valueOf(request.getParameter("betVal")));

        try {

            if (ServiceFactory.getInstance().getUserService().checkBalanceForBet(userLogin, size)) {
                int matchId = ((Match) request.getSession().getAttribute("match")).getId();
                BetType typeOfBet = BetType.valueOf(request.getParameter("betType").toUpperCase());
                Integer goalsTeam1 = null;
                Integer goalsTeam2 = null;
                if (typeOfBet == BetType.EXACTSCORE) {
                    goalsTeam1 = Integer.valueOf(request.getParameter("exAccVal1"));
                    goalsTeam2 = Integer.valueOf(request.getParameter("exAccVal2"));
                }

                Bet bet1 = new Bet(userLogin, matchId, size, typeOfBet, goalsTeam1, goalsTeam2, null);
                ServiceFactory.getInstance().getBetService().registrationBet(bet1, user);

                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("bet", bet1);
                request.setAttribute("bet", bet1);
                request.getRequestDispatcher("/controller?command=go_to_make_bet&match=" + bet.getIdMatches()).forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        } catch (NullPointerException ex) {

            response.sendRedirect("error.jsp");
        }
    }
}