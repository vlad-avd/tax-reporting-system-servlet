package ua.kpi.dao;

import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.util.Page;

import java.util.List;

public interface InspectorDao {
    List<Report> getVerificationReports(Long inspectorId, Page page);

    int getVerificationReportsNumberByInspectorId(Long inspectorId);

    boolean updateVerifiedReport(ReportDto reportDto);

    boolean moveReportToArchive(ReportDto reportDto);

    boolean setReplacedInspector(Long reportId, Long oldInspectorId, Long newInspectorId);

    List<Long> getAllInspectorIds();

    List<Long> getAllInspectorIdsFromReports();

    List<Long> getReplacedInspectorsByReportId(Long reportId);
}
