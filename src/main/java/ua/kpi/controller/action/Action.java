package ua.kpi.controller.action;

import ua.kpi.controller.exception.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Action {

    String handleRequest(HttpServletRequest request) throws SQLException, UserNotFoundException;

}
