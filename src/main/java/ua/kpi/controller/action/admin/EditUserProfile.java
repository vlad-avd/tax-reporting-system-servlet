package ua.kpi.controller.action.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;
import ua.kpi.service.UserService;
import ua.kpi.service.impl.UserServiceImpl;
import ua.kpi.util.UserValidator;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class EditUserProfile extends MultipleRequest{

    UserService userService = new UserServiceImpl();
    Logger logger = LoggerFactory.getLogger(EditUserProfile.class);

    @Override
    protected String handleGetRequest(HttpServletRequest request) throws UserNotFoundException {
        Long userId = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User: " + userId + " was not found."));
        request.setAttribute("user", user);
        request.setAttribute("roles", Role.values());

        return ROOT_FOLDER + ADMIN_PAGES + EDIT_USER;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        UserValidator userValidator = new UserValidator();

        Long userId = Long.parseLong(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String userRole = request.getParameter("role");

        UserDto userDto = UserDto.newBuilder()
                .setId(userId)
                .setUsername(username)
                .setPassword(password)
                .setPasswordConfirmation(passwordConfirmation)
                .setRole(Role.valueOf(userRole))
                .build();

        if(userValidator.isValidUsername(username)
                && userValidator.isValidPassword(password)
                && userValidator.arePasswordsMatch(password, passwordConfirmation)) {

            userService.updateUser(userDto);
            logger.debug("User: " + userId + " has been updated.");
            return REDIRECT + USER_LIST_PATH;
        } else {
            request.setAttribute("user", userDto);
            request.setAttribute("roles", Role.values());
            request.setAttribute("usernameIsValid", userValidator.isValidUsername(username));
            request.setAttribute("passwordIsValid", userValidator.isValidPassword(password));
            request.setAttribute("passwordMismatch", userValidator.arePasswordsMatch(password, passwordConfirmation));

            return ROOT_FOLDER + ADMIN_PAGES + EDIT_USER;
        }
    }
}
