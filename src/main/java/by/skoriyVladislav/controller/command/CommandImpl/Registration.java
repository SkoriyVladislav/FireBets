package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.constants.Constants;
import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class Registration implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("pwd1");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        User user = null;
        try {
            ServiceFactory.getInstance().getUserService().registerUser(login, password, name, surname, Constants.INITIALROLE, BigDecimal.valueOf((Constants.INITIALBALANCE)), email);
            user = ServiceFactory.getInstance().getUserService().getUser(login, password);
        }catch (ServiceException ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect("index.jsp");
    }
}
