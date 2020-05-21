package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.Action;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;
import ua.kpi.service.AdminService;
import ua.kpi.service.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.kpi.constant.Pages.*;

public class EditUserProfile implements Action {
    AdminService adminService = new AdminServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        Long user_id = Long.parseLong(request.getSession().getAttribute("userId").toString());
        User user = adminService.getUserById(user_id);
        request.setAttribute("user", user);
        request.setAttribute("roles", Role.values());
        return REDIRECT + PROFILE;
    }
}
