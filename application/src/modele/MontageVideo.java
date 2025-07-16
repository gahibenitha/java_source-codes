package modele;

public class MontageVideo {
    // Attributs
    private String effetsSpeciaux;
    private String ajoutMusique;
    private String ajoutTexte;
    private int dureeFinale;

    // Constructeur par défaut (vide)
    public MontageVideo() {
        // initialisation par défaut si besoin
    }

    // Getters et setters
    public void setEffetsSpeciaux(String effetsSpeciaux) {
        this.effetsSpeciaux = effetsSpeciaux;
    }

    public void setAjoutMusique(String ajoutMusique) {
        this.ajoutMusique = ajoutMusique;
    }

    public void setAjoutTexte(String ajoutTexte) {
        this.ajoutTexte = ajoutTexte;
    }

    public void setDureeFinale(int dureeFinale) {
        this.dureeFinale = dureeFinale;
    }

    public void afficherInfo() {
        System.out.println("Effets spéciaux : " + effetsSpeciaux);
        System.out.println("Musique ajoutée : " + ajoutMusique);
        System.out.println("Texte ajouté : " + ajoutTexte);
        System.out.println("Durée finale : " + dureeFinale + " secondes");
    }
}
