package modele;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String poste;
    private String salaire;
    private String dateEmbauche;

    public Employe() {
        // Constructeur par défaut
    }

    public Employe(int id, String nom, String prenom, String email, 
                  String telephone, String poste, String salaire, String dateEmbauche) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.poste = poste;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
    
    public String getSalaire() { return salaire; }
    public void setSalaire(String salaire) { this.salaire = salaire; }
    
    public String getDateEmbauche() { return dateEmbauche; }
    public void setDateEmbauche(String dateEmbauche) { this.dateEmbauche = dateEmbauche; }
}