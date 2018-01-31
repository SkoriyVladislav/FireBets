package by.skoriyVladislav.service.command.CommandImpl;

import by.skoriyVladislav.service.command.ICommand;
import by.skoriyVladislav.service.command.Receiver;
import by.skoriyVladislav.service.command.command_type.TypeCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUsersAjax implements ICommand {
    private Receiver receiver;

    public GetUsersAjax(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiver.action(TypeCommand.GET_USERS_AJAX, request, response);
    }
}
