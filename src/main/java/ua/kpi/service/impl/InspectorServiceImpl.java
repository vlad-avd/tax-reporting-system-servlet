package ua.kpi.service.impl;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.impl.ReportDaoImpl;
import ua.kpi.dto.PageableReportDto;
import ua.kpi.service.InspectorService;
import ua.kpi.util.Page;

/**
 * Class for receiving and processing data necessary for the inspector.
 * @author Vladyslav Avdiienko
 * @version 1.0
 */
public class InspectorServiceImpl implements InspectorService {

    private final ReportDao reportDao;

    {
        reportDao = new ReportDaoImpl();
    }

    @Override
    public PageableReportDto getVerificationReport(Long inspectorId, Page page) {
        PageableReportDto pageableReportDto = new PageableReportDto();
        pageableReportDto.setReportsPage(reportDao.getVerificationReports(inspectorId, page));
        pageableReportDto.setRowNumber(reportDao.getReportsNumber());

        return  pageableReportDto;
    }
}
