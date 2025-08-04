package dao;

import modele.Client;
import modele.Reservation;
import database.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    
    public ReservationDAO() {
        createTables();
        createIndexes();
    }

    private void createTables() {
        String createClientTable = """
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

        String createReservationTable = """
            CREATE TABLE IF NOT EXISTS reservations (
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                client_id BIGINT NOT NULL,
                type_seance ENUM('Photo', 'Vidéo', 'Photo/Vidéo') NOT NULL,
                date DATE NOT NULL,
                heure TIME NOT NULL,
                package_type ENUM('Simple', 'VIP', 'VIVIVIP') NOT NULL,
                service VARCHAR(100) NOT NULL,
                status ENUM('Confirmée', 'Annulée', 'Terminée') DEFAULT 'Confirmée',
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE,
                INDEX idx_date_heure (date, heure),
                CONSTRAINT uc_reservation UNIQUE (client_id, date, heure)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createClientTable);
            stmt.execute(createReservationTable);
        } catch (SQLException e) {
            System.err.println("Erreur création tables: " + e.getMessage());
        }
    }

    private void createIndexes() {
        String createEmailIndex = "CREATE INDEX IF NOT EXISTS idx_email ON clients(email)";
        String createPhoneIndex = "CREATE INDEX IF NOT EXISTS idx_telephone ON clients(telephone)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createEmailIndex);
            stmt.execute(createPhoneIndex);
        } catch (SQLException e) {
            System.err.println("Erreur création indexes: " + e.getMessage());
        }
    }

    public long saveClient(Client client) throws SQLException {
        String sql = """
            INSERT INTO clients (nom, prenom, telephone, email, adresse, sexe, id_carte)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE 
                nom = VALUES(nom),
                prenom = VALUES(prenom),
                telephone = VALUES(telephone),
                adresse = VALUES(adresse),
                sexe = VALUES(sexe)
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, client.nom);
            pstmt.setString(2, client.prenom);
            pstmt.setString(3, client.telephone);
            pstmt.setString(4, client.email);
            pstmt.setString(5, client.adresse);
            pstmt.setString(6, client.sexe);
            pstmt.setString(7, client.idCarte);

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
                throw new SQLException("Échec récupération ID client");
            }
        }
    }

    public Client getClientById(long id) throws SQLException {
        String sql = "SELECT * FROM clients WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
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
                return null;
            }
        }
    }

    public long saveReservation(Reservation reservation, long clientId) throws SQLException {
        String sql = """
            INSERT INTO reservations 
                (client_id, type_seance, date, heure, package_type, service)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setLong(1, clientId);
            pstmt.setString(2, reservation.typeSeance);
            pstmt.setDate(3, Date.valueOf(reservation.date));
            pstmt.setTime(4, Time.valueOf(reservation.heure));
            pstmt.setString(5, reservation.packageType);
            pstmt.setString(6, reservation.service);

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getLong(1);
                throw new SQLException("Échec récupération ID réservation");
            }
        }
    }

    public List<Reservation> getReservationsByClientId(long clientId) throws SQLException {
        String sql = "SELECT * FROM reservations WHERE client_id = ? ORDER BY date DESC, heure DESC";
        List<Reservation> reservations = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, clientId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.id = rs.getLong("id");
                    reservation.typeSeance = rs.getString("type_seance");
                    reservation.date = rs.getDate("date").toLocalDate();
                    reservation.heure = rs.getTime("heure").toLocalTime();
                    reservation.packageType = rs.getString("package_type");
                    reservation.service = rs.getString("service");
                    reservation.status = rs.getString("status");
                    reservations.add(reservation);
                }
            }
        }
        return reservations;
    }

    public boolean isTimeSlotAvailable(LocalDate date, LocalTime time) throws SQLException {
        String sql = """
            SELECT COUNT(*) FROM reservations 
            WHERE date = ? AND heure BETWEEN ? AND ?
            AND status = 'Confirmée'
            """;
        
        LocalTime start = time.minusMinutes(30);
        LocalTime end = time.plusMinutes(30);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(date));
            pstmt.setTime(2, Time.valueOf(start));
            pstmt.setTime(3, Time.valueOf(end));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) == 0;
            }
        }
    }
}