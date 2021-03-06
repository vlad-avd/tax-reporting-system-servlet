package ua.kpi.dao.impl;

import ua.kpi.dao.Mapper;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.RejectionReason;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.model.enums.Role;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapperImpl implements Mapper {
    @Override
    public User extractUser(ResultSet rs) throws SQLException {
        return User.newBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString( "password"))
                .setRole(Role.valueOf(rs.getString("role")))
                .build();
    }

    @Override
    public Report extractReport(ResultSet resultSet) throws SQLException {
            String rejectReason = resultSet.getString("rejection_reason");
            RejectionReason rejectionReason = null;
            if (rejectReason != null) {
                rejectionReason = RejectionReason.valueOf(rejectReason);
            }

            return Report.newBuilder()
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
                    .setComment(resultSet.getString("inspector_comment"))
                    .setCreated(resultSet.getDate("created").toLocalDate())
                    .setRejectionReason(rejectionReason)
                    .build();
    }
}
