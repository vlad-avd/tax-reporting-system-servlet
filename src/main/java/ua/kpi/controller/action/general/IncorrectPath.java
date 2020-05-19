package ua.kpi.controller.action.general;

import ua.kpi.controller.action.Action;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class IncorrectPath implements Action {
    @Override
    public String handleRequest(HttpServletRequest request) {
        return REDIRECT + HOME_PATH;
    }
}
