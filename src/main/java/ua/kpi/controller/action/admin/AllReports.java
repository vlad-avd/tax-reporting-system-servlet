package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.service.AdminService;
import ua.kpi.service.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.kpi.constant.Pages.*;

public class AllReports extends MultipleRequest {

    AdminService adminService = new AdminServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {

        int currentPage = 1;

        if(request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        int recordsPerPage = 8;

        List<Report> reports;

        reports = adminService.getAllReports(currentPage, recordsPerPage);
        request.setAttribute("reports", reports);

        int rows = adminService.getReportsNumber();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);

        return ROOT_FOLDER + ADMIN_PAGES + ALL_REPORTS;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        return null;
    }
}
