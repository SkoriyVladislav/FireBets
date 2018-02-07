package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPageUserProfile implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        User user = null;
        try {
            user = ServiceFactory.getInstance().getUserService().getUser(login);
        } catch (ServiceException ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/jsp/user_profile.jsp").forward(request, response);
    }

}
