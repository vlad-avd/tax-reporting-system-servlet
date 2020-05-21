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

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDaoImpl implements UserDao {

    private DataSource dataSource;

    private Mapper mapper;

    private ResourceBundle queries;

    {
        dataSource = PGConnectionPool.getInstance();
        queries = ResourceBundle.getBundle("sql-queries");
        mapper = new MapperImpl();
    }

    @Override
    public User getUserByUsername(String username) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.user.by.username"));) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapper.extractUser(rs) : null;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public User getUserById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.user.by.id"));) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
                return rs.next() ? mapper.extractUser(rs) : null;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.users"));) {
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("create.user"));) {
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("set.user.role"));) {

            ps.setLong(1, id);
            ps.setString(2, role.toString());
            ps.execute();
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }
}
