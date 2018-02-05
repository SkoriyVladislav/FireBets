package by.skoriyVladislav.dal.exception;


public class ConnectionPoolException extends DAOException {

    private static final long serialVersionUID = 1046412606789773169L;


    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

}
