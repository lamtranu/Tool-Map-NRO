package tool.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static tool.db.Config.DB_DRIVER;
import static tool.db.Config.DB_HOST;
import static tool.db.Config.DB_PASSWORD;
import static tool.db.Config.DB_PORT;
import static tool.db.Config.DB_USER;
import static tool.db.Config.MAX_LIFE_TIME_MS;
import static tool.db.Config.MAX_POOL_SIZE;
import static tool.db.Config.MIN_IDLE;

public class DatabaseManagers {

    private static final HikariDataSource ds;

    static {
        HikariDataSource temp = null;
        try {
            temp = createDataSource("Taidz", Config.DB_NAME);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ds = temp;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close() {
        if (ds != null) {
            ds.close();
        }
    }

    public static DatabaseResultSet executeQuery(final String query, final Object... params) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            return new ResultSetImpl(rs);
        } finally {
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (ps != null) try { ps.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }

    public static int executeUpdate(final String query, final Object... params) throws Exception {
        String formattedQuery = query;
        if (query.trim().toLowerCase().startsWith("insert") && query.trim().endsWith("()")) {
            StringBuilder sb = new StringBuilder("(");
            for (int i = 0; i < params.length; i++) {
                sb.append("?");
                if (i < params.length - 1) sb.append(",");
            }
            sb.append(")");
            formattedQuery = query.substring(0, query.lastIndexOf("()")) + sb.toString();
        }

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(formattedQuery);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate();
        } finally {
            if (ps != null) try { ps.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }

    private static HikariConfig createConfig(String poolName, String databaseName) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DB_DRIVER);
        config.setJdbcUrl(String.format(
                "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8",
                DB_HOST, DB_PORT, databaseName));
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.setMinimumIdle(MIN_IDLE);
        config.setMaximumPoolSize(MAX_POOL_SIZE);
        config.setMaxLifetime(MAX_LIFE_TIME_MS);
        config.setPoolName(poolName);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");

        return config;
    }

    public static HikariDataSource createDataSource(String poolName, String databaseName) {
        HikariConfig config = createConfig(poolName, databaseName);
        return new HikariDataSource(config);
    }
}
