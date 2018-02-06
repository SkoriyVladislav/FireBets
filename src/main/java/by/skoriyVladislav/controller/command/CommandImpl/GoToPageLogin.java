package by.skoriyVladislav.controller.command.CommandImpl;

import by.skoriyVladislav.controller.command.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class GoToPageLogin implements ICommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nfrom = URLEncoder.encode(request.getRequestURI(), "UTF-8");
        String from = nfrom.substring(3);
        if (request.getQueryString() != null) {
            from += "?" + request.getQueryString();
        }

        from = from.replace("&", "%26");
        response.sendRedirect("login.jsp?from=" + from);
    }
}
