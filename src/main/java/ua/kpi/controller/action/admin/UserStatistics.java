package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.Action;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.AdminService;
import ua.kpi.service.UserService;
import ua.kpi.service.impl.AdminServiceImpl;
import ua.kpi.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.kpi.constant.Pages.*;

public class UserStatistics implements Action {

    AdminService adminService = new AdminServiceImpl();

    UserService userService = new UserServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        Long userId = Long.parseLong(request.getParameter("id"));

        User user = userService.getUserById(userId);
        StatisticsDto statistics = adminService.getStatistics(userId);

        request.setAttribute("user", user);
        request.setAttribute("statistics", statistics);

        return ROOT_FOLDER + ADMIN_PAGES + USER_STATISTICS;
    }
}
