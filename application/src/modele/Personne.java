package modele;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;

    // Constructeur de base
    public Personne(String nom, String prenom, String email, String telephone) {
        this(0, nom, prenom, email, telephone, ""); // Appel au constructeur principal avec valeurs par défaut
    }

    // Constructeur principal
    public Personne(int id, String nom, String prenom, String email, String telephone, String adresse) {
        this.id = id;
        this.nom = validateNom(nom);
        this.prenom = validatePrenom(prenom);
        this.email = validateEmail(email);
        this.telephone = validateTelephone(telephone);
        this.adresse = (adresse != null) ? adresse : "";
    }

    // Méthodes de validation
    private String validateNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut pas être vide");
        }
        return nom.trim();
    }

    private String validatePrenom(String prenom) {
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut pas être vide");
        }
        return prenom.trim();
    }

    private String validateEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
            throw new IllegalArgumentException("Email invalide");
        }
        return email.trim();
    }

    private String validateTelephone(String telephone) {
        if (telephone == null || !telephone.matches("^[0-9]{10}$")) {
            throw new IllegalArgumentException("Le téléphone doit contenir 10 chiffres");
        }
        return telephone.trim();
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }
    public String getAdresse() { return adresse; }

    // Setters avec validation
    public void setId(int id) { this.id = id; }
    
    public void setNom(String nom) { 
        this.nom = validateNom(nom); 
    }
    
    public void setPrenom(String prenom) { 
        this.prenom = validatePrenom(prenom); 
    }
    
    public void setEmail(String email) { 
        this.email = validateEmail(email); 
    }
    
    public void setTelephone(String telephone) { 
        this.telephone = validateTelephone(telephone); 
    }
    
    public void setAdresse(String adresse) { 
        this.adresse = (adresse != null) ? adresse : ""; 
    }

    // Méthodes utilitaires
    @Override
    public String toString() {
        return String.format(
            "Personne [id=%d, nom=%s, prenom=%s, email=%s, telephone=%s, adresse=%s]",
            id, nom, prenom, email, telephone, adresse
        );
    }

    public void afficherInfo() {
        System.out.println(this);
    }

    // Méthode equals basée sur l'id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Personne other = (Personne) obj;
        return id == other.id;
    }

    // Méthode hashCode cohérente avec equals
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}