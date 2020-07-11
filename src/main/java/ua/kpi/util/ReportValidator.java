package ua.kpi.util;

import ua.kpi.controller.listener.SessionListener;

import java.util.Locale;
import java.util.ResourceBundle;

public class ReportValidator {
    private final ResourceBundle regexpBundle;

    {
        regexpBundle = ResourceBundle.getBundle("regex");
    }

    public boolean isFullNameValid(String fullName) {
        return fullName.matches(regexpBundle.getString("full.name.regexp"));
    }

    public boolean isWorkplaceValid(String workplace) {
        System.out.println(workplace);
        System.out.println(Locale.getDefault());
        System.out.println(regexpBundle.getString("workplace.regexp"));
        return workplace.matches(regexpBundle.getString("workplace.regexp"));
    }

    public boolean isSalaryValid(String salary) {
        return salary.matches(regexpBundle.getString("income.regexp"));
    }
}
