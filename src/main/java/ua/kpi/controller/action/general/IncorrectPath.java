package ua.kpi.controller.action.general;

import ua.kpi.controller.action.Action;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.HOME_PATH;
import static ua.kpi.constant.Pages.REDIRECT;

public class IncorrectPath implements Action {
    @Override
    public String handleRequest(HttpServletRequest request) {
        return REDIRECT + HOME_PATH;
    }
}
