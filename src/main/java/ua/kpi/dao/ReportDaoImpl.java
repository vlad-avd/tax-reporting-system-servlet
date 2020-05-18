package ua.kpi.dao;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.ReportDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.ReportStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {

    private Connection connection;

    @Override
    public boolean saveIndividualPersonReport(ReportDto reportDto) {
        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "INSERT INTO REPORT(full_name, workplace, salary, taxpayer_id, person_type, report_status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
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
        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "INSERT INTO REPORT(company_name, financial_turnover, taxpayer_id, person_type, report_status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
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

        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String query = "SELECT * FROM REPORT WHERE taxpayer_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
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
}
