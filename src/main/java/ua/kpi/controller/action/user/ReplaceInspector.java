package ua.kpi.controller.action.user;

import ua.kpi.controller.action.Action;
import ua.kpi.model.entity.Report;
import ua.kpi.service.ReportService;
import ua.kpi.service.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ua.kpi.constant.Pages.*;
import static ua.kpi.constant.Pages.REPORT_LIST;

public class ReplaceInspector implements Action {

    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        String id = request.getParameter("id");
        System.out.println(id);
        if(!id.isEmpty()) {
            Long reportId = Long.parseLong(id);

            reportService.setReplacedInspector(reportId);
        }
        return REDIRECT + REPORT_PATH;
    }
}
