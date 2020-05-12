package ua.kpi.dao;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.ReportDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReportDaoImpl implements ReportDao {

    private Connection connection;

    @Override
    public boolean saveIndividualPersonReport(ReportDto reportDto) {
        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "INSERT INTO REPORT(full_name, workplace, salary) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, reportDto.getFullName());
            ps.setString(2, reportDto.getWorkplace());
            ps.setBigDecimal(3, reportDto.getSalary());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }
}
