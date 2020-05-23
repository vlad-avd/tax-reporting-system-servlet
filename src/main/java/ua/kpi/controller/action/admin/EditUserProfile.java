package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;
import ua.kpi.service.AdminService;
import ua.kpi.service.AdminServiceImpl;
import ua.kpi.service.UserService;
import ua.kpi.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.kpi.constant.Pages.*;

public class EditUserProfile extends MultipleRequest{

    AdminService adminService = new AdminServiceImpl();

    UserService userService = new UserServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        Long user_id = Long.parseLong(request.getParameter("id"));
        User user = adminService.getUserById(user_id);
        request.setAttribute("user", user);
        request.setAttribute("roles", Role.values());
        return ROOT_FOLDER + ADMIN_PAGES + EDIT_USER;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        Long user_id = Long.parseLong(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(password);
        String passwordConfirmation = request.getParameter("passwordConfirmation");
        String userRole = request.getParameter("role");

        UserDto userDto = UserDto.newBuilder()
                .setId(user_id)
                .setUsername(username)
                .setPassword(password)
                .setRole(Role.valueOf(userRole))
                .build();
        userService.updateUser(userDto);

        return REDIRECT + USER_LIST_PATH;
    }
}
