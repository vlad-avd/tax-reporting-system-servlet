package ua.kpi.controller.action.inspector;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.exception.ReportNotFoundException;
import ua.kpi.dto.PageableReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.service.InspectorService;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.InspectorServiceImpl;
import ua.kpi.service.impl.ReportServiceImpl;
import ua.kpi.util.Page;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.*;

public class ReportVerification implements Action {

    InspectorService inspectorService = new InspectorServiceImpl();
    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) {

        String reportId = request.getParameter("id");

        if(reportId != null){
            Long id = Long.parseLong(reportId);
            Report report = reportService.getReportById(id)
                    .orElseThrow(() -> new ReportNotFoundException("Report: " + reportId + " was not found"));
            request.setAttribute("report", report);
            RejectionReason[] rejectionReasons = RejectionReason.values();
            request.setAttribute("rejectionReasons", rejectionReasons);
            return ROOT_FOLDER + INSPECTOR_PAGES + REPORT_VERIFICATION;
        }

        Page page = new Page();

        if(request.getParameter("currentPage") != null) {
            page.setCurrentPage(Integer.parseInt(request.getParameter("currentPage")));
        }

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());

        PageableReportDto pageableReportDto = inspectorService.getVerificationReport(userId, page);
        page.setSize(reportService.getVerificationReportsNumberByInspectorId(userId));
        page.setPageNumber((int)Math.ceil((double)page.getSize() / page.getRecordsPerPage()));

        request.setAttribute("reports", pageableReportDto.getReportsPage());
        request.setAttribute("noOfPages", page.getPageNumber());
        request.setAttribute("currentPage", page.getCurrentPage());
        request.setAttribute("recordsPerPage", page.getRecordsPerPage());

        return ROOT_FOLDER + INSPECTOR_PAGES + REPORT_VERIFICATION_LIST;
    }
}
