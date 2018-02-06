package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeMatch implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String team1 = request.getParameter("team1");
        String team2 = request.getParameter("team2");

        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String hour = request.getParameter("hour");
        String minute = request.getParameter("minute");

        double coefTeam1 = 0;
        double coefTeam2 = 0;
        double coefDraw = 0;
        double coefExAcc = 0;

        try {
            coefTeam1 = Double.valueOf(request.getParameter("coefTeam1"));
            coefTeam2 = Double.valueOf(request.getParameter("coefTeam2"));
            coefDraw = Double.valueOf(request.getParameter("coefDraw"));
            coefExAcc = Double.valueOf(request.getParameter("coefExAcc"));

        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            response.sendRedirect("error2.jsp");
        }

        String dataTime = year + "-" + month + "-" + day + " " + hour + ":" + minute;
        double[] coef = {coefTeam1, coefTeam2, coefDraw, coefExAcc};

        try {
            ServiceFactory.getInstance().getMatchService().registrationMatch(team1, team2, dataTime, coef);
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
    }
}

