package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.service.ReportService;
import ua.kpi.service.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.kpi.constant.Pages.*;

public class Report implements Action {

    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) {
        String reportId = request.getParameter("id");
        if(reportId != null){
            Long id = Long.parseLong(reportId);
            ua.kpi.model.entity.Report report = reportService.getReportById(id);
            request.setAttribute("report", report);
            return ROOT_FOLDER + USER_PAGES + REPORT;
        }
        List<ua.kpi.model.entity.Report> reports;
        Long user_id = Long.parseLong(request.getSession().getAttribute("userId").toString());
        reports = reportService.getReportsByUserId(user_id);
        request.setAttribute("reports", reports);
        return ROOT_FOLDER + USER_PAGES + REPORT_LIST;
    }
}
