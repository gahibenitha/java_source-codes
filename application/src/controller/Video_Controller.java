package controller;

import javax.swing.*;
import view.Video_View;


public class Video_Controller {
    private Video_View vue;
    private VideoClientData clientData;

    public Video_Controller(Video_View vue) {
        this.vue = vue;
        this.clientData = null;

        vue.getValiderBtn().addActionListener(_-> validerClient());
    }

    private void validerClient() {
        String nom = vue.getNomField().getText();
        String prenom = vue.getPrenomField().getText();
        String email = vue.getEmailField().getText();
        String tel = vue.getTelField().getText();
        String dureeStr = vue.getDureeVideoField().getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty() || dureeStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        int duree;
        try {
            duree = Integer.parseInt(dureeStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Durée de vidéo invalide.");
            return;
        }

        clientData = new VideoClientData(nom, prenom, email, tel, duree);
        updateAdminView();
        JOptionPane.showMessageDialog(null, "Informations enregistrées avec succès !");
    }

    private void updateAdminView() {
        if (clientData != null) {
            vue.getAdminTextArea().setText(
                "Informations client reçues :\n\n" +
                "Nom        : " + clientData.nom + "\n" +
                "Prénom     : " + clientData.prenom + "\n" +
                "Email      : " + clientData.email + "\n" +
                "Téléphone  : " + clientData.telephone + "\n" +
                "Durée Vidéo: " + clientData.dureeMinutes + " minute(s)"
            );
        }
    }

    static class VideoClientData {
        String nom, prenom, email, telephone;
        int dureeMinutes;

        VideoClientData(String nom, String prenom, String email, String telephone, int dureeMinutes) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.telephone = telephone;
            this.dureeMinutes = dureeMinutes;
        }
    }
}
