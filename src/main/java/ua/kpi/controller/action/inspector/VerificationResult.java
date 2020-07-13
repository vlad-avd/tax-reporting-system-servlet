package ua.kpi.controller.action.inspector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.Action;
import ua.kpi.controller.exception.ReportNotFoundException;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.REDIRECT;
import static ua.kpi.constant.Pages.REPORT_VERIFICATION_LIST_PATH;

public class VerificationResult implements Action {

    ReportService reportService = new ReportServiceImpl();
    Logger logger = LoggerFactory.getLogger(VerificationResult.class);

    @Override
    public String handleRequest(HttpServletRequest request) {
        String reportStatus = request.getParameter("reportStatus");
        String reportId = request.getParameter("id");
        String rejectionReason = request.getParameter("rejectionReason");
        String comment = request.getParameter("comment");
        Long id = Long.parseLong(reportId);

        Report report = reportService.getReportById(id)
                .orElseThrow(() -> new ReportNotFoundException("Report: " + reportId + " was not found"));

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
                .build();

        reportService.verifyReport(reportDto, reportStatus, rejectionReason, comment);
        logger.debug("Report: " + reportId + " has been verified.");
        return REDIRECT + REPORT_VERIFICATION_LIST_PATH;
    }
}
