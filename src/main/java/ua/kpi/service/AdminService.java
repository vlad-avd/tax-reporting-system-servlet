package ua.kpi.service;

import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    User getUserById(Long id);

    List<User> getAllUsers(int currentPage, int recordsPerPage);

    StatisticsDto getStatistics(Long userId);

    int getUsersNumber();

    List<Report> getAllReports(int currentPage, int recordsPerPage);

    int getReportsNumber();

    List<Report> getFilteredReports(String sortByDate, String sortByReportStatus, int currentPage, int recordsPerPage);

    int getFilteredReportsNumber(String sortByReportStatus);
}
