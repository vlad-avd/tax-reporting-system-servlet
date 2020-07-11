package ua.kpi.service.impl;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.impl.ReportDaoImpl;
import ua.kpi.dto.PageableReportDto;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.service.ReportService;
import ua.kpi.util.Page;

import java.util.List;

public class ReportServiceImpl implements ReportService {

    private final ReportDao reportDao;

    {
        reportDao = new ReportDaoImpl();
    }

    @Override
    public boolean createIndividualPersonReport(ReportDto report) {
        return reportDao.saveIndividualPersonReport(report);
    }

    @Override
    public boolean createLegalEntityReport(ReportDto report) {
        return reportDao.saveLegalEntityReport(report);
    }

    @Override
    public PageableReportDto getReportsByUserId(Long id, Page page) {
        PageableReportDto pageableReportDto = new PageableReportDto();
        pageableReportDto.setReportsPage(reportDao.getReportsByUserId(id, page.getCurrentPage(), page.getRecordsPerPage()));
        pageableReportDto.setRowNumber(getReportsNumberByUserId(id));

        return pageableReportDto;
    }

    @Override
    public Report getReportById(Long id) {
        return reportDao.findReportById(id);
    }

    @Override
    public boolean updateVerifiedReport(ReportDto reportDto) {
        return reportDao.updateVerifiedReport(reportDto);
    }

    @Override
    public boolean moveReportToArchive(ReportDto reportDto) {
        return reportDao.moveReportToArchive(reportDto);
    }

    @Override
    public boolean setReplacedInspector(Long reportId) {
        Long oldInspectorId = getReportById(reportId).getInspectorId();
        Long newInspectorId = getInspectorIdWithLeastReportsNumber(reportId);
        return reportDao.setReplacedInspector(reportId, oldInspectorId, newInspectorId);
    }

    @Override
    public Long getInspectorIdWithLeastReportsNumber(Long reportId) {
        List<Long> inspectors = reportDao.getAllInspectorIds();
        List<Long> replacedInspectors = reportDao.getReplacedInspectorsByReportId(reportId);
        inspectors.removeAll(replacedInspectors);
        List<Long> inspectorIdsInReports = reportDao.getAllInspectorIdsFromReports();

        Long inspectorWithLeastReportsNumber = inspectors.get(0);
        long reportsNumber = inspectorIdsInReports.stream()
                .filter(inspectors.get(0)::equals)
                .count();

        for(Long inspector : inspectors){
            long rN = inspectorIdsInReports.stream()
                    .filter(inspector::equals)
                    .count();

            if(rN < reportsNumber){
                reportsNumber = rN;
                inspectorWithLeastReportsNumber = inspector;
            }
        }

        return  inspectorWithLeastReportsNumber;
    }

    @Override
    public boolean isPossiblyToReplaceInspector(Long reportId) {
        List<Long> inspectors = reportDao.getAllInspectorIds();
        System.out.println("All inspectors: " + inspectors);
        List<Long> replacedInspectors = reportDao.getReplacedInspectorsByReportId(reportId);
        System.out.println("Replaced inspectors: " + replacedInspectors);

        return (inspectors.size() - replacedInspectors.size()) > 1;
    }

    @Override
    public int getReportsNumberByUserId(Long userId) {
        return reportDao.getReportsNumberByUserId(userId);
    }

    @Override
    public int getVerificationReportsNumberByInspectorId(Long inspectorId) {
        return reportDao.getVerificationReportsNumberByInspectorId(inspectorId);
    }

    @Override
    public boolean updateReport(ReportDto reportDto) {
        return reportDao.updateReport(reportDto);
    }

    @Override
    public void verifyReport(ReportDto reportDto, String reportStatus, String rejectionReason, String comment) {
        if(reportStatus.equals("approve") ){
            reportDto.getBuilder().setReportStatus(ReportStatus.APPROVED).build();
            moveReportToArchive(reportDto);

        }
        else if(reportStatus.equals("reject") ){
            reportDto.getBuilder().setReportStatus(ReportStatus.REJECTED).build();
            if(!rejectionReason.isEmpty()){
                reportDto.getBuilder().setRejectionReason(RejectionReason.valueOf(rejectionReason));
            }
            if(!comment.isEmpty()) {
                reportDto.getBuilder().setComment(comment);
            }
            moveReportToArchive(reportDto);
        }
        else if(reportStatus.equals("sendToEdit")){
            reportDto.getBuilder().setReportStatus(ReportStatus.NEED_TO_EDIT).build();
            if(!comment.isEmpty()) {
                reportDto.getBuilder().setComment(comment);
            }
            updateVerifiedReport(reportDto);
        }
    }
}
