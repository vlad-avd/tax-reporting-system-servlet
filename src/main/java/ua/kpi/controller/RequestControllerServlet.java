package ua.kpi.controller;

import ua.kpi.controller.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RequestControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            handleRequest(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            handleRequest(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String path = request.getRequestURI().replaceFirst(".*/tax-reporting-system/", "");
        String page = ActionFactory.getInstance().getAction(path).handleRequest(request);

        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/tax-reporting-system/"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
