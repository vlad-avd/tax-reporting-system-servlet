package ua.kpi.controller.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "ru";

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("locale", DEFAULT_LOCALE);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}