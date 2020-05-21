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

    private static DataSource dataSource;

    private static DataSource setProps(){
        ResourceBundle pgProperties = ResourceBundle.getBundle("postgres");
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(pgProperties.getString("pg.url"));
        ds.setDriverClassName(pgProperties.getString("pg.driver"));
        ds.setUsername(pgProperties.getString("pg.user"));
        ds.setPassword(pgProperties.getString("pg.pwd"));
        ds.setMinIdle(Integer.parseInt(pgProperties.getString("pg.minIdle")));
        ds.setMaxIdle(Integer.parseInt(pgProperties.getString("pg.maxIdle")));
        ds.setMaxOpenPreparedStatements(Integer.parseInt(pgProperties.getString("pg.maxOpenStatement")));
        return ds;
    }

    public static DataSource getInstance(){
        if (dataSource == null)
        {
            synchronized (PGConnectionPool.class) {
                if(dataSource == null) {
                    dataSource = setProps();
                }
            }
        }
        return dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
