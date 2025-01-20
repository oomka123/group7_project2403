import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCConnection.getConnection();
        if (connection != null) {
            Quiz quiz = new Quiz(connection);
            quiz.startQuiz();
        } else {
            System.out.println("Connection is null");
        }
    }
}