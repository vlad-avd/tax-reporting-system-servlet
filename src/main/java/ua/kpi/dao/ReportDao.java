package ua.kpi.dao;

import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.util.Page;

import java.util.List;

public interface ReportDao {
    boolean saveIndividualPersonReport(ReportDto reportDto);

    boolean saveLegalEntityReport(ReportDto reportDto);

    List<Report> getReportsByUserId(Long id, int currentPage, int recordsPerPage);

    List<Report> getAllReportsByUserId(Long id);

    Report findReportById(Long id);

    int getReportsNumber();

    int getReportsNumberByUserId(Long userId);

    boolean updateReport(ReportDto reportDto);
}
