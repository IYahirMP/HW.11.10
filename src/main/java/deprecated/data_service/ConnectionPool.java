package deprecated.data_service;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static MysqlDataSource dataSource;

    // Initialize the DataSource
    public static void initializeDataSource(String url, String username, String password) {
        if (dataSource == null) {
            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    dataSource.setUrl(url);
                    dataSource.setUser(username);
                    dataSource.setPassword(password);
                }
            }
        }
    }

    // Get a connection from the DataSource
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
