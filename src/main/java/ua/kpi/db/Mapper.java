package ua.kpi.db;

import ua.kpi.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper {
    User extractUser(ResultSet rs) throws SQLException;
}
