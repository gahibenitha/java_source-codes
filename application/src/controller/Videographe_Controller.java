package controller;
import view.Videographe_View;
import javax.swing.*;

public class Videographe_Controller {
    private Videographe_View vue;

    public Videographe_Controller(Videographe_View vue) {
        this.vue = vue;

        vue.getReserverBtn().addActionListener(_-> envoyerReservation());
        vue.getConfirmerBtn().addActionListener(_ -> confirmerReservation());
    }

    private void envoyerReservation() {
        vue.getClientInfoArea().setText("Votre demande de réservation vidéo a été envoyée.\nVeuillez attendre confirmation.");
    }

    private void confirmerReservation() {
        String nom = vue.getVideographeNomField().getText().trim();
        String specialite = vue.getSpecialiteField().getText().trim();

        if (nom.isEmpty() || specialite.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        String message = "Votre réservation vidéo est confirmée.\n\nVidéographe : " + nom + "\nSpécialité : " + specialite;
        vue.getClientInfoArea().setText(message);
        vue.getAdminInfoArea().setText("Confirmation envoyée au client :\n\n" + message);
    }
}
