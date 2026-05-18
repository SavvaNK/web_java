package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс построителя соединения с базой данных PostgreSQL.
 */
public class DbConnectionBuilder implements ConnectionBuilder {
    public DbConnectionBuilder() {
        try {
            ConnectionProperty.loadProperties();
            Class.forName(ConnectionProperty.getProperty("db.driver.class"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");
        return DriverManager.getConnection(url, login, password);
    }
}
