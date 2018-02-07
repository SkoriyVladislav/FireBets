package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeCoeff implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double coefTeam11 = 0;
        double coefTeam21 = 0;
        double coefDraw1 = 0;
        double coefExAcc1 = 0;
        int idMatch = -1;
        try {
            coefTeam11 = Double.valueOf(request.getParameter("coefTeam1"));
            coefTeam21 = Double.valueOf(request.getParameter("coefTeam2"));
            coefDraw1 = Double.valueOf(request.getParameter("coefDraw"));
            coefExAcc1 = Double.valueOf(request.getParameter("coefExAcc"));
            idMatch = Integer.valueOf(request.getParameter("matchId"));
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            response.sendRedirect("error.jsp");
        }
        double[] coeff = {coefTeam11, coefTeam21, coefDraw1, coefExAcc1};


        try {
            ServiceFactory.getInstance().getMatchService().changeCoefficients(idMatch, coeff);
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }

        request.getRequestDispatcher("/controller?command=go_to_make_bet&match=" + idMatch).forward(request, response);
    }
}
