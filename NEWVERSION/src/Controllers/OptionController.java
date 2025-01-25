package Controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionController {
    private final Connection connection;

    public OptionController(Connection connection) {
        this.connection = connection;
    }

    public void addOption(int questionId, char label, String text) throws SQLException {
        String query = "INSERT INTO options (question_id, option_label, option_text) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, questionId);
            ps.setString(2, String.valueOf(label));
            ps.setString(3, text);
            ps.executeUpdate();
        }
    }

    public void listOptions(int questionId) throws SQLException {
        String query = "SELECT * FROM options WHERE question_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Option " + rs.getString("option_label") + ": " + rs.getString("option_text"));
            }
        }
    }
}
