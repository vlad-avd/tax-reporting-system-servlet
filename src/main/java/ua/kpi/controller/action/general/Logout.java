package ua.kpi.controller.action.general;

import ua.kpi.controller.action.Action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.kpi.constant.Pages.*;

public class Logout implements Action {
    @Override
    public String handleRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("role");
        return REDIRECT + LOGIN_PATH;
    }
}
