package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class LegalEntityReport extends MultipleRequest implements Action {
    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        return ROOT_FOLDER + USER_PAGES + LEGAL_ENTITY_REPORT;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        return null;
    }
}
