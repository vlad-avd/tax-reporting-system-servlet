package ua.kpi.controller.action.guest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.UserDto;
import ua.kpi.model.enums.Role;
import ua.kpi.service.GuestService;
import ua.kpi.service.impl.GuestServiceImpl;
import ua.kpi.util.UserValidator;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class Registration extends MultipleRequest {

    private final GuestService guestService = new GuestServiceImpl();
    Logger logger = LoggerFactory.getLogger(Registration.class);

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        return ROOT_FOLDER + GUEST_PAGES + REGISTRATION;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {

        UserValidator userValidator = new UserValidator();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");

        UserDto user = UserDto.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .setPasswordConfirmation(passwordConfirmation)
                .setRole(Role.ROLE_USER)
                .build();

        if(!userValidator.isUserExistsWithUsername(username)
                && userValidator.isValidUsername(username)
                && userValidator.isValidPassword(password)
                && userValidator.arePasswordsMatch(password, passwordConfirmation)) {

            guestService.createUser(user);
            logger.debug("User: " + user.getUsername() + " has been created.");
            return REDIRECT + LOGIN_PATH;
        } else {
            request.setAttribute("usernameIsValid", userValidator.isValidUsername(username));
            request.setAttribute("passwordIsValid", userValidator.isValidPassword(password));
            request.setAttribute("passwordMismatch", userValidator.arePasswordsMatch(password, passwordConfirmation));
            request.setAttribute("isUserExists", userValidator.isUserExistsWithUsername(username));

            return ROOT_FOLDER + GUEST_PAGES + REGISTRATION;
        }
    }
}
