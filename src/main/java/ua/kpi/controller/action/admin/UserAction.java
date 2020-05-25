package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.Action;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.service.AdminService;
import ua.kpi.service.AdminServiceImpl;
import ua.kpi.service.UserService;
import ua.kpi.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ua.kpi.constant.Pages.*;
import static ua.kpi.constant.Pages.REPORT_LIST;

public class UserAction implements Action {

    AdminService adminService = new AdminServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        String userId = request.getParameter("id");
        if(userId != null){
            Long id = Long.parseLong(userId);
            User user = adminService.getUserById(id);
            request.setAttribute("user", user);
            return ROOT_FOLDER + USER_PAGES + REPORT;
        }

        int currentPage = 1;

        if(request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        int recordsPerPage = 2;

        List<User> users;
        users = adminService.getAllUsers(currentPage, recordsPerPage);
        request.setAttribute("users", users);

        int rows = adminService.getUsersNumber();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);

        return ROOT_FOLDER + ADMIN_PAGES + USER_LIST;
    }
}
