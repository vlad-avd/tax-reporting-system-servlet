package ua.kpi.dao;

import ua.kpi.dto.ReportDto;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.ReportStatus;

import java.util.List;

public interface ReportDao {
    boolean saveIndividualPersonReport(ReportDto reportDto);

    boolean saveLegalEntityReport(ReportDto reportDto);

    List<Report> getReportsByUserId(Long id);

    Report findReportById(Long id);

    List<Report> getVerificationReports(Long inspectorId);

    boolean updateVerifiedReport(ReportDto reportDto);

    boolean setReplacedInspector(Long reportId, Long oldInspectorId, Long newInspectorId);

    List<Long> getAllInspectorIds();

    List<Long> getAllInspectorIdsFromReports();

    List<Long> getReplacedInspectorsByReportId(Long reportId);

    boolean updateReportInspectorId(Long reportId, Long inspectorId);
}
