package by.skoriyVladislav.service.command;

import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.dal.user_dao.UserDAO;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.command.command_type.TypeCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Receiver {
    public void action(TypeCommand cmd, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch(cmd){
            case GO_TO_INDEX:
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case GO_TO_PROFILE:
                request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);
                break;

            case GO_TO_REGISTRATION:
                request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(request, response);
                break;

            case REGISTRATION:
                String login = request.getParameter("login");
                String password = request.getParameter("pwd1");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");

                DAOFactory factory = DAOFactory.getInstance();
                UserDAO userDAO= factory.getUserDAO();
                userDAO.registerUser(login, password, name, surname, "user", 0.0, email);
                break;

            case LOGIN:
                String login1 = request.getParameter("login");
                String password1 = request.getParameter("password");

                DAOFactory factory1 = DAOFactory.getInstance();
                UserDAO userDAO1 = factory1.getUserDAO();
                User user = userDAO1.createUser(login1, password1);
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case LOGOUT:
                request.getSession().setAttribute("user", null);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
        }
    }
}
