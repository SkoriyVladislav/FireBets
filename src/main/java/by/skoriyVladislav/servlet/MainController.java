package by.skoriyVladislav.servlet;

import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.command.Client;
import by.skoriyVladislav.service.command.ICommand;
import by.skoriyVladislav.service.command.Invoker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainController", urlPatterns = {"/controller"})
public class MainController extends HttpServlet {

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

        ServiceFactory factory = ServiceFactory.getInstance();
        Client client = factory.getClient();

        ICommand command = client.initCommand(request.getParameter("command"));
        Invoker invoker = new Invoker(command);

        invoker.invokeCommand(request, response);
    }
}
