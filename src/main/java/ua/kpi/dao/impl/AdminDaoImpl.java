package ua.kpi.dao.impl;

import ua.kpi.dao.AdminDao;
import ua.kpi.dao.ConnectionPool;
import ua.kpi.dao.Mapper;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.ReportStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AdminDaoImpl implements AdminDao {
    private final ConnectionPool connectionPool;

    private final Mapper mapper;

    private final ResourceBundle queries;

    {
        connectionPool = new PGConnectionPool();
        queries = ResourceBundle.getBundle("sql-queries");
        mapper = new MapperImpl();
    }

    @Override
    public int getUsersNumber() {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.users.number"));) {

            ResultSet rs = ps.executeQuery();

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<User> getAllUsers(int currentPage, int recordsPerPage) {
        List<User> users = new ArrayList<>();

        int startIndex = currentPage * recordsPerPage - recordsPerPage;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.users"));) {

            ps.setInt(1, recordsPerPage);
            ps.setInt(2, startIndex);

            ResultSet resultSet =  ps.executeQuery();

            while (resultSet.next()) {
                users.add(mapper.extractUser(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return users;
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps1 = connection
                     .prepareStatement(queries.getString("get.all.reports"));
             PreparedStatement ps2 = connection
                     .prepareStatement(queries.getString("get.all.reports.from.archive"));) {

            ResultSet resultSet1 =  ps1.executeQuery();

            while (resultSet1.next()) {
                reports.add(mapper.extractReport(resultSet1));
            }

            ResultSet resultSet2 =  ps2.executeQuery();

            while (resultSet2.next()) {
                reports.add(mapper.extractReport(resultSet2));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return reports;
    }

    @Override
    public List<Report> getAllReports(int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Report> reports = getAllReports();

        return reports.stream()
                .sorted((r1, r2) -> Long.compare(r2.getId(), r1.getId()))
                .skip(start)
                .limit(recordsPerPage)
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> getFilteredReports(String sortByDate, String sortByReportStatus, int currentPage, int recordsPerPage) {
        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Report> reports = getAllReports();

        reports.sort(Comparator.comparing(Report::getId).reversed());

        reports = filterReports(reports, sortByDate, sortByReportStatus);

        return reports.stream()
                .skip(start)
                .limit(recordsPerPage)
                .collect(Collectors.toList());
    }

    @Override
    public int getFilteredReportsNumber(String sortByReportStatus) {
        List<Report> reports = getAllReports();

        reports = filterReports(reports, "fromOldestToNewest", sortByReportStatus);
        return reports.size();
    }

    public List<Report> filterReports(List<Report> reports, String sortByDate, String sortByReportStatus) {

        if (sortByReportStatus.equals("onVerifying")) {
            reports = reports.stream()
                    .filter(report -> report.getReportStatus().equals(ReportStatus.ON_VERIFYING))
                    .collect(Collectors.toList());
        } else if (sortByReportStatus.equals("needToEdit")) {
            reports = reports.stream()
                    .filter(report -> report.getReportStatus().equals(ReportStatus.NEED_TO_EDIT))
                    .collect(Collectors.toList());
        } else if (sortByReportStatus.equals("approved")) {
            reports = reports.stream()
                    .filter(report -> report.getReportStatus().equals(ReportStatus.APPROVED))
                    .collect(Collectors.toList());
        } else if (sortByReportStatus.equals("rejected")) {
            reports = reports.stream()
                    .filter(report -> report.getReportStatus().equals(ReportStatus.REJECTED))
                    .collect(Collectors.toList());
        }

        if (sortByDate.equals("fromOldestToNewest")) {
            Collections.reverse(reports);
        }

        return reports;
    }
}
