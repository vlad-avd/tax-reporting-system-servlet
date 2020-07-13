package ua.kpi.controller.action.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.Action;
import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.User;
import ua.kpi.service.AdminService;
import ua.kpi.service.UserService;
import ua.kpi.service.impl.AdminServiceImpl;
import ua.kpi.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class UserStatistics implements Action {

    AdminService adminService = new AdminServiceImpl();
    UserService userService = new UserServiceImpl();
    Logger logger = LoggerFactory.getLogger(UserStatistics.class);

    @Override
    public String handleRequest(HttpServletRequest request) throws UserNotFoundException {
        Long userId = Long.parseLong(request.getParameter("id"));

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User: " + userId + " was not found."));
        StatisticsDto statistics = adminService.getStatistics(userId);
        logger.debug("User: " + userId + " statistics were received.");
        request.setAttribute("user", user);
        request.setAttribute("statistics", statistics);

        return ROOT_FOLDER + ADMIN_PAGES + USER_STATISTICS;
    }
}
