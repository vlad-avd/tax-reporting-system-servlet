package ua.kpi.dao;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.db.Mapper;
import ua.kpi.db.MapperImpl;
import ua.kpi.db.PGConnectionPool;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.Report;
import ua.kpi.model.entity.User;
import ua.kpi.model.enums.PersonType;
import ua.kpi.model.enums.ReportStatus;
import ua.kpi.model.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Connection connection;

    private Mapper mapper = new MapperImpl();

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
    public User getUserById(Long id) {
        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "SELECT * FROM USR WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapper.extractUser(rs) : null;
            }
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            connection = PGConnectionPool.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String query = "SELECT * FROM USR";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet resultSet =  ps.executeQuery();

            while (resultSet.next()) {
                users.add(mapper.extractUser(resultSet));
            }
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
        return users;
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
