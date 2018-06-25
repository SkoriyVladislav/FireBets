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
        double coefTeam1 = 0;
        double coefTeam2 = 0;
        double coefDraw = 0;
        double coefExAcc = 0;
        int idMatch = -1;
        try {
            coefTeam1 = Double.valueOf(request.getParameter("CoefTEAM1"));
            coefTeam2 = Double.valueOf(request.getParameter("CoefTEAM2"));
            coefDraw = Double.valueOf(request.getParameter("CoefDRAW"));
            coefExAcc = Double.valueOf(request.getParameter("CoefExAcc"));
            idMatch = Integer.valueOf(request.getParameter("matchId"));
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            response.sendRedirect("error.jsp");
        }
        double[] coeff = {coefTeam1, coefTeam2, coefDraw, coefExAcc};


        try {
            ServiceFactory.getInstance().getMatchService().changeCoefficients(idMatch, coeff);
        } catch (ServiceException ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/controller?command=go_to_make_bet&match=" + idMatch).forward(request, response);
    }
}
