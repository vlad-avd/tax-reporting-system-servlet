package ua.kpi.dao.impl;

import ua.kpi.controller.exception.ReportNotFoundException;
import ua.kpi.dao.ConnectionPool;
import ua.kpi.dao.Mapper;
import ua.kpi.dao.ReportDao;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.util.Page;

import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReportDaoImpl implements ReportDao {

    private final ConnectionPool connectionPool;

    private final Mapper mapper;

    private final ResourceBundle queries;

    {
        connectionPool = new PGConnectionPool();
        queries = ResourceBundle.getBundle("sql-queries");
        mapper = new MapperImpl();
    }

    @Override
    public boolean saveIndividualPersonReport(ReportDto reportDto) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("create.individual.person.report"));) {
            ps.setString(1, reportDto.getFullName());
            ps.setString(2, reportDto.getWorkplace());
            ps.setBigDecimal(3, reportDto.getSalary());
            ps.setLong(4, reportDto.getTaxpayerId());
            ps.setString(5, PersonType.INDIVIDUAL_PERSON.toString());
            ps.setString(6, ReportStatus.ON_VERIFYING.toString());
            ps.setDate(7, Date.valueOf(LocalDate.now()));
            ps.setLong(8, reportDto.getInspectorId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean saveLegalEntityReport(ReportDto reportDto) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("create.legal.entity.report"))) {
            ps.setString(1, reportDto.getCompanyName());
            ps.setBigDecimal(2, reportDto.getFinancialTurnover());
            ps.setLong(3, reportDto.getTaxpayerId());
            ps.setString(4, PersonType.LEGAL_ENTITY.toString());
            ps.setString(5, ReportStatus.ON_VERIFYING.toString());
            ps.setDate(6, Date.valueOf(LocalDate.now()));
            ps.setLong(7, reportDto.getInspectorId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Report> getReportsByUserId(Long id, int currentPage, int recordsPerPage) {

        int start = currentPage * recordsPerPage - recordsPerPage;

        List<Report> reports = getAllReportsByUserId(id);

        return reports.stream()
                .sorted((r1, r2) -> Long.compare(r2.getId(), r1.getId()))
                .skip(start)
                .limit(recordsPerPage)
                .collect(Collectors.toList());
    }

    @Override
    public List<Report> getAllReportsByUserId(Long id) {

        List<Report> reports = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps1 = connection
                     .prepareStatement(queries.getString("get.all.reports.by.taxpayer.id"));
             PreparedStatement ps2 = connection
                     .prepareStatement(queries.getString("get.all.reports.from.archive.by.taxpayer.id"));) {

            ps1.setLong(1, id);
            ResultSet resultSet1 =  ps1.executeQuery();

            while (resultSet1.next()) {
                reports.add(mapper.extractReport(resultSet1));
            }

            ps2.setLong(1, id);
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
    public Report findReportById(Long id) {

        Report report = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps1 = connection
                     .prepareStatement(queries.getString("get.report.by.id"));
             PreparedStatement ps2 = connection
                     .prepareStatement(queries.getString("get.report.from.archive.by.id"));) {

            ps1.setLong(1, id);
            ps2.setLong(1, id);

            ResultSet rs1 = ps1.executeQuery();
            ResultSet rs2 = ps2.executeQuery();

            while(rs1.next()){
                report = mapper.extractReport(rs1);
            }

            while(rs2.next()){
                report = mapper.extractReport(rs2);
            }

            return report;

        } catch (SQLException ex) {
            throw new ReportNotFoundException("Report: " + id + " was not found.");
        }
    }

    @Override
    public int getReportsNumber() {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps1 = connection
                     .prepareStatement(queries.getString("get.reports.number"));
             PreparedStatement ps2 = connection
                     .prepareStatement(queries.getString("get.archived.reports.number"));) {

            int reportsNumber = 0;

            ResultSet rs1 = ps1.executeQuery();
            reportsNumber += rs1.next() ? rs1.getInt(1) : 0;

            ResultSet rs2 = ps2.executeQuery();
            reportsNumber += rs2.next() ? rs2.getInt(1) : 0;

            return reportsNumber;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int getReportsNumberByUserId(Long userId) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps1 = connection
                     .prepareStatement(queries.getString("get.reports.number.by.user.id"));
             PreparedStatement ps2 = connection
                     .prepareStatement(queries.getString("get.archived.reports.number.by.user.id"));) {

            int reportsNumber = 0;

            ps1.setLong(1, userId);
            ResultSet rs1 = ps1.executeQuery();
            reportsNumber += rs1.next() ? rs1.getInt(1) : 0;

            ps2.setLong(1, userId);
            ResultSet rs2 = ps2.executeQuery();
            reportsNumber += rs2.next() ? rs2.getInt(1) : 0;

            return reportsNumber;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
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
    public boolean updateReport(ReportDto reportDto) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("update.report.content"));) {

            ps.setString(1, reportDto.getFullName());
            ps.setString(2, reportDto.getWorkplace());
            ps.setBigDecimal(3, reportDto.getSalary());
            ps.setString(4, reportDto.getCompanyName());
            ps.setBigDecimal(5, reportDto.getFinancialTurnover());
            ps.setLong(6, reportDto.getId());

            return ps.execute();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
