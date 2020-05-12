package ua.kpi.controller.exception;

import java.sql.SQLException;

public class SqlRuntimeException extends RuntimeException {
    public SqlRuntimeException(SQLException cause) {
        super(cause);
    }

    public SqlRuntimeException(String message) {
        super(message);
    }
}
