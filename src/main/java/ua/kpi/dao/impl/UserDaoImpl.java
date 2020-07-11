package ua.kpi.dao.impl;

import ua.kpi.controller.exception.SqlRuntimeException;
import ua.kpi.controller.exception.UserAlreadyExistsException;
import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.Mapper;
import ua.kpi.dao.UserDao;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDaoImpl implements UserDao {

    private final DataSource dataSource;

    private final Mapper mapper;

    private final ResourceBundle queries;

    {
        dataSource = PGConnectionPool.getInstance();
        queries = ResourceBundle.getBundle("sql-queries");
        mapper = new MapperImpl();
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.user.by.username"));) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapper.extractUser(rs) : null;
        } catch (SQLException ex) {
            throw new UserNotFoundException("User: " + username + "was not found.");
        }
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("get.user.by.id"));) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
                return rs.next() ? mapper.extractUser(rs) : null;
        } catch (SQLException ex) {
            throw new UserNotFoundException("User: " + id + "was not found.");
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
            throw new UserAlreadyExistsException("User: " + user.getUsername() + " already exists.");
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

    @Override
    public boolean isUserExistsWithUsername(String username) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("is.exists.user.with.username"));) {

            ps.setString(1,username);

            return ps.executeQuery().next();

        } catch (SQLException ex) {
            throw new UserAlreadyExistsException("User: " + username + " already exists.");
        }
    }

    @Override
    public boolean areUsernameAndPasswordCorrect(String username, String password) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("are.username.and.password.correct"));) {

            ps.setString(1,username);
            ps.setString(2, password);

            return ps.executeQuery().next();

        } catch (SQLException ex) {
            throw new SqlRuntimeException(ex);
        }
    }
}
