package ua.kpi.controller.action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.Action;
import ua.kpi.controller.exception.ReportNotFoundException;
import ua.kpi.dto.PageableReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;
import ua.kpi.util.Page;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class ReportAction implements Action {

    ReportService reportService = new ReportServiceImpl();
    Logger logger = LoggerFactory.getLogger(ReportAction.class);

    @Override
    public String handleRequest(HttpServletRequest request) {
        String reportId = request.getParameter("id");
        if(reportId != null) {
            Long id = Long.parseLong(reportId);
            Report report = reportService.getReportById(id)
                    .orElseThrow(() -> new ReportNotFoundException("Report: " + reportId + " was not found"));
            request.setAttribute("replaceInspector", true);
            request.setAttribute("report", report);
            if(reportService.isPossiblyToReplaceInspector(id)){
                request.setAttribute("replaceInspector", true);
            } else{
                request.setAttribute("replaceInspector", false);
            }
            return ROOT_FOLDER + USER_PAGES + REPORT;
        }

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());

        Page page = new Page();

        if(request.getParameter("currentPage") != null) {
            page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
        }

        PageableReportDto pageableReportDto = reportService.getReportsByUserId(userId, page);
        logger.debug("User: " + userId + " received their reports.");
        page.setSize(reportService.getReportsNumberByUserId(userId));
        page.setPageNumber((int)Math.ceil((double)page.getSize() / page.getRecordsPerPage()));

        request.setAttribute("reports", pageableReportDto.getReportsPage());
        request.setAttribute("noOfPages", page.getPageNumber());
        request.setAttribute("currentPage", page.getCurrentPage());
        request.setAttribute("recordsPerPage", page.getRecordsPerPage());

        return ROOT_FOLDER + USER_PAGES + REPORT_LIST;
    }
}
