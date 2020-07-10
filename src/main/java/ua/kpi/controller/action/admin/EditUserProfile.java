package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.UserDto;
import ua.kpi.dto.UserValidationDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;
import ua.kpi.service.AdminService;
import ua.kpi.service.impl.AdminServiceImpl;
import ua.kpi.service.UserService;
import ua.kpi.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class EditUserProfile extends MultipleRequest{

    UserService userService = new UserServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        request.setAttribute("roles", Role.values());
        return ROOT_FOLDER + ADMIN_PAGES + EDIT_USER;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        Long user_id = Long.parseLong(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String userRole = request.getParameter("role");

        UserDto userDto = UserDto.newBuilder()
                .setId(user_id)
                .setUsername(username)
                .setPassword(password)
                .setPasswordConfirmation(passwordConfirmation)
                .setRole(Role.valueOf(userRole))
                .build();

        UserValidationDto userValidationDto = userService.updateUser(userDto);

        if(userValidationDto.isUserValid()) {
            return REDIRECT + USER_LIST_PATH;
        }
        else {
            request.setAttribute("usernameIsValid", userValidationDto.isUsernameValid());
            request.setAttribute("passwordIsValid", userValidationDto.isPasswordValid());
            request.setAttribute("passwordMismatch", userValidationDto.arePasswordsMatch());
            request.setAttribute("isUserExists", userValidationDto.isUserExistsWithUsername());

            return ROOT_FOLDER + USER_PAGES + EDIT_REPORT;
        }
    }
}
