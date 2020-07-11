package ua.kpi.controller.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(RuntimeException cause) {
        super(cause);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
