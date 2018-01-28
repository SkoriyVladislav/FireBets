package by.skoriyVladislav.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.command.Invoker;

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

        String cmd = request.getParameter("command");
        Invoker invoker = new Invoker(ServiceFactory.getInstance().getClient().initCommand(cmd));
        invoker.invokeCommand(request, response);
    }
}