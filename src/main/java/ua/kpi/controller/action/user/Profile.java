package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.model.entity.User;
import ua.kpi.service.AdminService;
import ua.kpi.service.UserService;
import ua.kpi.service.impl.AdminServiceImpl;
import ua.kpi.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.kpi.constant.Pages.*;

public class Profile implements Action {

    UserService userService = new UserServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);
        return ROOT_FOLDER + USER_PAGES + PROFILE;
    }
}
