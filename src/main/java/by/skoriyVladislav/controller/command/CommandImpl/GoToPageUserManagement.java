package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPageUserManagement implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/user_management.jsp").forward(request, response);
    }
}