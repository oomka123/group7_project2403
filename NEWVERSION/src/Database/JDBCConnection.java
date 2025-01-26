package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/project_quiz";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
