package controller;

import javax.swing.*;
import view.Video_View;

public class Video_Controller {
    private final Video_View vue;
    private VideoClientData clientData;

    public Video_Controller(Video_View vue) {
        this.vue = vue;
        this.clientData = null;

        vue.getValiderBtn().addActionListener(e -> validerClient());
    }

    private void validerClient() {
        String nom = vue.getNomField().getText().trim();
        String prenom = vue.getPrenomField().getText().trim();
        String email = vue.getEmailField().getText().trim();
        String tel = vue.getTelField().getText().trim();
        String dureeStr = vue.getDureeVideoField().getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty() || dureeStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        int duree;
        try {
            duree = Integer.parseInt(dureeStr);
            if (duree <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Durée de vidéo invalide. Veuillez entrer un nombre positif.");
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
                "Nom        : " + clientData.getNom() + "\n" +
                "Prénom     : " + clientData.getPrenom() + "\n" +
                "Email      : " + clientData.getEmail() + "\n" +
                "Téléphone  : " + clientData.getTelephone() + "\n" +
                "Durée Vidéo: " + clientData.getDureeMinutes() + " minute(s)"
            );
        }
    }

    static class VideoClientData {
        private final String nom;
        private final String prenom;
        private final String email;
        private final String telephone;
        private final int dureeMinutes;

        public VideoClientData(String nom, String prenom, String email, String telephone, int dureeMinutes) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.telephone = telephone;
            this.dureeMinutes = dureeMinutes;
        }

        public String getNom() { return nom; }
        public String getPrenom() { return prenom; }
        public String getEmail() { return email; }
        public String getTelephone() { return telephone; }
        public int getDureeMinutes() { return dureeMinutes; }
    }
}
