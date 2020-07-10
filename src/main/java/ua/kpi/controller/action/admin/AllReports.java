package ua.kpi.controller.action.admin;

import ua.kpi.controller.action.Action;
import ua.kpi.dto.PageableReportDto;
import ua.kpi.service.AdminService;
import ua.kpi.service.impl.AdminServiceImpl;
import ua.kpi.util.Page;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class AllReports implements Action {

    AdminService adminService = new AdminServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) {

        Page page = new Page();

        if(request.getParameter("currentPage") != null) {
            page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
        }

        String sortByDate = request.getParameter("sortByDate");
        String sortByReportStatus = request.getParameter("sortByReportStatus");

        PageableReportDto pageableReport = adminService.getFilteredReports(page, sortByDate, sortByReportStatus);

        page.setSize(pageableReport.getRowNumber());

        request.setAttribute("reports", pageableReport.getReportsPage());

        page.setPageNumber((int)Math.ceil((double)page.getSize() / page.getRecordsPerPage()));

        request.setAttribute("sortByDate", sortByDate);
        request.setAttribute("sortByReportStatus", sortByReportStatus);

        request.setAttribute("noOfPages", page.getPageNumber());
        request.setAttribute("currentPage", page.getCurrentPage());
        request.setAttribute("recordsPerPage", page.getRecordsPerPage());

        return ROOT_FOLDER + ADMIN_PAGES + ALL_REPORTS;
    }
}
