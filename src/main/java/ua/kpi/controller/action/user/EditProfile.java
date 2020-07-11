package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;
import ua.kpi.service.UserService;
import ua.kpi.service.impl.UserServiceImpl;
import ua.kpi.util.UserValidator;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class EditProfile extends MultipleRequest implements Action {

    UserService userService = new UserServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        String role = request.getSession().getAttribute("role").toString();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        request.setAttribute("role", role);

        return ROOT_FOLDER + USER_PAGES + EDIT_PROFILE;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {

        UserValidator userValidator = new UserValidator();

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        String role = request.getSession().getAttribute("role").toString();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password-confirmation");

        UserDto userDto = UserDto.newBuilder()
                .setId(userId)
                .setUsername(username)
                .setPassword(password)
                .setPasswordConfirmation(passwordConfirmation)
                .setRole(Role.valueOf(role))
                .build();

        if(userValidator.isValidUsername(username)
                && userValidator.isValidPassword(password)
                && userValidator.arePasswordsMatch(password, passwordConfirmation)) {

            userService.updateUser(userDto);
            return REDIRECT + PROFILE_PATH;
        } else {
            request.setAttribute("usernameIsValid", userValidator.isValidUsername(username));
            request.setAttribute("passwordIsValid", userValidator.isValidPassword(password));
            request.setAttribute("passwordMismatch", userValidator.arePasswordsMatch(password, passwordConfirmation));
            request.setAttribute("user", userDto);

            return ROOT_FOLDER + USER_PAGES + EDIT_PROFILE;
        }
    }
}
