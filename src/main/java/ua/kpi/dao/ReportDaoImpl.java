package ua.kpi.dao;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.db.ConnectionPool;
import ua.kpi.db.Mapper;
import ua.kpi.db.MapperImpl;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.ReportStatus;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<Report> getReportsByUserId(Long id) {

        List<Report> reports = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.reports.by.taxpayer.id"));) {
            ps.setLong(1, id);
            ResultSet resultSet =  ps.executeQuery();

            while (resultSet.next()) {
                Report report = Report.newBuilder()
                        .setId(resultSet.getLong("id"))
                        .setCompanyName(resultSet.getString("company_name"))
                        .setFinancialTurnover(resultSet.getBigDecimal("financial_turnover"))
                        .setFullName(resultSet.getString("full_name"))
                        .setInspectorId(resultSet.getLong("inspector_id"))
                        .setPersonType(PersonType.valueOf(resultSet.getString("person_type")))
                        .setReportStatus(ReportStatus.valueOf(resultSet.getString("report_status")))
                        .setSalary(resultSet.getBigDecimal("salary"))
                        .setTaxpayerId(resultSet.getLong("taxpayer_id"))
                        .setWorkplace(resultSet.getString("workplace"))
                        .build();
                reports.add(report);
            }
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
        return reports;
    }

    @Override
    public Report findReportById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.report.by.id"));) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
                return rs.next() ? mapper.extractReport(rs) : null;

        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<Report> getVerificationReports(Long inspectorId) {
        List<Report> reports = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.verification.reports"));) {
            ps.setLong(1, inspectorId);
            ResultSet resultSet =  ps.executeQuery();

            while (resultSet.next()) {
                Report report = Report.newBuilder()
                        .setId(resultSet.getLong("id"))
                        .setCompanyName(resultSet.getString("company_name"))
                        .setFinancialTurnover(resultSet.getBigDecimal("financial_turnover"))
                        .setFullName(resultSet.getString("full_name"))
                        .setInspectorId(resultSet.getLong("inspector_id"))
                        .setPersonType(PersonType.valueOf(resultSet.getString("person_type")))
                        .setReportStatus(ReportStatus.valueOf(resultSet.getString("report_status")))
                        .setSalary(resultSet.getBigDecimal("salary"))
                        .setTaxpayerId(resultSet.getLong("taxpayer_id"))
                        .setWorkplace(resultSet.getString("workplace"))
                        .build();
                reports.add(report);
            }
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
        return reports;
    }

    @Override
    public boolean updateVerifiedReport(ReportDto reportDto) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("update.verified.report"));) {
            ps.setString(1, reportDto.getReportStatus().toString());
            ps.setLong(2, reportDto.getInspectorId());
            ps.setString(3, reportDto.getComment());
            if(reportDto.getRejectionReason() != null){
                ps.setString(4, reportDto.getRejectionReason().toString());
            }
            else {
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
    public boolean setReplacedInspector(Long reportId, Long inspectorId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("set.replaced.inspector"));) {
            ps.setLong(1, inspectorId);
            ps.setLong(2, reportId);

            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }
}
