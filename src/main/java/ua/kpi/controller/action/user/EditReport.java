package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.PersonType;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;
import ua.kpi.util.RequestParametersSetter;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static ua.kpi.constant.Pages.*;

public class EditReport implements Action {

    ReportService reportService = new ReportServiceImpl();
    RequestParametersSetter parametersSetter = new RequestParametersSetter();

    @Override
    public String handleRequest(HttpServletRequest request) {

        String update = request.getParameter("update");
        Long reportId = Long.parseLong(request.getParameter("id"));
        Report report = reportService.getReportById(reportId);
        ReportDto reportDto = ReportDto.newBuilder()
                .setId(reportId)
                .build();

        if (update.equals("false")) {
            request.setAttribute("report", report);
            return ROOT_FOLDER + USER_PAGES + EDIT_REPORT;
        } else {
            if(report.getPersonType().equals(PersonType.INDIVIDUAL_PERSON)) {
                String fullName = request.getParameter("fullName");
                String workplace = request.getParameter("workplace");
                BigDecimal salary = new BigDecimal(request.getParameter("salary"));

                reportDto.getBuilder()
                        .setFullName(fullName)
                        .setWorkplace(workplace)
                        .setSalary(salary)
                        .build();
            } else {
                String companyName = request.getParameter("companyName");
                BigDecimal financialTurnover = new BigDecimal(request.getParameter("financialTurnover"));

                reportDto.getBuilder()
                        .setCompanyName(companyName)
                        .setFinancialTurnover(financialTurnover)
                        .build();
            }

            Map<String, String> parameters = new HashMap<>();
            parameters.put("id", reportId.toString());

            reportService.updateReport(reportDto);
            return REDIRECT + REPORT_PATH + parametersSetter.setParameters(parameters);
        }
    }
}
