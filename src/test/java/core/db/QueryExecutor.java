package core.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryExecutor {

    public static String getSingleValue(
            String query
    ) {

        try {

            Connection connection =
                    DatabaseManager.getConnection();

            Statement statement =
                    connection.createStatement();

            ResultSet resultSet =
                    statement.executeQuery(query);

            if (resultSet.next()) {

                return resultSet.getString(1);
            }

            return null;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to execute query",
                    e
            );
        }
    }
}