package by.skoriyVladislav.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

@WebServlet(name = "AjaxController", urlPatterns = {"/ajax_controller"})
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String val = request.getParameter("login");
        ICommand command = ServiceFactory.getInstance().getClient().initCommand("CHECK_LOGIN_AJAX");
        Invoker invoker = new Invoker(command);
        invoker.invokeCommand(request, response);*/

        response.setContentType("application/json");//Отправляем от сервера данные в JSON -формате
        response.setCharacterEncoding("utf-8");//Кодировка отправляемых данных
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonEnt = new JSONObject();
            if(request.getParameter("login").equals("admin")) {
                jsonEnt.put("serverInfo", "Логин свободен");
            }else {
                jsonEnt.put("serverInfo", "Введен неправильный логин или пароль!");
            }
            out.print(jsonEnt.toString());
        }
    }
}
