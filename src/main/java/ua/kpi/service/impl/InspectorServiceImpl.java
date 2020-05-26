package ua.kpi.service.impl;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.impl.ReportDaoImpl;
import ua.kpi.model.entity.Report;
import ua.kpi.service.InspectorService;

import java.util.List;

public class InspectorServiceImpl implements InspectorService {

    private ReportDao reportDao;

    {
        reportDao = new ReportDaoImpl();
    }

    @Override
    public List<Report> getVerificationReport(Long inspectorId, int currentPage, int recordsPerPage) {
        return reportDao.getVerificationReports(inspectorId, currentPage, recordsPerPage);
    }
}
