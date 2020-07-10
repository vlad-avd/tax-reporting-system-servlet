package ua.kpi.service;

import ua.kpi.dto.PageableReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.util.Page;

import java.util.List;

public interface InspectorService {
    PageableReportDto getVerificationReport(Long inspectorId, Page page);
}
