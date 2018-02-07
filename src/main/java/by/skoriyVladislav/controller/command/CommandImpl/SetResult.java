package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetResult implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int matchId1 = -1;

        try {
            matchId1 = Integer.valueOf(request.getParameter("matchId"));
            int goalsTeam1 = Integer.valueOf(request.getParameter("goalsTeam1"));
            int goalsTeam2 = Integer.valueOf(request.getParameter("goalsTeam2"));

            ServiceFactory.getInstance().getMatchService().setResult(matchId1, goalsTeam1, goalsTeam2);
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        } catch (NullPointerException ex) {
            request.setAttribute("error", ex);
            response.sendRedirect("error.jsp");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/make_bet.jsp").forward(request, response);
    }
}
