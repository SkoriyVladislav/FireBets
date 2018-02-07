package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.match_dao.MatchDAO;
import by.skoriyVladislav.entity.match.Match;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoToPageMain implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Match> matches = ServiceFactory.getInstance().getMatchService().getMatches();
            request.getSession().setAttribute("matches", matches);
            response.sendRedirect("main.jsp");
        } catch (ServiceException ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
