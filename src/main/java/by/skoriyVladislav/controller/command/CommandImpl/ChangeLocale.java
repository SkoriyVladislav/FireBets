package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLocale implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String)request.getSession().getAttribute("locale");
        if (locale == null || locale.equals("ru_RU")) {
            locale = "en_US";
        } else {
            locale = "ru_RU";
        }
        request.getSession().setAttribute("locale", locale);
        response.sendRedirect("index.jsp");
    }
}