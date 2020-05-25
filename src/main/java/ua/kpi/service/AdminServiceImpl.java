package ua.kpi.service;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.ReportDaoImpl;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.UserDaoImpl;
import ua.kpi.db.ConnectionPool;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.ReportStatus;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {

    //private ConnectionPool connectionPool = PGConnectionPool.getInstance();

    private UserDao userDao;
    private ReportDao reportDao;

    {
        userDao = new UserDaoImpl();
        reportDao = new ReportDaoImpl();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers(int currentPage, int recordsPerPage) {
        return userDao.getAllUsers(currentPage, recordsPerPage);
    }

    @Override
    public StatisticsDto getStatistics(Long userId) {
        List<Report> reports = reportDao.getReportsByUserId(userId);

        int reportsNumber = reports.size();

        List<LocalDate> createdDates = reports.stream()
                        .map(Report::getCreated)
                        .collect(Collectors.toList());

        LocalDate firstReportDate = createdDates.stream()
                .min(Comparator.comparing(LocalDate::toEpochDay))
                .orElse(null);

        LocalDate lastReportDate = createdDates.stream()
                .max(Comparator.comparing(LocalDate::toEpochDay))
                .orElse(null);

        List<ReportStatus> reportStatuses = reports.stream()
                .map(Report::getReportStatus)
                .collect(Collectors.toList());

        int onVerifyingReportsNumber = Math.toIntExact(reportStatuses.stream()
                .filter(reportStatus -> reportStatus.equals(ReportStatus.ON_VERIFYING))
                .count());

        int needToEditReportsNumber = Math.toIntExact(reportStatuses.stream()
                .filter(reportStatus -> reportStatus.equals(ReportStatus.NEED_TO_EDIT))
                .count());

        int approvedReportsNumber = Math.toIntExact(reportStatuses.stream()
                .filter(reportStatus -> reportStatus.equals(ReportStatus.APPROVED))
                .count());

        int rejectedReportsNumber = Math.toIntExact(reportStatuses.stream()
                .filter(reportStatus -> reportStatus.equals(ReportStatus.REJECTED))
                .count());

        return StatisticsDto.newBuilder()
                .setReportsNumber(reportsNumber)
                .setFirstReportDate(firstReportDate)
                .setLastReportDate(lastReportDate)
                .setOnVerifyingReportsNumber(onVerifyingReportsNumber)
                .setNeedToEditReportsNumber(needToEditReportsNumber)
                .setApprovedReportsNumber(approvedReportsNumber)
                .setRejectedReportsNumber(rejectedReportsNumber)
                .build();
    }

    @Override
    public int getUsersNumber() {
        return userDao.getUsersNumber();
    }

    @Override
    public List<Report> getAllReports(int currentPage, int recordsPerPage) {
        return reportDao.getAllReports(currentPage, recordsPerPage);
    }

    @Override
    public int getReportsNumber() {
        return reportDao.getReportsNumber();
    }

    @Override
    public List<Report> getFilteredReports(String sortByDate, String sortByReportStatus, int currentPage, int recordsPerPage) {
        return reportDao.getFilteredReports(sortByDate, sortByReportStatus, currentPage, recordsPerPage);
    }

    @Override
    public int getFilteredReportsNumber(String sortByReportStatus) {
        return reportDao.getFilteredReportsNumber(sortByReportStatus);
    }
}
