package core.db;

import core.config.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null ||
                    connection.isClosed()) {

                connection =
                        DriverManager.getConnection(
                                ConfigReader.get("dbUrl"),
                                ConfigReader.get("dbUsername"),
                                ConfigReader.get("dbPassword")
                        );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to connect to DB",
                    e
            );
        }

        return connection;
    }
}