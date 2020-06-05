package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;
import ua.kpi.model.entity.Report;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ua.kpi.constant.Pages.*;

public class ReportAction implements Action {

    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) {
        String reportId = request.getParameter("id");
        if(reportId != null){
            Long id = Long.parseLong(reportId);
            Report report = reportService.getReportById(id);
            request.setAttribute("replaceInspector", true);
            request.setAttribute("report", report);
            if(reportService.isPossiblyToReplaceInspector(id)){
                request.setAttribute("replaceInspector", true);
            }
            else{
                request.setAttribute("replaceInspector", false);
            }
            return ROOT_FOLDER + USER_PAGES + REPORT;
        }

        int currentPage = 1;

        if(request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        int recordsPerPage = 8;

        List<Report> reports;
        Long userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
        reports = reportService.getReportsByUserId(userId, currentPage, recordsPerPage);
        request.setAttribute("reports", reports);

        int rows = reportService.getReportsNumberByUserId(userId);

        int nOfPages = (int)Math.ceil((double)rows / recordsPerPage);

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);

        return ROOT_FOLDER + USER_PAGES + REPORT_LIST;
    }
}
