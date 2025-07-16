package modele;

public class Client extends Personne {
    private String description;

    public Client(String nom, String prenom, String email, String telephone) {
        super(nom, prenom, email, telephone);
        this.description = "";
    }

    public Client(String nom, String prenom, String email, String telephone, String description) {
        super(nom, prenom, email, telephone);
        this.description = (description != null) ? description : "";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description = description;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s, %s)\nDescription : %s",
                getNom(), getPrenom(), getEmail(), getTelephone(), description);
    }
}
