package ua.kpi.service;

import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;

import java.util.List;

public interface ReportService {
    boolean createIndividualPersonReport(ReportDto reportDto);

    boolean createLegalEntityReport(ReportDto reportDto);

    List<Report> getReportsByUserId(Long id);

    Report getReportById(Long id);
}
