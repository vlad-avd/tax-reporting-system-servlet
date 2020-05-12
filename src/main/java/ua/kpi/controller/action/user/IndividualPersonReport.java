package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.controller.action.MultipleRequest;
import ua.kpi.dto.ReportDto;
import ua.kpi.dto.UserDto;
import ua.kpi.model.enums.Role;
import ua.kpi.service.GuestService;
import ua.kpi.service.GuestServiceImpl;
import ua.kpi.service.ReportService;
import ua.kpi.service.ReportServiceImpl;

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
        ReportDto reportDto = ReportDto.newBuilder().setFullName(fullName).setWorkplace(workplace).setSalary(salary).build();
        reportService.createIndividualPersonReport(reportDto);
        return REDIRECT + LOGIN_PATH;
    }
}
