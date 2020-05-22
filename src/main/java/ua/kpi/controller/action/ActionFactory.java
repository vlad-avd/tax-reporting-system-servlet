package ua.kpi.controller.action;

import ua.kpi.controller.action.admin.EditUserProfile;
import ua.kpi.controller.action.admin.UserAction;
import ua.kpi.controller.action.admin.UserStatistics;
import ua.kpi.controller.action.general.Home;
import ua.kpi.controller.action.general.IncorrectPath;
import ua.kpi.controller.action.general.Logout;
import ua.kpi.controller.action.guest.Login;
import ua.kpi.controller.action.guest.Registration;
import ua.kpi.controller.action.inspector.ReportVerification;
import ua.kpi.controller.action.inspector.VerificationResult;
import ua.kpi.controller.action.user.*;

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
        actions.put("report", new ReportAction());
        actions.put("report/individual-person-report", new IndividualPersonReport());
        actions.put("report/legal-entity-report", new LegalEntityReport());
        actions.put("user", new UserAction());
        actions.put("report-verification", new ReportVerification());
        actions.put("report-verification/verification-result", new VerificationResult());
        actions.put("report/replace-inspector", new ReplaceInspector());
        actions.put("profile", new Profile());
        actions.put("profile/edit", new EditProfile());
        actions.put("user/edit", new EditUserProfile());
        actions.put("user/statistics", new UserStatistics());
    }

    public Action getAction(String path) {
        return actions.getOrDefault(path, new IncorrectPath());
    }
}
