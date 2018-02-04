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
            case GO_TO_MAIN:
                command = new GoToPageMain(receiver);
                break;

            case GO_TO_REGISTRATION:
                command = new GoToPageRegistration(receiver);
                break;

            case GO_TO_PROFILE:
                command = new GoToPageProfile(receiver);
                break;

            case GO_TO_MY_BETS:
                command = new GoToMyBets(receiver);
                break;

            case GO_TO_LOGIN:
                command = new GoToPageLogin(receiver);
                break;

            case GO_TO_MAKE_BET:
                command = new GoToPageMakeBet(receiver);
                break;

            case GO_TO_USER_PROFILE:
                command = new GoToUserProfile(receiver);
                break;

            case GO_TO_USER_MANAGEMENT:
                command = new GoToUserManagement(receiver);
                break;

            case MAKE_BET:
                command = new MakeBet(receiver);
                break;

            case DELETE_BET:
                command = new DeleteBet(receiver);
                break;

            case CHANGE_USERS_ROLE:
                command = new ChangeUsersRole(receiver);
                break;

            case CHANGE_BET:
                command = new ChangeBet(receiver);
                break;

            case CHANGE_COEFF:
                command = new ChangeCoeff(receiver);
                break;

            case MAKE_MATCH:
                command = new MakeMatch(receiver);
                break;

            case REGISTRATION:
                command = new Registration(receiver);
                break;

            case LOGIN:
                command = new Login(receiver);
                break;

            case LOGOUT:
                command = new Logout(receiver);
                break;

            case CHECK_LOGIN_AJAX:
                command = new CheckLoginAjax(receiver);
                break;

            case CHECK_SIZE_BETS_AJAX:
                command = new CheckSizeBetAjax(receiver);
                break;

            case GET_USERS_AJAX:
                command = new GetUsersAjax(receiver);
                break;
        }
        return command;
    }
}