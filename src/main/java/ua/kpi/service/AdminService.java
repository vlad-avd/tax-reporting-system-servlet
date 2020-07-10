package ua.kpi.service;

import ua.kpi.dto.PageableReportDto;
import ua.kpi.dto.PageableUserDto;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.util.Page;

import java.util.List;

public interface AdminService {

    PageableUserDto getAllUsers(Page page);

    StatisticsDto getStatistics(Long userId);

    int getUsersNumber();

    List<Report> getAllReports(int currentPage, int recordsPerPage);

    int getReportsNumber();

    PageableReportDto getFilteredReports(Page page, String sortByDate, String sortByReportStatus);

    int getFilteredReportsNumber(String sortByReportStatus);
}
