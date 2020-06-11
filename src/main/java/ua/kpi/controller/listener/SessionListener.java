package ua.kpi.controller.listener;

import ua.kpi.model.enums.Role;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "ru";

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("locale", DEFAULT_LOCALE);
        if(httpSessionEvent.getSession().getAttribute("role") == null){
            httpSessionEvent.getSession().setAttribute("role", Role.ROLE_GUEST);
        }
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}