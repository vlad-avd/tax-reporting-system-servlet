package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class Report implements Action {
    @Override
    public String handleRequest(HttpServletRequest request) {
        return ROOT_FOLDER + USER_PAGES + REPORT;
    }
}
