package ua.kpi.dao;

import ua.kpi.dto.ReportDto;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.ReportStatus;

import java.util.List;

public interface ReportDao {
    boolean saveIndividualPersonReport(ReportDto reportDto);

    boolean saveLegalEntityReport(ReportDto reportDto);

    List<Report> getReportsByUserId(Long id, int currentPage, int recordsPerPage);

    List<Report> getReportsByUserId(Long id);

    Report findReportById(Long id);

    List<Report> getVerificationReports(Long inspectorId, int currentPage, int recordsPerPage);

    int getVerificationReportsNumberByInspectorId(Long inspectorId);

    boolean updateVerifiedReport(ReportDto reportDto);

    boolean moveReportToArchive(ReportDto reportDto);

    boolean setReplacedInspector(Long reportId, Long oldInspectorId, Long newInspectorId);

    List<Long> getAllInspectorIds();

    List<Long> getAllInspectorIdsFromReports();

    List<Long> getReplacedInspectorsByReportId(Long reportId);

    int getReportsNumber();

    int getReportsNumberByUserId(Long userId);

    List<Report> getAllReports(int currentPage, int recordsPerPage);

    List<Report> getFilteredReports(String sortByDate, String sortByReportStatus, int currentPage, int recordsPerPage);

    int getFilteredReportsNumber(String sortByReportStatus);

    boolean updateReportContent(Long reportId, ReportDto reportDto);
}
