package ua.kpi.service;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.ReportDaoImpl;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.ReportStatus;

import java.util.List;

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

    @Override
    public List<Report> getReportsByUserId(Long id, int currentPage, int recordsPerPage) {
        return reportDao.getReportsByUserId(id, currentPage, recordsPerPage);
    }

    @Override
    public List<Report> getReportsByUserId(Long userId) {
        return reportDao.getReportsByUserId(userId);
    }

    @Override
    public Report getReportById(Long id) {
        return reportDao.findReportById(id);
    }

    @Override
    public boolean updateVerifiedReport(ReportDto reportDto) {
        return reportDao.updateVerifiedReport(reportDto);
    }

    @Override
    public boolean moveReportToArchive(ReportDto reportDto) {
        return reportDao.moveReportToArchive(reportDto);
    }

    @Override
    public boolean setReplacedInspector(Long reportId) {
        Long oldInspectorId = getReportById(reportId).getInspectorId();
        Long newInspectorId = getInspectorIdWithLeastReportsNumber(reportId);
        return reportDao.setReplacedInspector(reportId, oldInspectorId, newInspectorId);
    }

    @Override
    public Long getInspectorIdWithLeastReportsNumber(Long reportId) {
        List<Long> inspectors = reportDao.getAllInspectorIds();
        List<Long> replacedInspectors = reportDao.getReplacedInspectorsByReportId(reportId);
        inspectors.removeAll(replacedInspectors);
        List<Long> inspectorIdsInReports = reportDao.getAllInspectorIdsFromReports();

        Long inspectorWithLeastReportsNumber = inspectors.get(0);
        long reportsNumber = inspectorIdsInReports.stream()
                .filter(inspectors.get(0)::equals)
                .count();

        for(Long inspector : inspectors){
            long rN = inspectorIdsInReports.stream()
                    .filter(inspector::equals)
                    .count();

            if(rN < reportsNumber){
                reportsNumber = rN;
                inspectorWithLeastReportsNumber = inspector;
            }
        }

        return  inspectorWithLeastReportsNumber;
    }

    @Override
    public boolean isPossiblyToReplaceInspector(Long reportId) {
        List<Long> inspectors = reportDao.getAllInspectorIds();
        System.out.println("All inspectors: " + inspectors);
        List<Long> replacedInspectors = reportDao.getReplacedInspectorsByReportId(reportId);
        System.out.println("Replaced inspectors: " + replacedInspectors);

        return (inspectors.size() - replacedInspectors.size()) > 1;
    }

    @Override
    public int getReportsNumber() {
        return reportDao.getReportsNumber();
    }

    @Override
    public int getReportsNumberByUserId(Long userId) {
        return reportDao.getReportsNumberByUserId(userId);
    }
}
