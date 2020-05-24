package ua.kpi.service;

import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.ReportStatus;

import java.util.List;

public interface ReportService {
    boolean createIndividualPersonReport(ReportDto reportDto);

    boolean createLegalEntityReport(ReportDto reportDto);

    List<Report> getReportsByUserId(Long id);

    Report getReportById(Long id);

    boolean updateVerifiedReport(ReportDto reportDto);

    boolean moveReportToArchive(ReportDto reportDto);

    boolean setReplacedInspector(Long reportId);

    Long getInspectorIdWithLeastReportsNumber(Long reportId);

    boolean isPossiblyToReplaceInspector(Long reportId);
}
