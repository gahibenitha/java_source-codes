package modele;
import java.util.Date;
public class Photo {
    private int id;
    private String nomFichier;
    private String format;
    private double taille;
    private Date dateCreation;
    private int nombre;

    public Photo(int id, String nomFichier, String format, double taille, Date dateCreation, int nombre) {
        this.id = id;
        this.nomFichier = nomFichier;
        this.format = format;
        this.taille = taille;
        this.dateCreation = dateCreation;
        this.nombre = nombre;
    }

    public void afficherInfo() {
        System.out.println("Photo ID : " + id);
        System.out.println("Nom du fichier : " + nomFichier);
        System.out.println("Format : " + format);
        System.out.println("Taille : " + taille + " Mo");
        System.out.println("Date de création : " + dateCreation);
        System.out.println("Nombre de photos : " + nombre);
    }
}

