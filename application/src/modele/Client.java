package modele;

public class Client {
    public long id;
    public String nom;
    public String prenom;
    public String telephone;
    public String email;
    public String adresse;
    public String sexe;
    public String idCarte;

    public Client() {
    }

    public Client(String nom, String prenom, String telephone, String email, 
                 String adresse, String sexe, String idCarte) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.sexe = sexe;
        this.idCarte = idCarte;
    }
}