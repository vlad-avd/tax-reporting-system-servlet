package ua.kpi.dao;

import ua.kpi.dto.ReportDto;

public interface ReportDao {
    boolean saveIndividualPersonReport(ReportDto reportDto);
}
