package ua.kpi.service.impl;

import ua.kpi.dao.ReportDao;
import ua.kpi.dao.impl.ReportDaoImpl;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.impl.UserDaoImpl;
import ua.kpi.dto.PageableReportDto;
import ua.kpi.dto.PageableUserDto;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.service.AdminService;
import ua.kpi.util.Page;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {

    private final UserDao userDao;
    private final ReportDao reportDao;

    {
        userDao = new UserDaoImpl();
        reportDao = new ReportDaoImpl();
    }

    @Override
    public PageableUserDto getAllUsers(Page page) {
        PageableUserDto pageableUserDto = new PageableUserDto();
        pageableUserDto.setUserPage(userDao.getAllUsers(page.getCurrentPage(), page.getRecordsPerPage()));
        pageableUserDto.setRowNumber(getUsersNumber());

        return  pageableUserDto;
    }

    @Override
    public StatisticsDto getStatistics(Long userId) {
        List<Report> reports = reportDao.getAllReportsByUserId(userId);

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
    public PageableReportDto getFilteredReports(Page page, String sortByDate, String sortByReportStatus) {

        PageableReportDto reports = new PageableReportDto();

        if(sortByDate == null && sortByReportStatus == null){
            sortByDate = "fromNewestToOldest";
            sortByReportStatus = "all";
        }

        if(sortByDate.equals("fromNewestToOldest") && sortByReportStatus.equals("all")) {
            reports.setReportsPage(getAllReports(page.getCurrentPage(), page.getRecordsPerPage()));
            reports.setRowNumber(getReportsNumber());
        } else {
            reports.setReportsPage(reportDao.getFilteredReports(sortByDate, sortByReportStatus, page.getCurrentPage(), page.getRecordsPerPage()));
            reports.setRowNumber(getFilteredReportsNumber(sortByReportStatus));
        }

        return reports;
    }

    @Override
    public int getFilteredReportsNumber(String sortByReportStatus) {
        return reportDao.getFilteredReportsNumber(sortByReportStatus);
    }


}
