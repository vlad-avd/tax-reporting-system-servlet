package ua.kpi.dao.impl;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.dao.UserDao;
import ua.kpi.dao.Mapper;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;
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
    public List<User> getAllUsers(int currentPage, int recordsPerPage) {
        List<User> users = new ArrayList<>();

        int startIndex = currentPage * recordsPerPage - recordsPerPage;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.all.users"));) {

            ps.setInt(1, recordsPerPage);
            ps.setInt(2, startIndex);

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

    @Override
    public boolean updateUser(UserDto userDto) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("update.user"));) {
            ps.setString(1, userDto.getUsername());
            ps.setString(2, userDto.getPassword());
            ps.setString(3, userDto.getRole().toString());
            ps.setLong(4, userDto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }

    @Override
    public int getUsersNumber() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.users.number"));) {

            ResultSet rs = ps.executeQuery();

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }
}
