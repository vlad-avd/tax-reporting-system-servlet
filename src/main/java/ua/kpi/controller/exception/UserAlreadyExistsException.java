package ua.kpi.controller.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(RuntimeException cause) {
        super(cause);
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
