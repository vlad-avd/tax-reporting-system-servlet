package ua.kpi.db;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;
import ua.kpi.controller.action.ActionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PGConnectionPool implements ConnectionPool {

    private static ConnectionPool instance;

    private static DataSource dataSource;

    private PGConnectionPool(){
        ResourceBundle pgProperties = ResourceBundle.getBundle("postgres");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(pgProperties.getString("pg.url"));
        ds.setDriverClassName(pgProperties.getString("pg.driver"));
        ds.setUsername(pgProperties.getString("pg.user"));
        ds.setPassword(pgProperties.getString("pg.pwd"));
        ds.setMinIdle(Integer.parseInt(pgProperties.getString("pg.minIdle")));
        ds.setMaxIdle(Integer.parseInt(pgProperties.getString("pg.maxIdle")));
        ds.setMaxOpenPreparedStatements(Integer.parseInt(pgProperties.getString("pg.maxOpenStatement")));
        dataSource = ds;
    }

    public static ConnectionPool getInstance(){
        if (instance == null)
        {
            instance = new PGConnectionPool();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
