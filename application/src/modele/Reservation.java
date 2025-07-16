package modele;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Reservation {
    private int id;
    private Date date;
    private String typeSeance;
    private int duree;
    private double prix;
    private String statut;
    private Client client;

    // Constructeur
    public Reservation(int id, Date date, String typeSeance, int duree, double prix, String statut, Client client) {
        this.id = id;
        this.date = date;
        this.typeSeance = typeSeance;
        this.duree = duree;
        this.prix = prix;
        this.statut = statut;
        this.client = client;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTypeSeance() {
        return typeSeance;
    }

    public void setTypeSeance(String typeSeance) {
        this.typeSeance = typeSeance;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // Méthode pour afficher les informations de la réservation
    public void afficherReservation() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Formater la date
        System.out.println("Réservation ID : " + id);
        System.out.println("Date : " + sdf.format(date));  // Utiliser le format
        System.out.println("Type de séance : " + typeSeance);
        System.out.println("Durée : " + duree + " heures");
        System.out.println("Prix : " + prix + "€");
        System.out.println("Statut : " + statut);
        if (client != null) {
         //   System.out.println("Client : " + client.getNom() + " " + client.getPrenom());  // Affichage du nom du client
        }
    }
}
