package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static ua.kpi.constant.Pages.*;

public class IndividualPersonReport extends MultipleRequest implements Action {

    private final ReportService reportService = new ReportServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        return ROOT_FOLDER + USER_PAGES + INDIVIDUAL_PERSON_REPORT;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {
        String fullName = request.getParameter("fullName");
        String workplace = request.getParameter("workplace");
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        ReportDto reportDto = ReportDto.newBuilder()
                .setFullName(fullName)
                .setWorkplace(workplace)
                .setSalary(salary)
                .setTaxpayerId(userId)
                .setInspectorId(reportService.getInspectorIdWithLeastReportsNumber(0L))
                .setReportStatus(ReportStatus.ON_VERIFYING)
                .setPersonType(PersonType.INDIVIDUAL_PERSON)
                .build();
        reportService.createIndividualPersonReport(reportDto);
        return REDIRECT + REPORT_PATH;
    }
}
