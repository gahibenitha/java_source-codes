package modele;

public class Photographe extends Personne {
    private String specialisation; // Spécialisation du photographe (ex: Mariage, Portrait, etc.)
    private int experience;        // Expérience en années

    // Constructeur simple
    public Photographe(String nom, String prenom, String email, String telephone) {
        super(nom, prenom, email, telephone);
    }

    // Constructeur complet (optionnel)
    public Photographe(String nom, String prenom, String email, String telephone, String specialisation, int experience) {
        super(nom, prenom, email, telephone);
        this.specialisation = specialisation;
        this.experience = experience;
    }

    // Getters et Setters
    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    // Méthode pour afficher les informations du photographe
    @Override
    public void afficherInfo() {
        super.afficherInfo();
        System.out.println("Spécialisation : " + specialisation);
        System.out.println("Expérience : " + experience + " années");
    }
}
