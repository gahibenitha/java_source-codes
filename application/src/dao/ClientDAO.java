package dao;

import modele.Client;
import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    
    public ClientDAO() {
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS clients (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100) NOT NULL,
                telephone VARCHAR(20) NOT NULL,
                email VARCHAR(100) NOT NULL UNIQUE,
                adresse TEXT NOT NULL,
                sexe ENUM('Homme', 'Femme') NOT NULL,
                id_carte VARCHAR(50) NOT NULL UNIQUE,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
            """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erreur création table clients: " + e.getMessage());
        }
    }

    public long create(Client client) throws SQLException {
        String sql = """
            INSERT INTO clients (nom, prenom, telephone, email, adresse, sexe, id_carte)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            setClientParameters(pstmt, client);
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
                throw new SQLException("Échec création client, aucun ID généré");
            }
        }
    }

    public Client findById(long id) throws SQLException {
        String sql = "SELECT * FROM clients WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? mapClient(rs) : null;
            }
        }
    }

    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM clients ORDER BY nom, prenom";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                clients.add(mapClient(rs));
            }
        }
        return clients;
    }

    public boolean update(Client client) throws SQLException {
        String sql = """
            UPDATE clients SET 
                nom = ?, prenom = ?, telephone = ?, email = ?,
                adresse = ?, sexe = ?, id_carte = ?
            WHERE id = ?
            """;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            setClientParameters(pstmt, client);
            pstmt.setLong(8, client.id);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    private void setClientParameters(PreparedStatement pstmt, Client client) throws SQLException {
        pstmt.setString(1, client.nom);
        pstmt.setString(2, client.prenom);
        pstmt.setString(3, client.telephone);
        pstmt.setString(4, client.email);
        pstmt.setString(5, client.adresse);
        pstmt.setString(6, client.sexe);
        pstmt.setString(7, client.idCarte);
    }

    private Client mapClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.id = rs.getLong("id");
        client.nom = rs.getString("nom");
        client.prenom = rs.getString("prenom");
        client.telephone = rs.getString("telephone");
        client.email = rs.getString("email");
        client.adresse = rs.getString("adresse");
        client.sexe = rs.getString("sexe");
        client.idCarte = rs.getString("id_carte");
        return client;
    }
}