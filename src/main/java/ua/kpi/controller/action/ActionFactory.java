package ua.kpi.controller.action;

import ua.kpi.controller.action.general.Home;
import ua.kpi.controller.action.general.IncorrectPath;
import ua.kpi.controller.action.general.Logout;
import ua.kpi.controller.action.guest.Login;
import ua.kpi.controller.action.guest.Registration;
import ua.kpi.controller.action.user.IndividualPersonReport;
import ua.kpi.controller.action.user.LegalEntityReport;
import ua.kpi.controller.action.user.Report;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFactory {

    private static ActionFactory instance;

    synchronized public static ActionFactory getInstance()
    {
        if (instance == null)
        {
            instance = new ActionFactory();
        }
        return instance;
    }

    private ActionFactory() { };

    private final Map<String, Action> actions = new ConcurrentHashMap<>();

    {
        actions.put("registration", new Registration());
        actions.put("login", new Login());
        actions.put("logout", new Logout());
        actions.put("home", new Home());
        actions.put("report", new Report());
        actions.put("report/individual-person-report", new IndividualPersonReport());
        actions.put("report/legal-entity-report", new LegalEntityReport());
    }

    public Action getAction(String path) {
        return actions.getOrDefault(path, new IncorrectPath());
    }
}
