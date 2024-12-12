import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/devoir_thread"; // Replace with your database name and host
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connexion réussie !");

            // Requête d'insertion pour ajouter plusieurs clients
            String insertQuery = "INSERT INTO customer (name, email, phone) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(insertQuery)) {
                // Liste de musiciens indie/rock
                String[][] customers = {
                        {"Jimi Hendrix", "jimi.hendrix@example.com", "987-654-3210"},
                        {"Kurt Cobain", "kurt.cobain@example.com", "555-666-7777"},
                        {"Freddie Mercury", "freddie.mercury@example.com", "222-333-4444"},
                        {"David Bowie", "david.bowie@example.com", "888-999-0000"},
                        {"Bob Dylan", "bob.dylan@example.com", "111-222-3333"},
                        {"Thom Yorke", "thom.yorke@example.com", "444-555-6666"},
                        {"Patti Smith", "patti.smith@example.com", "777-888-9999"},
                        {"Amy Winehouse", "amy.winehouse@example.com", "333-444-5555"}
                };

                for (String[] customer : customers) {
                    pstmt.setString(1, customer[0]);
                    pstmt.setString(2, customer[1]);
                    pstmt.setString(3, customer[2]);

                    int rowsInserted = pstmt.executeUpdate();
                    if (rowsInserted > 0) {
                        System.out.println("Le client " + customer[0] + " a été ajouté avec succès !");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}