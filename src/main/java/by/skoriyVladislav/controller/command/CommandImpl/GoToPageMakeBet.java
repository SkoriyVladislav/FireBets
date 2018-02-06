package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPageMakeBet implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchId = Integer.parseInt(request.getParameter("match"));
        Match match = null;

        try {
            match = ServiceFactory.getInstance().getMatchService().getMatch(matchId);
        } catch (ServiceException ex) {
            response.sendRedirect("error.jsp");
        }

        User user = (User) request.getSession().getAttribute("user");
        Bet bet = null;

        try {
            bet = ServiceFactory.getInstance().getBetService().getBet(user.getLogin(), matchId);
        }  catch (ServiceException ex) {
            response.sendRedirect("error.jsp");
        }

        request.getSession().setAttribute("bet", bet);
        request.setAttribute("bet", bet);
        request.getSession().setAttribute("match", match);
        request.getRequestDispatcher("/WEB-INF/jsp/make_bet.jsp").forward(request, response);
    }
}
