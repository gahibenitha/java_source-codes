package dao;

import modele.Personne;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneDAO {
    private final Connection conn;

    public PersonneDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MLR4", "utilisateur", "motdepasse");
    }

    public void ajouterPersonne(Personne p) throws SQLException {
        String sql = "INSERT INTO PERSONNES (ID_PERSONNE, NOM_PERSONNE, PRENOM_PERSONNE, TELEPHONE_PERSONNE, EMAIL_PERSONNE, ADRESSE_PERSONNE) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getNom());
            stmt.setString(3, p.getPrenom());
            stmt.setString(4, p.getTelephone());
            stmt.setString(5, p.getEmail());
            stmt.setString(6, p.getAdresse());
            stmt.executeUpdate();
        }
    }

    public List<Personne> getToutesLesPersonnes() throws SQLException {
        List<Personne> liste = new ArrayList<>();
        String sql = "SELECT * FROM PERSONNES";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Personne p = new Personne(
                    rs.getInt("ID_PERSONNE"),
                    rs.getString("NOM_PERSONNE"),
                    rs.getString("PRENOM_PERSONNE"),
                    rs.getString("EMAIL_PERSONNE"),
                    rs.getString("TELEPHONE_PERSONNE"),
                    rs.getString("ADRESSE_PERSONNE")
                );
                liste.add(p);
            }
        }
        return liste;
    }

    public void supprimerPersonne(int id) throws SQLException {
        String sql = "DELETE FROM PERSONNES WHERE ID_PERSONNE = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void modifierPersonne(Personne p) throws SQLException {
        String sql = "UPDATE PERSONNES SET NOM_PERSONNE = ?, PRENOM_PERSONNE = ?, TELEPHONE_PERSONNE = ?, EMAIL_PERSONNE = ?, ADRESSE_PERSONNE = ? WHERE ID_PERSONNE = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNom());
            stmt.setString(2, p.getPrenom());
            stmt.setString(3, p.getTelephone());
            stmt.setString(4, p.getEmail());
            stmt.setString(5, p.getAdresse());
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}