package controller;
import javax.swing.*;
import view.Photographe_View;


public class Photographe_Controller {
    private Photographe_View vue;

    public Photographe_Controller(Photographe_View vue) {
        this.vue = vue;

        vue.getReserverBtn().addActionListener(_-> envoyerReservation());
        vue.getConfirmerBtn().addActionListener(_-> confirmerReservation());
    }

    private void envoyerReservation() {
        vue.getClientInfoArea().setText("Réservation envoyée.\nVeuillez attendre confirmation de l'administrateur...");
    }

    private void confirmerReservation() {
        String nom = vue.getPhotographeNomField().getText().trim();
        String specialite = vue.getSpecialiteField().getText().trim();

        if (nom.isEmpty() || specialite.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        String message = "Votre réservation est confirmée.\n\nPhotographe : " + nom + "\nSpécialité : " + specialite;
        vue.getClientInfoArea().setText(message);
        vue.getAdminInfoArea().setText("Confirmation envoyée au client :\n\n" + message);
    }
}
