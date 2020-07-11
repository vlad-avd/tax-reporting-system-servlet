package ua.kpi.controller.exception;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(RuntimeException cause) {
        super(cause);
    }

    public ReportNotFoundException(String message) {
        super(message);
    }
}
