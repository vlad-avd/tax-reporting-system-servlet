package ua.kpi.controller.action.inspector;

import ua.kpi.controller.action.Action;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.service.InspectorService;
import ua.kpi.service.impl.InspectorServiceImpl;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ua.kpi.constant.Pages.*;

public class ReportVerification implements Action {

    InspectorService inspectorService = new InspectorServiceImpl();
    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {

        String reportId = request.getParameter("id");

        if(reportId != null){
            Long id = Long.parseLong(reportId);
            Report report = reportService.getReportById(id);
            request.setAttribute("report", report);
            RejectionReason[] rejectionReasons = RejectionReason.values();
            request.setAttribute("rejectionReasons", rejectionReasons);
            return ROOT_FOLDER + INSPECTOR_PAGES + REPORT_VERIFICATION;
        }

        int currentPage = 1;

        if(request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        int recordsPerPage = 2;

        List<Report> reports;

        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        reports = inspectorService.getVerificationReport(userId, currentPage, recordsPerPage);
        request.setAttribute("reports", reports);

        int rows = reportService.getVerificationReportsNumberByInspectorId(userId);

        int nOfPages = (int)Math.ceil((double)rows / recordsPerPage);

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);

        return ROOT_FOLDER + INSPECTOR_PAGES + REPORT_VERIFICATION_LIST;
    }
}
