package ua.kpi.dao.impl;

import ua.kpi.dao.ConnectionPool;
import ua.kpi.dao.InspectorDao;
import ua.kpi.dao.Mapper;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.util.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InspectorDaoImpl implements InspectorDao {
    private final ConnectionPool connectionPool;

    private final Mapper mapper;

    private final ResourceBundle queries;

    {
        connectionPool = new PGConnectionPool();
        queries = ResourceBundle.getBundle("sql-queries");
        mapper = new MapperImpl();
    }

    @Override
    public List<Report> getVerificationReports(Long inspectorId, Page page) {
        List<Report> reports = new ArrayList<>();

        int startIndex = page.getCurrentPage() * page.getRecordsPerPage() - page.getRecordsPerPage();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.verification.reports"));) {
            ps.setLong(1, inspectorId);
            ps.setInt(2, page.getRecordsPerPage());
            ps.setInt(3, startIndex);

            ResultSet resultSet =  ps.executeQuery();

            while (resultSet.next()) {
                reports.add(mapper.extractReport(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return reports;
    }

    @Override
    public int getVerificationReportsNumberByInspectorId(Long inspectorId) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.verification.reports.number.by.inspector.id"));) {

            ps.setLong(1, inspectorId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean updateVerifiedReport(ReportDto reportDto) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("update.verified.report"));) {
            ps.setString(1, reportDto.getReportStatus().toString());
            ps.setLong(2, reportDto.getInspectorId());
            ps.setString(3, reportDto.getComment());
            if (reportDto.getRejectionReason() != null) {
                ps.setString(4, reportDto.getRejectionReason().toString());
            } else {
                ps.setNull(4, Types.VARCHAR);
            }
            ps.setLong(5, reportDto.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean moveReportToArchive(ReportDto reportDto) {
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps1 = connection
                    .prepareStatement(queries.getString("delete.report"));
                 PreparedStatement ps2 = connection
                         .prepareStatement(queries.getString("add.report.to.archive"));
                 PreparedStatement ps3 = connection
                         .prepareStatement(queries.getString("delete.replaced.inspector"))) {

                connection.setAutoCommit(false);

                ps3.setLong(1, reportDto.getId());
                ps3.executeUpdate();

                ps1.setLong(1, reportDto.getId());
                ps1.executeUpdate();

                ps2.setLong(1, reportDto.getId());
                ps2.setString(2, reportDto.getCompanyName());
                ps2.setBigDecimal(3, reportDto.getFinancialTurnover());
                ps2.setString(4, reportDto.getWorkplace());
                ps2.setBigDecimal(5, reportDto.getSalary());
                ps2.setString(6, reportDto.getFullName());
                ps2.setLong(7, reportDto.getInspectorId());
                ps2.setLong(8, reportDto.getTaxpayerId());
                ps2.setDate(9, Date.valueOf(reportDto.getCreated()));
                ps2.setString(10, reportDto.getReportStatus().toString());
                ps2.setString(11, reportDto.getPersonType().toString());
                if(reportDto.getRejectionReason() != null) {
                    ps2.setString(12, reportDto.getRejectionReason().toString());
                }
                else {
                    ps2.setNull(12, Types.VARCHAR);
                }
                ps2.setString(13, reportDto.getComment());

                ps2.executeUpdate();

                connection.commit();
                return true;
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean setReplacedInspector(Long reportId, Long oldInspectorId, Long newInspectorId) {
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps1 = connection
                    .prepareStatement(queries.getString("set.replaced.inspector"));
                 PreparedStatement ps2 = connection
                         .prepareStatement(queries.getString("update.report.inspector.id"))) {

                connection.setAutoCommit(false);

                Savepoint savepoint = connection.setSavepoint("savepoint");

                ps1.setLong(1, oldInspectorId);
                ps1.setLong(2, reportId);

                ps1.execute();

                ps2.setLong(1, newInspectorId);
                ps2.setLong(2, reportId);

                ps2.executeUpdate();

                connection.commit();

                return true;
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    throw new RuntimeException(ex);
                }
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Long> getAllInspectorIds() {

        List<Long> inspectorIds = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.inspector.ids"));) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inspectorIds.add(rs.getLong(1));
            }

            return inspectorIds;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Long> getAllInspectorIdsFromReports() {
        List<Long> inspectorIds = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.inspector.ids.from.reports"));) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inspectorIds.add(rs.getLong(1));
            }

            return inspectorIds;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Long> getReplacedInspectorsByReportId(Long reportId) {
        List<Long> replacedInspectorIds = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.replaced.inspectors.by.report.id"));) {
            ps.setLong(1, reportId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                replacedInspectorIds.add(rs.getLong(1));
            }

            return replacedInspectorIds;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
