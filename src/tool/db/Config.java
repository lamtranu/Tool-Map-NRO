package tool.db;

import com.zaxxer.hikari.HikariDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static String DB_DRIVER;
    public static String DB_HOST;
    public static String DB_PORT;
    public static String DB_NAME;
    public static String DB_USER;
    public static String DB_PASSWORD;
    public static int MIN_IDLE;
    public static int MAX_POOL_SIZE;
    public static long MAX_LIFE_TIME_MS;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
            DB_DRIVER = prop.getProperty("db.driver");
            DB_HOST = prop.getProperty("db.host");
            DB_PORT = prop.getProperty("db.port");
            DB_NAME = prop.getProperty("db.name");
            DB_USER = prop.getProperty("db.user");
            DB_PASSWORD = prop.getProperty("db.password");
            MIN_IDLE = Integer.parseInt(prop.getProperty("db.minIdle"));
            MAX_POOL_SIZE = Integer.parseInt(prop.getProperty("db.maxPoolSize"));
            MAX_LIFE_TIME_MS = Long.parseLong(prop.getProperty("db.maxLifeTimeMs"));
        } catch (IOException e) {
            System.err.println("Could not load config.properties file.");
        }
    }
}
