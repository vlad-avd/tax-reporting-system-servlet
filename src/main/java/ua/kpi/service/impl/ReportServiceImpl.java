package ua.kpi.service.impl;

import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.ReportDao;
import ua.kpi.dao.impl.ReportDaoImpl;
import ua.kpi.dto.PageableReportDto;
import ua.kpi.dto.ReportDto;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.service.ReportService;
import ua.kpi.util.Page;

import java.util.List;

/**
 * Class for receiving and processing report data.
 * @author Vladyslav Avdiienko
 * @version 1.0
 */
public class ReportServiceImpl implements ReportService {

    private final ReportDao reportDao;

    {
        reportDao = new ReportDaoImpl();
    }

    /** Create new individual person report with given data.
     * @param report New report data to save.
     * @see ReportDto
     * @return Creating result.
     */
    @Override
    public boolean createIndividualPersonReport(ReportDto report) {
        return reportDao.saveIndividualPersonReport(report);
    }

    /** Create new legal entity report with given data.
     * @param report New report data to save.
     * @see ReportDto
     * @return Creating result.
     */
    @Override
    public boolean createLegalEntityReport(ReportDto report) {
        return reportDao.saveLegalEntityReport(report);
    }

    /** Returns user submitted reports.
     * @param id User id.
     * page Page content description
     * @see Page
     * @return Page of reports to display, total number of reports to display..
     * @see PageableReportDto
     */
    @Override
    public PageableReportDto getReportsByUserId(Long id, Page page) {
        PageableReportDto pageableReportDto = new PageableReportDto();
        pageableReportDto.setReportsPage(reportDao.getReportsByUserId(id, page.getCurrentPage(), page.getRecordsPerPage()));
        pageableReportDto.setRowNumber(getReportsNumberByUserId(id));

        return pageableReportDto;
    }

    /** Return report with given id.
     * @param id
     * @return Report.
     * @see Report
     */
    @Override
    public Report getReportById(Long id) {
        return reportDao.findReportById(id);
    }

    /** Update report data after verifying.
     * @param reportDto Report to update.
     * @see ReportDto
     * @return Updating result.
     */
    @Override
    public boolean updateVerifiedReport(ReportDto reportDto) {
        return reportDao.updateVerifiedReport(reportDto);
    }

    /** Delete report from report table, save to archive.
     * Update report status (rejection_reason, inspector comment).
     * @param reportDto Report to move.
     * @see ReportDto
     * @return Moving result.
     */
    @Override
    public boolean moveReportToArchive(ReportDto reportDto) {
        return reportDao.moveReportToArchive(reportDto);
    }

    /** Set new inspector to report if possible.
     * @param reportId Report in which the inspector is replaced.
     * @return Replacing result.
     */
    @Override
    public boolean setReplacedInspector(Long reportId) {
        Long oldInspectorId = getReportById(reportId).getInspectorId();
        Long newInspectorId = getInspectorIdWithLeastReportsNumber(reportId);
        return reportDao.setReplacedInspector(reportId, oldInspectorId, newInspectorId);
    }

    /** Return inspector id with least reports number.
     * @param reportId Report id to exclude already replaced inspectors.
     * @return Inspector id.
     */
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

    /** Check possibility of replacing the inspector.
     * @param reportId Report id  to check the possibility of replacing the inspector.
     * @return Checking result.
     */
    @Override
    public boolean isPossiblyToReplaceInspector(Long reportId) {
        List<Long> inspectors = reportDao.getAllInspectorIds();
        System.out.println("All inspectors: " + inspectors);
        List<Long> replacedInspectors = reportDao.getReplacedInspectorsByReportId(reportId);
        System.out.println("Replaced inspectors: " + replacedInspectors);

        return (inspectors.size() - replacedInspectors.size()) > 1;
    }

    /** Returns total user submitted reports number.
     * @param userId
     * @return Total user submitted reports number
     */
    @Override
    public int getReportsNumberByUserId(Long userId) {
        return reportDao.getReportsNumberByUserId(userId);
    }

    /** Returns number of reports that the inspector should check.
     * @param inspectorId
     * @return Number of reports that the inspector should check.
     */
    @Override
    public int getVerificationReportsNumberByInspectorId(Long inspectorId) {
        return reportDao.getVerificationReportsNumberByInspectorId(inspectorId);
    }

    /** Update report data.
     * @param reportDto Report to update.
     * @see ReportDto
     * @return Updating result.
     */
    @Override
    public boolean updateReport(ReportDto reportDto) {
        return reportDao.updateReport(reportDto);
    }

    /** Verification of the report and its further processing.
     * @param reportDto Report to verify.
     * @see ReportDto
     * @param reportStatus Report status as set by the inspector.
     * @param rejectionReason Rejection reason status as set by the inspector.
     * @param comment Inspector comment.
     */
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
