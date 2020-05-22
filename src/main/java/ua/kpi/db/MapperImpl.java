package ua.kpi.db;

import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.PersonType;
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

        Date lastEdit = resultSet.getDate("last_edit");
        LocalDate lastEditDate = null;
        if(lastEdit != null){
            lastEditDate = lastEdit.toLocalDate();
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
                .setInspectorId(resultSet.getLong("inspector_id"))
                .setComment(resultSet.getString("inspector_comment"))
                .setCreated(resultSet.getDate("created").toLocalDate())
                .setLastEdit(lastEditDate)
                .build();
    }
}
