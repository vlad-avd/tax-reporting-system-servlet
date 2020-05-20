package ua.kpi.dao;

import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;

import java.util.List;

public interface ReportDao {
    boolean saveIndividualPersonReport(ReportDto reportDto);

    boolean saveLegalEntityReport(ReportDto reportDto);

    List<Report> getReportsByUserId(Long id);

    Report findReportById(Long id);

    List<Report> getVerificationReports(Long inspectorId);
}
