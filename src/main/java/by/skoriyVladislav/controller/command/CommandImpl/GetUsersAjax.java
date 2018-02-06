package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.entity.user.User;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUsersAjax implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
        response.setCharacterEncoding("UTF-8");//Кодировка отправляемых данных

        String criteria = request.getParameter("criteria");
        try {
            List<User> products = ServiceFactory.getInstance().getUserService().getUsers(criteria);
            String json = new Gson().toJson(products);
            response.getWriter().write(json);
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }
    }
}
