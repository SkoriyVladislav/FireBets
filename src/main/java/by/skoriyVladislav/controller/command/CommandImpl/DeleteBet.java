package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.entity.bet.Bet;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBet implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Bet bet = (Bet) request.getSession().getAttribute("bet");

        try {
            if (ServiceFactory.getInstance().getBetService().deleteBet(user, bet)) {
                request.getSession().setAttribute("user", user);
                request.setAttribute("bet", null);
                request.getSession().setAttribute("bet", null);
                request.getRequestDispatcher("/controller?command=go_to_make_bet&match=" + bet.getIdMatches()).forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }
    }
}
