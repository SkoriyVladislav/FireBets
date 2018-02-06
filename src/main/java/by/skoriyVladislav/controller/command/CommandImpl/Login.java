package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements ICommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = ServiceFactory.getInstance().getUserService().getUser(login, password);
            if (user == null) {
                request.setAttribute("errorMsg", "Wrong login or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }
        request.getSession().setAttribute("user", user);

        try {
            String from1 = request.getParameter("from");
            response.sendRedirect(from1);
        } catch (NullPointerException ex) {
            response.sendRedirect("index.jsp");
        }
    }
}
