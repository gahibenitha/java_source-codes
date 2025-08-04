package dao;

import modele.Employe;
import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAO {
    private static final String TABLE_NAME = "employes";
    
    // Requêtes SQL préparées
    private static final String INSERT = String.format(
        "INSERT INTO %s (nom, prenom, email, telephone, poste, salaire, date_embauche) VALUES (?, ?, ?, ?, ?, ?, ?)", 
        TABLE_NAME);
    
    private static final String SELECT_ALL = String.format(
        "SELECT * FROM %s ORDER BY nom, prenom", TABLE_NAME);
    
    private static final String SELECT_BY_ID = String.format(
        "SELECT * FROM %s WHERE id = ?", TABLE_NAME);
    
    private static final String UPDATE = String.format(
        "UPDATE %s SET nom=?, prenom=?, email=?, telephone=?, poste=?, salaire=?, date_embauche=? WHERE id=?", 
        TABLE_NAME);
    
    private static final String DELETE = String.format(
        "DELETE FROM %s WHERE id=?", TABLE_NAME);

    /**
     * Crée un nouvel employé dans la base de données
     * @param employe L'objet Employe à créer
     * @return true si la création a réussi, false sinon
     */
    public boolean create(Employe employe) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            
            setEmployeParameters(stmt, employe);
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        employe.setId(rs.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException("Erreur lors de la création d'un employé", e);
        }
        return false;
    }

    /**
     * Récupère tous les employés de la base de données
     * @return Une liste de tous les employés
     */
    public List<Employe> findAll() {
        List<Employe> employes = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL)) {
            
            while (rs.next()) {
                employes.add(mapResultSetToEmploye(rs));
            }
        } catch (SQLException e) {
            handleSQLException("Erreur lors de la récupération des employés", e);
        }
        return employes;
    }

    /**
     * Trouve un employé par son ID
     * @param id L'ID de l'employé à trouver
     * @return L'employé trouvé ou null si non trouvé
     */
    public Employe findById(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEmploye(rs);
                }
            }
        } catch (SQLException e) {
            handleSQLException("Erreur lors de la recherche d'un employé par ID", e);
        }
        return null;
    }

    /**
     * Met à jour un employé existant
     * @param employe L'employé avec les nouvelles données
     * @return true si la mise à jour a réussi, false sinon
     */
    public boolean update(Employe employe) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
            
            setEmployeParameters(stmt, employe);
            stmt.setInt(8, employe.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            handleSQLException("Erreur lors de la mise à jour d'un employé", e);
        }
        return false;
    }

    /**
     * Supprime un employé par son ID
     * @param id L'ID de l'employé à supprimer
     * @return true si la suppression a réussi, false sinon
     */
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            handleSQLException("Erreur lors de la suppression d'un employé", e);
        }
        return false;
    }

    // ============ MÉTHODES UTILITAIRES PRIVÉES ============

    /**
     * Convertit un ResultSet en objet Employe
     */
    private Employe mapResultSetToEmploye(ResultSet rs) throws SQLException {
        return new Employe(
            rs.getInt("id"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("email"),
            rs.getString("telephone"),
            rs.getString("poste"),
            rs.getString("salaire"),
            rs.getDate("date_embauche").toString()
        );
    }

    /**
     * Définit les paramètres d'un PreparedStatement à partir d'un objet Employe
     */
    private void setEmployeParameters(PreparedStatement stmt, Employe employe) throws SQLException {
        stmt.setString(1, employe.getNom());
        stmt.setString(2, employe.getPrenom());
        stmt.setString(3, employe.getEmail());
        stmt.setString(4, employe.getTelephone());
        stmt.setString(5, employe.getPoste());
        stmt.setString(6, employe.getSalaire());
        stmt.setDate(7, Date.valueOf(employe.getDateEmbauche()));
    }

    /**
     * Gère les exceptions SQL de manière uniforme
     */
    private void handleSQLException(String message, SQLException e) {
        System.err.println(message);
        System.err.println("Code d'erreur SQL: " + e.getErrorCode());
        System.err.println("État SQL: " + e.getSQLState());
        System.err.println("Message: " + e.getMessage());
        e.printStackTrace();
        
        // Vous pourriez aussi logger cette erreur dans un fichier log
        // Logger.getLogger(EmployeDAO.class.getName()).log(Level.SEVERE, message, e);
    }
}