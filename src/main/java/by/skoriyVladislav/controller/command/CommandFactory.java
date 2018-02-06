package by.skoriyVladislav.controller.command;

import by.skoriyVladislav.dal.connection_pool.ConnectionPool;
import by.skoriyVladislav.controller.command.CommandImpl.*;
import by.skoriyVladislav.controller.command.command_type.TypeCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.Map;

public class CommandFactory {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class);

    private CommandFactory() {
    }

    private static final Map<TypeCommand, ICommand> commands = new EnumMap<>(TypeCommand.class);

    static {
        commands.put(TypeCommand.CHANGE_BET, new ChangeBet());
        commands.put(TypeCommand.CHANGE_COEFF, new ChangeCoeff());
        commands.put(TypeCommand.CHANGE_LOCALE, new ChangeLocale());
        commands.put(TypeCommand.CHANGE_USERS_ROLE, new ChangeUsersRole());

        commands.put(TypeCommand.CHECK_TIME_AJAX, new CheckTimeAjax());
        commands.put(TypeCommand.CHECK_LOGIN_AJAX, new CheckLoginAjax());
        commands.put(TypeCommand.CHECK_SIZE_BETS_AJAX, new CheckSizeBetAjax());

        commands.put(TypeCommand.DELETE_BET, new DeleteBet());
        commands.put(TypeCommand.GET_USERS_AJAX, new GetUsersAjax());

        commands.put(TypeCommand.GO_TO_MAIN, new GoToPageMain());
        commands.put(TypeCommand.GO_TO_LOGIN, new GoToPageLogin());
        commands.put(TypeCommand.GO_TO_MAKE_BET, new GoToPageMakeBet());
        commands.put(TypeCommand.GO_TO_MY_BETS, new GoToPageMyBets());
        commands.put(TypeCommand.GO_TO_PROFILE, new GoToPageProfile());
        commands.put(TypeCommand.GO_TO_REGISTRATION, new GoToPageRegistration());
        commands.put(TypeCommand.GO_TO_USER_MANAGEMENT, new GoToPageUserManagement());
        commands.put(TypeCommand.GO_TO_USER_PROFILE, new GoToPageUserProfile());

        commands.put(TypeCommand.LOGIN, new Login());
        commands.put(TypeCommand.LOGOUT, new Logout());

        commands.put(TypeCommand.MAKE_BET, new MakeBet());
        commands.put(TypeCommand.MAKE_MATCH, new MakeMatch());

        commands.put(TypeCommand.REGISTRATION, new Registration());
        commands.put(TypeCommand.SET_RESULT, new SetResult());
    }

    public static ICommand getCommand(HttpServletRequest request) {
        String commandTypeName = request.getParameter("command");
        boolean commandNameIsValid = commandNameIsValid(commandTypeName);
        if (commandNameIsValid) {
            return initCommand(commandTypeName);
        }else {
            logger.error(String.format("Defined type_command is invalid: %s.", commandTypeName));
            return commands.get(TypeCommand.GO_TO_MAIN);
        }
    }

    private static ICommand initCommand(String commandTypeName) {
        ICommand command;
        commandTypeName = commandTypeName.toUpperCase();
        TypeCommand commandType = TypeCommand.valueOf(commandTypeName);
        command = commands.get(commandType);
        if (command == null) {
            logger.error(String.format("Defined type_command is invalid: %s.", commandType));
            command = commands.get(TypeCommand.GO_TO_MAIN);
        }

        return command;
    }

    private static boolean commandNameIsValid(String commandTypeName) {
        boolean isExist = !((commandTypeName == null) || commandTypeName.trim().isEmpty());
        if (isExist) {
            for (TypeCommand type : TypeCommand.values()) {
                if (type.toString().equalsIgnoreCase(commandTypeName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
