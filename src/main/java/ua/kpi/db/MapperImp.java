package ua.kpi.db;

import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class MapperImp implements Mapper {
    @Override
    public User extractUser(ResultSet rs) throws SQLException {
        return User.newBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString( "password"))
                .setRole(Role.valueOf(rs.getString("role")))
                .build();
    }
}
