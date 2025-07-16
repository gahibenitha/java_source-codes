package controller;

import view.MontageVideo_View;
import modele.MontageVideo;  // J'imagine que tu as un modèle MontageVideo

public class MontageVideo_Controller {
    private MontageVideo_View view;
    private MontageVideo montage;  // Le modèle lié au contrôleur

    // Constructeur
    public MontageVideo_Controller(MontageVideo_View view) {
        this.view = view;
        this.montage = new MontageVideo();  // initialisation du modèle

        // Ici tu peux ajouter des listeners sur la vue, ex :
        // view.getValiderBtn().addActionListener(e -> validerChoix());
    }

    // Setters appelés par la vue utilisateur
    public void setEffetsSpeciaux(String effets) {
        montage.setEffetsSpeciaux(effets);
    }

    public void setAjoutMusique(String musique) {
        montage.setAjoutMusique(musique);
    }

    public void setAjoutTexte(String texte) {
        montage.setAjoutTexte(texte);
    }

    public void setDureeFinale(int duree) {
        montage.setDureeFinale(duree);
    }

    // Quand l'utilisateur clique sur "Valider"
    public void validerChoix() {
        // Affichage console (optionnel)
        montage.afficherInfo();

        // Exemple : création et affichage de la vue admin avec les données actuelles du montage
        // vueAdmin = new AdminInterface(montage);

        // Fermeture de l’interface utilisateur
        // vueUtilisateur.fermer();
    }
}
