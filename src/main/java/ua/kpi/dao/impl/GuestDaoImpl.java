package ua.kpi.dao.impl;

import ua.kpi.controller.exception.UserAlreadyExistsException;
import ua.kpi.dao.ConnectionPool;
import ua.kpi.dao.GuestDao;
import ua.kpi.dao.Mapper;
import ua.kpi.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GuestDaoImpl implements GuestDao {

    private final ConnectionPool connectionPool;

    private final ResourceBundle queries;

    {
        connectionPool = new PGConnectionPool();
        queries = ResourceBundle.getBundle("sql-queries");
    }

    @Override
    public boolean isUserExistsWithUsername(String username) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection
                     .prepareStatement(queries.getString("is.exists.user.with.username"));) {

            ps.setString(1,username);

            return ps.executeQuery().next();

        } catch (SQLException ex) {
            throw new UserAlreadyExistsException("User: " + username + " already exists.");
        }
    }

    @Override
    public boolean createUser(UserDto user) {
        try (Connection connection = connectionPool.getConnection();
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
}
