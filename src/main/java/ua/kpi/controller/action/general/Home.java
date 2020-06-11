package ua.kpi.controller.action.general;

import ua.kpi.controller.action.Action;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.HOME;
import static ua.kpi.constant.Pages.ROOT_FOLDER;

public class Home implements Action {
    @Override
    public String handleRequest(HttpServletRequest request) {
        return ROOT_FOLDER + HOME;
    }
}
