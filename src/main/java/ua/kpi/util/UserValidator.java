package ua.kpi.util;

import ua.kpi.service.GuestService;
import ua.kpi.service.impl.GuestServiceImpl;

import java.util.ResourceBundle;

public class UserValidator {

    GuestService guestService;
    private ResourceBundle regexpBundle;

    {
        guestService = new GuestServiceImpl();
        regexpBundle = ResourceBundle.getBundle("regex");
    }

    public boolean isUserExistsWithUsername(String username) {
        return guestService.isUserExistsWithUsername(username);
    }

    public boolean isValidUsername(String username) {
        return username.matches(regexpBundle.getString("username.regexp"));
    }

    public boolean isValidPassword(String password) {
        return password.matches(regexpBundle.getString("password.regexp"));
    }

    public boolean arePasswordsMatch(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }
}
