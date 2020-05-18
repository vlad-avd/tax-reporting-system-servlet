package ua.kpi.service;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.ReportDaoImpl;
import ua.kpi.dto.ReportDto;

public class ReportServiceImpl implements ReportService {

    private ReportDao reportDao;

    {
        reportDao = new ReportDaoImpl();
    }

    @Override
    public boolean createIndividualPersonReport(ReportDto reportDto) {
        return reportDao.saveIndividualPersonReport(reportDto);
    }

    @Override
    public boolean createLegalEntityReport(ReportDto reportDto) {
        return reportDao.saveLegalEntityReport(reportDto);
    }
}
