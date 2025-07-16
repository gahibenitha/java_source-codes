package dao;

import modele.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private final Connection conn;

    public ClientDAO() throws SQLException {
        // Exemple avec SQLite (tu peux adapter pour MySQL, etc.)
        try {
    Class.forName("org.sqlite.JDBC");
} catch (ClassNotFoundException e) {
    throw new RuntimeException("Pilote JDBcC SQLite introuvable", e);
}

        conn = DriverManager.getConnection("jdbc:sqlite:clients.db");
        creerTableSiNonExistante();
    }

    private void creerTableSiNonExistante() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS clients (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nom TEXT NOT NULL,
                prenom TEXT NOT NULL,
                email TEXT NOT NULL,
                description TEXT
            )
        """;
        conn.createStatement().execute(sql);
    }

    public List<Client> getTousLesClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients ORDER BY id ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client c = new Client(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        "", // téléphone
                        rs.getString("description")
                );
                clients.add(c);
            }
        }
        return clients;
    }

    public void ajouterClient(Client client) throws SQLException {
        String sql = "INSERT INTO clients (nom, prenom, email, description) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getEmail());
            stmt.setString(4, client.getDescription());
            stmt.executeUpdate();
        }
    }
}
