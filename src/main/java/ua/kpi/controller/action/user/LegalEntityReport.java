package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;
import ua.kpi.util.ReportValidator;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static ua.kpi.constant.Pages.*;

public class LegalEntityReport extends MultipleRequest implements Action {

    private final ReportService reportService = new ReportServiceImpl();

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        return ROOT_FOLDER + USER_PAGES + LEGAL_ENTITY_REPORT;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {

        ReportValidator reportValidator = new ReportValidator();

        String companyName = request.getParameter("companyName");
        String financialTurnoverParameter = request.getParameter("financialTurnover");

        if(reportValidator.isWorkplaceValid(companyName)
                && reportValidator.isSalaryValid(financialTurnoverParameter)) {

            BigDecimal financialTurnover = new BigDecimal(financialTurnoverParameter);
            Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
            ReportDto reportDto = ReportDto.newBuilder()
                    .setCompanyName(companyName)
                    .setFinancialTurnover(financialTurnover)
                    .setTaxpayerId(userId)
                    .setInspectorId(reportService.getInspectorIdWithLeastReportsNumber(0L))
                    .setReportStatus(ReportStatus.ON_VERIFYING)
                    .setPersonType(PersonType.LEGAL_ENTITY)
                    .build();
            reportService.createLegalEntityReport(reportDto);
            return REDIRECT + REPORT_PATH;
        }

        else {
            request.setAttribute("isCompanyNameValid", reportValidator.isWorkplaceValid(companyName));
            request.setAttribute("isFinancialTurnoverValid", reportValidator.isSalaryValid(financialTurnoverParameter));

            return ROOT_FOLDER + USER_PAGES + LEGAL_ENTITY_REPORT;
        }
    }
}
