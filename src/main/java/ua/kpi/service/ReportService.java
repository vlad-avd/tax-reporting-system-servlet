package ua.kpi.service;

import ua.kpi.dto.ReportDto;

public interface ReportService {
    boolean createIndividualPersonReport(ReportDto reportDto);
}
