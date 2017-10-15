package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.DBException;

public class DBUtil {

    public static Connection createConnection(String jdbcUrl, String username, String password) throws DBException {
        Connection newConn = null;

        try {
            Class.forName("org.postgresql.Driver");
            newConn = DriverManager.getConnection(jdbcUrl, username, password);

            return newConn;
        } catch (ClassNotFoundException | SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}
