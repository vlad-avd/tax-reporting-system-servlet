package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.Action;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.service.AdminService;
import ua.kpi.service.impl.AdminServiceImpl;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ua.kpi.constant.Pages.*;

public class UserStatistics implements Action {

    AdminService adminService = new AdminServiceImpl();

    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        Long userId = Long.parseLong(request.getParameter("id"));
        User user = adminService.getUserById(userId);
        StatisticsDto statistics = adminService.getStatistics(userId);

        List<Report> reports = reportService.getReportsByUserId(userId);

        request.setAttribute("reports", reports);
        request.setAttribute("user", user);
        request.setAttribute("statistics", statistics);

        return ROOT_FOLDER + ADMIN_PAGES + USER_STATISTICS;
    }
}
