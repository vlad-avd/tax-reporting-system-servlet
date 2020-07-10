package ua.kpi.controller.action.guest;

import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.UserDto;
import ua.kpi.dto.UserValidationDto;
import ua.kpi.model.enums.Role;
import ua.kpi.service.GuestService;
import ua.kpi.service.impl.GuestServiceImpl;
import ua.kpi.util.UserValidator;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class Registration extends MultipleRequest {

    private final GuestService guestService = new GuestServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        return ROOT_FOLDER + GUEST_PAGES + REGISTRATION;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");

        UserDto user = UserDto.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .setPasswordConfirmation(passwordConfirmation)
                .setRole(Role.ROLE_USER)
                .build();

        UserValidationDto userValidationDto = guestService.createUser(user);

        if(userValidationDto.isUserValid()) {
            return REDIRECT + LOGIN_PATH;
        }
        else {
            request.setAttribute("usernameIsValid", userValidationDto.isUsernameValid());
            request.setAttribute("passwordIsValid", userValidationDto.isPasswordValid());
            request.setAttribute("passwordMismatch", userValidationDto.arePasswordsMatch());
            request.setAttribute("isUserExists", userValidationDto.isUserExistsWithUsername());

            return ROOT_FOLDER + GUEST_PAGES + REGISTRATION;
        }
    }
}
