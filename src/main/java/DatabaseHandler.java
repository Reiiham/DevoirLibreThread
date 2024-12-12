import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/devoir_thread";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static boolean isCustomerExists(int customerId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM customer WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void insertOrder(Order order) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO `order` (id, date, amount, customer_id,status) VALUES (?, ?, ?, ?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, order.getId());
            stmt.setDate(2, java.sql.Date.valueOf(order.getDate()));
            stmt.setDouble(3, order.getAmount());
            stmt.setInt(4, order.getCustomerId());
            stmt.setString(5, order.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
