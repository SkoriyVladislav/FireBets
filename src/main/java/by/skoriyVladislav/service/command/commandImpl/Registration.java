package by.skoriyVladislav.service.command.commandImpl;

import by.skoriyVladislav.service.command.ICommand;
import by.skoriyVladislav.service.command.Receiver;
import by.skoriyVladislav.service.command.command_type.TypeCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration implements ICommand {
    private Receiver receiver;

    public Registration(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiver.action(TypeCommand.REGISTRATION, request, response);
    }
}
