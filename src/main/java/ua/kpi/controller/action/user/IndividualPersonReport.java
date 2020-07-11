package ua.kpi.controller.action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

public class IndividualPersonReport extends MultipleRequest implements Action {

    private final ReportService reportService = new ReportServiceImpl();
    Logger logger = LoggerFactory.getLogger(IndividualPersonReport.class);

    @Override
    protected String handleGetRequest(HttpServletRequest request) {
        return ROOT_FOLDER + USER_PAGES + INDIVIDUAL_PERSON_REPORT;
    }

    @Override
    protected String handlePostRequest(HttpServletRequest request) {

        ReportValidator reportValidator = new ReportValidator();

        String fullName = request.getParameter("fullName");
        String workplace = request.getParameter("workplace");
        String salaryParameter = request.getParameter("salary");

        if(reportValidator.isFullNameValid(fullName)
                && reportValidator.isWorkplaceValid(workplace)
                && reportValidator.isSalaryValid(salaryParameter)) {

            BigDecimal salary = new BigDecimal(salaryParameter);
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
            logger.debug("Individual person report has been created.");
            return REDIRECT + REPORT_PATH;
        } else {
            request.setAttribute("isFullNameValid", reportValidator.isFullNameValid(fullName));
            request.setAttribute("isWorkplaceValid", reportValidator.isWorkplaceValid(workplace));
            request.setAttribute("isSalaryValid", reportValidator.isSalaryValid(salaryParameter));

            return ROOT_FOLDER + USER_PAGES + INDIVIDUAL_PERSON_REPORT;
        }
    }
}
