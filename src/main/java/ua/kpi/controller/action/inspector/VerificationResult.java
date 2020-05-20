package ua.kpi.controller.action.inspector;

import ua.kpi.controller.action.Action;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.service.ReportService;
import ua.kpi.service.ReportServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ua.kpi.constant.Pages.*;

public class VerificationResult implements Action {

    ReportService reportService = new ReportServiceImpl();

    @Override
    public String handleRequest(HttpServletRequest request) throws SQLException {
        String reportStatus = request.getParameter("reportStatus");
        String reportId = request.getParameter("id");
        String rejectionReason = request.getParameter("rejectionReason");
        String comment = request.getParameter("comment");
        Long id = Long.parseLong(reportId);

        ReportDto reportDto = ReportDto.newBuilder()
                .setId(id)
                .setInspectorId(Long.parseLong(request.getSession().getAttribute("userId").toString()))
                .build();

        if(!rejectionReason.isEmpty()){
            reportDto.getBuilder().setRejectionReason(RejectionReason.valueOf(rejectionReason));
        }
        if(!comment.isEmpty()){
            reportDto.getBuilder().setComment(comment);
        }

        if( reportStatus.equals("approve") ){
            reportDto.getBuilder().setReportStatus(ReportStatus.APPROVED).build();
        }
        else if( reportStatus.equals("reject") ){
            reportDto.getBuilder().setReportStatus(ReportStatus.REJECTED).build();
        }
        else if(reportStatus.equals("sendToEdit")){
            reportDto.getBuilder().setReportStatus(ReportStatus.NEED_TO_EDIT).build();
        }
        reportService.updateVerifiedReport(reportDto);
        return ROOT_FOLDER + INSPECTOR_PAGES + REPORT_VERIFICATION_LIST;
    }
}
