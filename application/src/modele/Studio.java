package modele;
import java.util.ArrayList;
import java.util.List;

public class Studio {
    private int id;
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private List<Equipement> equipements;

    // Constructeur
    public Studio(int id, String nom, String adresse, String telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.equipements = new ArrayList<>(); // Initialisation de la liste d'équipements
    }

    // Méthode pour ajouter un équipement
    public void ajouterEquipement(Equipement e) {
        equipements.add(e);
    }

    // Méthode pour afficher les informations du studio
    public void afficherStudio() {
        System.out.println("Studio ID: " + id);
        System.out.println("Nom : " + nom);
        System.out.println("Adresse : " + adresse);
        System.out.println("Téléphone : " + telephone);
        System.out.println("Email : " + email);
        System.out.println("Équipements : ");
        for (Equipement e : equipements) {
            System.out.println("  - " + e.getNom());
        }
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<Equipement> equipements) {
        this.equipements = equipements;
    }
}
