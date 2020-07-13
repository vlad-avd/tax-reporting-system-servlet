package ua.kpi.controller.action.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static ua.kpi.constant.Pages.LOGIN_PATH;
import static ua.kpi.constant.Pages.REDIRECT;

public class Logout implements Action {

    Logger logger = LoggerFactory.getLogger(Logout.class);

    @Override
    public String handleRequest(HttpServletRequest request) {

        HttpSession session = request.getSession();
        logger.debug("User: " + session.getAttribute("username") + "logged out.");
        session.invalidate();

        return REDIRECT + LOGIN_PATH;
    }
}
