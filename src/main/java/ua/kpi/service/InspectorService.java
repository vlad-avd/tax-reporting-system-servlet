package ua.kpi.service;

import ua.kpi.model.entity.Report;

import java.util.List;

public interface InspectorService {
    List<Report> getVerificationReport(Long inspectorId);
}
