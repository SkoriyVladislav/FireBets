package by.skoriyVladislav.service.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Invoker {
    private ICommand command;

    public Invoker(ICommand command) {
        this.command = command;
    }

    public void invokeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        command.execute(request, response);
    }
}
