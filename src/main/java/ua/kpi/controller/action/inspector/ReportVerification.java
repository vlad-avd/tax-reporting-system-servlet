package ua.kpi.controller.action.inspector;

import ua.kpi.controller.action.Action;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.service.InspectorService;
import ua.kpi.service.InspectorServiceImpl;
import ua.kpi.service.ReportService;
import ua.kpi.service.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ua.kpi.constant.Pages.*;
import static ua.kpi.constant.Pages.REPORT_LIST;

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
        List<Report> reports;
        Long user_id = Long.parseLong(request.getSession().getAttribute("userId").toString());
        reports = inspectorService.getVerificationReport(user_id);
        request.setAttribute("reports", reports);
        return ROOT_FOLDER + INSPECTOR_PAGES + REPORT_VERIFICATION_LIST;
    }
}
