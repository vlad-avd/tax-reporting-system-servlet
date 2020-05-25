package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.Action;
import ua.kpi.model.entity.Report;
import ua.kpi.service.AdminService;
import ua.kpi.service.AdminServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ua.kpi.constant.Pages.*;

public class AllReports implements Action {

    AdminService adminService = new AdminServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) {

        int currentPage = 1;

        if(request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        int recordsPerPage = 8;

        List<Report> reports;

        String sortByDate = request.getParameter("sortByDate");
        String sortByReportStatus = request.getParameter("sortByReportStatus");

        int rows = 0;

        if((sortByDate == null && sortByReportStatus == null)
                || (sortByDate.equals("fromNewestToOldest") && sortByReportStatus.equals("all"))) {
            reports = adminService.getAllReports(currentPage, recordsPerPage);
            rows = adminService.getReportsNumber();
        } else {
            reports = adminService.getFilteredReports(sortByDate, sortByReportStatus, currentPage, recordsPerPage);
            rows = adminService.getFilteredReportsNumber(sortByReportStatus);
        }
        request.setAttribute("reports", reports);

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);

        return ROOT_FOLDER + ADMIN_PAGES + ALL_REPORTS;
    }
}
