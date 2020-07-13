package ua.kpi.dao;

import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;

import java.util.List;

public interface AdminDao {
    List<User> getAllUsers(int currentPage, int recordsPerPage);

    int getUsersNumber();

    List<Report> getAllReports(int currentPage, int recordsPerPage);

    List<Report> getFilteredReports(String sortByDate, String sortByReportStatus, int currentPage, int recordsPerPage);

    int getFilteredReportsNumber(String sortByReportStatus);
}
