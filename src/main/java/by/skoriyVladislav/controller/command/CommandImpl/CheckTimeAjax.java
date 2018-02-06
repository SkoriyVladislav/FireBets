package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;
import by.skoriyVladislav.dal.DAOFactory;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckTimeAjax implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных

        int id = Integer.valueOf(request.getParameter("matchId"));

        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            if (ServiceFactory.getInstance().getMatchService().checkTimeForBet(id)) {
                jsonEnt.put("serverInfo", "true");
            } else {
                jsonEnt.put("serverInfo", "false");
            }
            out.print(jsonEnt.toString());
        } catch (ServiceException ex) {

            response.sendRedirect("error.jsp");
        }
    }
}
