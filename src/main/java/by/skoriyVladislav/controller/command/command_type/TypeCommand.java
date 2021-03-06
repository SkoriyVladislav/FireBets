package by.skoriyVladislav.controller.command.command_type;

public enum TypeCommand {
    LOGIN,
    LOGOUT,
    MAKE_BET,
    MAKE_MATCH,
    DELETE_BET,
    CHANGE_BET,
    SET_RESULT,
    CHANGE_COEFF,
    REGISTRATION,
    CHANGE_LOCALE,
    CHANGE_USERS_ROLE,
    GO_TO_MAIN,
    GO_TO_ERROR,
    GO_TO_LOGIN,
    GO_TO_PROFILE,
    GO_TO_MY_BETS,
    GO_TO_MAKE_BET,
    GO_TO_REGISTRATION,
    GO_TO_USER_PROFILE,
    GO_TO_USER_MANAGEMENT,
    GET_USERS_AJAX,
    CHECK_TIME_AJAX,
    CHECK_LOGIN_AJAX,
    CHECK_SIZE_BETS_AJAX;

    TypeCommand() {
    }
}
