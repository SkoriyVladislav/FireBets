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

public class MakeBet implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        String userLogin = user.getLogin();
        double tempDouble = -1;
        ;

        try {
            tempDouble = Double.valueOf(request.getParameter("betVal"));
            BigDecimal size = BigDecimal.valueOf(tempDouble);
            if (ServiceFactory.getInstance().getUserService().checkBalanceForBet(userLogin, size)) {

                int matchId = ((Match) request.getSession().getAttribute("match")).getId();
                BetType typeOfBet = BetType.valueOf(request.getParameter("betType").toUpperCase());
                Integer goalsTeam1 = null;
                Integer goalsTeam2 = null;
                if (typeOfBet == BetType.EXACTSCORE) {
                    goalsTeam1 = Integer.valueOf(request.getParameter("exAccVal1"));
                    goalsTeam2 = Integer.valueOf(request.getParameter("exAccVal2"));
                }

                Bet bet = new Bet(userLogin, matchId, size, typeOfBet, goalsTeam1, goalsTeam2, null);
                ServiceFactory.getInstance().getBetService().registrationBet(bet, user);

                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("bet", bet);
                request.setAttribute("bet", bet);
                request.getRequestDispatcher("/WEB-INF/jsp/make_bet.jsp").forward(request, response);
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
