package ua.kpi.service;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.ReportDaoImpl;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.UserDaoImpl;
import ua.kpi.db.ConnectionPool;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.model.entity.Report;

import java.util.List;

public class InspectorServiceImpl implements InspectorService {

    private ReportDao reportDao;

    {
        reportDao = new ReportDaoImpl();
    }

    @Override
    public List<Report> getVerificationReport(Long inspectorId) {
        return reportDao.getVerificationReports(inspectorId);
    }
}
