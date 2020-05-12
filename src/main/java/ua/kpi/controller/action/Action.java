package ua.kpi.controller.action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Action {

    String handleRequest(HttpServletRequest request) throws SQLException;

}
