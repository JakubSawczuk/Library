package wat.ai.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

    public DatabaseConnection() {
    }

    private static HashMap<String, String> getDatabaseProperties() {
        HashMap<String, String> propertiesMap = new HashMap<>();
        Properties properties = new Properties();
        try {
            InputStream input = DatabaseConnection.class.getResourceAsStream("/application.properties");
            properties.load(input);
            propertiesMap.put("url", properties.getProperty("spring.datasource.url"));
            propertiesMap.put("user", properties.getProperty("spring.datasource.username"));
            propertiesMap.put("password", properties.getProperty("spring.datasource.password"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        return propertiesMap;
    }

    public static Connection getMySQLConnection() throws SQLException {
        HashMap<String, String> propertiesMap = getDatabaseProperties();
        return DriverManager.getConnection(propertiesMap.get("url"), propertiesMap.get("user"), propertiesMap.get("password"));
    }
}
