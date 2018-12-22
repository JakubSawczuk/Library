package wat.ai.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private MySQLConnection() {
    }

    public static Connection getMySQLConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "library_new";
        String useSSL = "?useSSL=true";
        String userName = "admin";
        String password = "admin";
        return getMySQLConnection(hostName, dbName, userName, password, useSSL);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password, String useSSL) throws SQLException{
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + useSSL;

        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
