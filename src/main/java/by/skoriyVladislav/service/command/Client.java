package by.skoriyVladislav.service.command;

import by.skoriyVladislav.service.command.CommandImpl.*;
import by.skoriyVladislav.service.command.command_type.TypeCommand;

public class Client {

    private Receiver receiver;

    public Client(Receiver receiver) {
        this.receiver = receiver;
    }

    public ICommand initCommand(String commandStr) {
        TypeCommand cmd = TypeCommand.valueOf(commandStr.toUpperCase());
        ICommand command = null;
        switch(cmd) {
            case GO_TO_INDEX:
                command = new GoToPageIndex(receiver);
                break;
            case GO_TO_REGISTRATION:
                command = new GoToPageRegistration(receiver);
                break;
            case GO_TO_PROFILE:
                command = new GoToPageProfile(receiver);
                break;
            case REGISTRATION:
                command = new Registration(receiver);
                break;
            case LOGIN:
                command = new Login(receiver);
                break;
        }
        return command;
    }
}