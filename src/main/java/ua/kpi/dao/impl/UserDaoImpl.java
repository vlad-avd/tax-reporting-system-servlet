package ua.kpi.dao.impl;

import ua.kpi.controller.exception.UserAlreadyExistsException;
import ua.kpi.controller.exception.UserNotFoundException;
import ua.kpi.dao.ConnectionPool;
import ua.kpi.dao.Mapper;
import ua.kpi.dao.UserDao;
import ua.kpi.dto.UserDto;
import ua.kpi.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDaoImpl implements UserDao {

    private final ConnectionPool connectionPool;

    private final Mapper mapper;

    private final ResourceBundle queries;

    {
        connectionPool = new PGConnectionPool();
        queries = ResourceBundle.getBundle("sql-queries");
        mapper = new MapperImpl();
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        try (Connection connection = connectionPool.getConnection();
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
        try (Connection connection = connectionPool.getConnection();
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
    public boolean updateUser(UserDto userDto) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("update.user"));) {
            ps.setString(1, userDto.getUsername());
            ps.setString(2, userDto.getPassword());
            ps.setString(3, userDto.getRole().toString());
            ps.setLong(4, userDto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean areUsernameAndPasswordCorrect(String username, String password) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("are.username.and.password.correct"));) {

            ps.setString(1,username);
            ps.setString(2, password);

            return ps.executeQuery().next();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
