package controller;

import javax.swing.*;
import view.Videographe_View;

public class Videographe_Controller {
    private final Videographe_View vue;

    public Videographe_Controller(Videographe_View vue) {
        this.vue = vue;

        // Attache les actions aux boutons
        vue.getReserverBtn().addActionListener(e -> envoyerReservation());
        vue.getConfirmerBtn().addActionListener(e -> confirmerReservation());
    }

    // Lorsqu'on clique sur "Réserver"
    private void envoyerReservation() {
        vue.getClientInfoArea().setText(
            "✅ Votre demande de réservation vidéo a été envoyée.\n" +
            "⏳ Veuillez attendre la confirmation du vidéographe."
        );
    }

    // Lorsqu'on clique sur "Confirmer"
    private void confirmerReservation() {
        String nom = vue.getVideographeNomField().getText().trim();
        String specialite = vue.getSpecialiteField().getText().trim();

        if (nom.isEmpty() || specialite.isEmpty()) {
            JOptionPane.showMessageDialog(null, "❗ Veuillez remplir tous les champs.");
            return;
        }

        String messageClient = "🎥 Réservation vidéo confirmée !\n\n" +
                               "👤 Vidéographe : " + nom + "\n" +
                               "📌 Spécialité : " + specialite;

        String messageAdmin = "📢 Confirmation envoyée au client :\n\n" + messageClient;

        vue.getClientInfoArea().setText(messageClient);
        vue.getAdminInfoArea().setText(messageAdmin);
    }
}
