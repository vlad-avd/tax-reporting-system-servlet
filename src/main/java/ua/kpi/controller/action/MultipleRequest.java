package ua.kpi.controller.action;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.HOME;
import static ua.kpi.constant.Pages.ROOT_FOLDER;

public abstract class MultipleRequest implements Action {
    @Override
    public String handleRequest(HttpServletRequest request) {
        if(request.getMethod().equals("GET")){
            return handleGetRequest(request);
        }
        else if(request.getMethod().equals("POST")){
            return handlePostRequest(request);
        }
        return ROOT_FOLDER + HOME;
    }

    protected abstract String handleGetRequest(HttpServletRequest request);

    protected abstract String handlePostRequest(HttpServletRequest request);


}
