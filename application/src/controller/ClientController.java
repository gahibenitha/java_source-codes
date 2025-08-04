package controller;

import dao.ClientDAO;
import modele.Client;
import java.sql.SQLException;
import java.util.List;

public class ClientController {
    private final ClientDAO clientDAO;

    public ClientController() {
        this.clientDAO = new ClientDAO();
    }

    public long createClient(String nom, String prenom, String telephone, 
                           String email, String adresse, String sexe, String idCarte) {
        try {
            Client client = new Client(); // Utilise maintenant le constructeur par défaut
            client.nom = nom;
            client.prenom = prenom;
            client.telephone = telephone;
            client.email = email;
            client.adresse = adresse;
            client.sexe = sexe;
            client.idCarte = idCarte;
            
            return clientDAO.create(client);
        } catch (SQLException e) {
            System.err.println("Erreur création client: " + e.getMessage());
            return -1;
        }
    }

    // ... (le reste des méthodes reste inchangé)
    public Client getClient(long id) {
        try {
            return clientDAO.findById(id);
        } catch (SQLException e) {
            System.err.println("Erreur récupération client: " + e.getMessage());
            return null;
        }
    }

    public List<Client> getAllClients() {
        try {
            return clientDAO.findAll();
        } catch (SQLException e) {
            System.err.println("Erreur récupération clients: " + e.getMessage());
            return List.of();
        }
    }

    public boolean updateClient(long id, String nom, String prenom, String telephone, 
                              String email, String adresse, String sexe, String idCarte) {
        try {
            Client client = new Client();
            client.id = id;
            client.nom = nom;
            client.prenom = prenom;
            client.telephone = telephone;
            client.email = email;
            client.adresse = adresse;
            client.sexe = sexe;
            client.idCarte = idCarte;
            
            return clientDAO.update(client);
        } catch (SQLException e) {
            System.err.println("Erreur mise à jour client: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteClient(long id) {
        try {
            return clientDAO.delete(id);
        } catch (SQLException e) {
            System.err.println("Erreur suppression client: " + e.getMessage());
            return false;
        }
    }

    public boolean validateClientData(String nom, String prenom, String telephone, 
                                    String email, String idCarte) {
        // Validation basique
        if (nom == null || nom.trim().isEmpty()) return false;
        if (prenom == null || prenom.trim().isEmpty()) return false;
        if (telephone == null || !telephone.matches("^[0-9]{10}$")) return false;
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) return false;
        if (idCarte == null || !idCarte.matches("^[0-9]{8,12}$")) return false;
        
        return true;
    }
}