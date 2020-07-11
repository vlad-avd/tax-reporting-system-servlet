package ua.kpi.controller.action.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.controller.action.Action;
import ua.kpi.service.ReportService;
import ua.kpi.service.impl.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;

import static ua.kpi.constant.Pages.REDIRECT;
import static ua.kpi.constant.Pages.REPORT_PATH;

public class ReplaceInspector implements Action {

    ReportService reportService = new ReportServiceImpl();
    Logger logger = LoggerFactory.getLogger(ReplaceInspector.class);

    @Override
    public String handleRequest(HttpServletRequest request) {
        String id = request.getParameter("id");
        if(!id.isEmpty()) {
            Long reportId = Long.parseLong(id);
            reportService.setReplacedInspector(reportId);
            logger.debug("Report: " + reportId + " inspector has been replaced");
        }
        return REDIRECT + REPORT_PATH;
    }
}
