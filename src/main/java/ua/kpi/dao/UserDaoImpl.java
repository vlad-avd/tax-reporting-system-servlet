package ua.kpi.dao;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.db.Mapper;
import ua.kpi.db.MapperImp;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.Role;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    private Mapper mapper = new MapperImp();

//    public UserDaoImpl(){
//        try {
//            Connection connection = PGConnectionPool.getInstance().getConnection();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    @Override
    public User getUserByUsername(String username) {
        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "SELECT * FROM USR WHERE username = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractUser(rs) : null;
            }
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public boolean createUser(UserDto user) {
        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "INSERT INTO USR(username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public void setUserRole(Long id, Role role) {
        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "INSERT INTO USER_ROLE VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.setString(2, role.toString());
            ps.execute();
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }
}
