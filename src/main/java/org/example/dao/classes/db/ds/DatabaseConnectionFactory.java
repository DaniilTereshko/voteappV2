package org.example.dao.classes.db.ds;

import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {
    private static Properties props = new Properties();
    private static final String SERVER = "server";
    private static final String PORT = "port";
    private static final String DATABASE = "database";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVER = "org.postgresql.Driver";
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource();
        ds.setDriverClassName(DRIVER);
        try (InputStream inputStream = Files.newInputStream(Path.of(System.getProperty("user.dir") + File.separator + FileSystems.getDefault().getPath("src/main/resources/db.properties")))){
            props.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ds.setUsername(props.getProperty(USERNAME));
        ds.setPassword(props.getProperty(PASSWORD));
        final String URL = String.format("jdbc:postgresql://%s:%s/%s",
                props.getProperty(SERVER), props.getProperty(PORT), props.getProperty(DATABASE));
        ds.setJdbcUrl(URL);
    }

    private DatabaseConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private static void closeConnection(Connection c) throws SQLException {
        if (c != null && !c.isClosed()) {
            c.close();
        }
    }
}
