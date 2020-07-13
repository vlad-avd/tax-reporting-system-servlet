package ua.kpi.service;

import ua.kpi.dto.PageableReportDto;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.util.Page;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    boolean createIndividualPersonReport(ReportDto reportDto);

    boolean createLegalEntityReport(ReportDto reportDto);

    PageableReportDto getReportsByUserId(Long id, Page page);

    Optional<Report> getReportById(Long id);

    boolean updateVerifiedReport(ReportDto reportDto);

    boolean moveReportToArchive(ReportDto reportDto);

    boolean setReplacedInspector(Long reportId);

    Long getInspectorIdWithLeastReportsNumber(Long reportId, boolean createReport);

    boolean isPossiblyToReplaceInspector(Long reportId);

    int getReportsNumberByUserId(Long userId);

    int getVerificationReportsNumberByInspectorId(Long inspectorId);

    boolean updateReport(ReportDto reportDto);

    void verifyReport(ReportDto reportDto, String reportStatus, String rejectionReason, String comment);
}
