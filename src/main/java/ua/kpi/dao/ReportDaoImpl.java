package ua.kpi.dao;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.db.ConnectionPool;
import ua.kpi.db.Mapper;
import ua.kpi.db.MapperImpl;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.ReportDto;
import ua.kpi.dto.StatisticsDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.ReportStatus;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReportDaoImpl implements ReportDao {

    private DataSource dataSource;

    private Mapper mapper;

    private ResourceBundle queries;

    {
        dataSource = PGConnectionPool.getInstance();
        queries = ResourceBundle.getBundle("sql-queries");
        mapper = new MapperImpl();
    }

    @Override
    public boolean saveIndividualPersonReport(ReportDto reportDto) {
        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public boolean saveLegalEntityReport(ReportDto reportDto) {
        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<Report> getReportsByUserId(Long id, int currentPage, int recordsPerPage) {

        List<Report> reports = new ArrayList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
        return reports.stream().
                skip(start).
                limit(recordsPerPage).
                collect(Collectors.toList());
    }

    @Override
    public List<Report> getReportsByUserId(Long id) {

        List<Report> reports = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
        return reports;
    }

    @Override
    public Report findReportById(Long id) {

        Report report = null;

        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<Report> getVerificationReports(Long inspectorId, int currentPage, int recordsPerPage) {
        List<Report> reports = new ArrayList<>();

        int startIndex = currentPage * recordsPerPage - recordsPerPage;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.verification.reports"));) {
            ps.setLong(1, inspectorId);
            ps.setInt(2, recordsPerPage);
            ps.setInt(3, startIndex);

            ResultSet resultSet =  ps.executeQuery();

            while (resultSet.next()) {
                reports.add(mapper.extractReport(resultSet));
            }
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
        return reports;
    }

    @Override
    public int getVerificationReportsNumberByInspectorId(Long inspectorId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.verification.reports.number.by.inspector.id"));) {

            ps.setLong(1, inspectorId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public boolean updateVerifiedReport(ReportDto reportDto) {
        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public boolean moveReportToArchive(ReportDto reportDto) {
        try (Connection connection = dataSource.getConnection();) {
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
                if(reportDto.getLastEdit() != null) {
                    ps2.setDate(10, Date.valueOf(reportDto.getLastEdit()));
                }
                else {
                    ps2.setNull(10, Types.DATE);
                }
                ps2.setString(11, reportDto.getReportStatus().toString());
                ps2.setString(12, reportDto.getPersonType().toString());
                if(reportDto.getRejectionReason() != null) {
                    ps2.setString(13, reportDto.getRejectionReason().toString());
                }
                else {
                    ps2.setNull(13, Types.VARCHAR);
                }
                ps2.setString(14, reportDto.getComment());

                ps2.executeUpdate();

                connection.commit();
                return true;
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                throw new SqlRuntimeException(ex);
            }
        } catch (SQLException ex){
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public boolean setReplacedInspector(Long reportId, Long oldInspectorId, Long newInspectorId) {
        try (Connection connection = dataSource.getConnection();) {
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
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                throw new SqlRuntimeException(ex);
            }
        } catch (SQLException ex){
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<Long> getAllInspectorIds() {

        List<Long> inspectorIds = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.inspector.ids"));) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inspectorIds.add(rs.getLong(1));
            }

            return inspectorIds;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<Long> getAllInspectorIdsFromReports() {
        List<Long> inspectorIds = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.inspector.ids.from.reports"));) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inspectorIds.add(rs.getLong(1));
            }

            return inspectorIds;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<Long> getReplacedInspectorsByReportId(Long reportId) {
        List<Long> replacedInspectorIds = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.replaced.inspectors.by.report.id"));) {
            ps.setLong(1, reportId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                replacedInspectorIds.add(rs.getLong(1));
            }

            return replacedInspectorIds;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public int getReportsNumber() {
        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public int getReportsNumberByUserId(Long userId) {
        try (Connection connection = dataSource.getConnection();
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
            throw new SqlRuntimeException(ex);
        }
    }
}
