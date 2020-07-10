package ua.kpi.service;

import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;

import java.util.List;

public interface ReportService {
    boolean createIndividualPersonReport(ReportDto reportDto);

    boolean createLegalEntityReport(ReportDto reportDto);

    List<Report> getReportsByUserId(Long id, int currentPage, int recordsPerPage);

    List<Report> getReportsByUserId(Long userId);

    Report getReportById(Long id);

    boolean updateVerifiedReport(ReportDto reportDto);

    boolean moveReportToArchive(ReportDto reportDto);

    boolean setReplacedInspector(Long reportId);

    Long getInspectorIdWithLeastReportsNumber(Long reportId);

    boolean isPossiblyToReplaceInspector(Long reportId);

    int getReportsNumberByUserId(Long userId);

    int getVerificationReportsNumberByInspectorId(Long inspectorId);

    boolean updateReportContent(Long reportId, ReportDto reportDto);

    void verifyReport(ReportDto reportDto, String reportStatus, String rejectionReason, String comment);
}
