package modele;

public class Equipement {
    private int id;
    private String nom, marque, type, etat;

    public Equipement(int id, String nom, String marque, String type, String etat) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.type = type;
        this.etat = etat;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getMarque() { return marque; }
    public String getType() { return type; }
    public String getEtat() { return etat; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setMarque(String marque) { this.marque = marque; }
    public void setType(String type) { this.type = type; }
    public void setEtat(String etat) { this.etat = etat; }

    @Override
    public String toString() {
        return "[ID: " + id + ", Nom: " + nom + ", Marque: " + marque + ", Type: " + type + ", État: " + etat + "]";
    }
}