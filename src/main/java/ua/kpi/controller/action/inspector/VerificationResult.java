package ua.kpi.controller.action.inspector;

import ua.kpi.controller.action.Action;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.kpi.constant.Pages.REDIRECT;
import static ua.kpi.constant.Pages.REPORT_VERIFICATION_LIST_PATH;

public class VerificationResult implements Action {

    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        String reportStatus = request.getParameter("reportStatus");
        String reportId = request.getParameter("id");
        String rejectionReason = request.getParameter("rejectionReason");
        String comment = request.getParameter("comment");
        Long id = Long.parseLong(reportId);

        Report report = reportService.getReportById(id);

        ReportDto reportDto = ReportDto.newBuilder()
                .setId(report.getId())
                .setCompanyName(report.getCompanyName())
                .setFinancialTurnover(report.getFinancialTurnover())
                .setFullName(report.getFullName())
                .setInspectorId(report.getInspectorId())
                .setPersonType(report.getPersonType())
                .setSalary(report.getSalary())
                .setTaxpayerId(report.getTaxpayerId())
                .setWorkplace(report.getWorkplace())
                .setCreated(report.getCreated())
                .setLastEdit(report.getLastEdit())
                .build();

        reportService.verifyReport(reportDto, reportStatus, rejectionReason, comment);

        return REDIRECT + REPORT_VERIFICATION_LIST_PATH;
    }
}
