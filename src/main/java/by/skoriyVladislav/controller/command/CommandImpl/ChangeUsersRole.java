package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.exception.DAOException;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeUsersRole implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String role = request.getParameter("status");

        try {
            if (ServiceFactory.getInstance().getUserService().changeRole(login, role)) {

                response.sendRedirect("controller?command=go_to_user_profile&login=" + login);
            } else {

                response.sendRedirect("error.jsp");
            }
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }
    }
}
