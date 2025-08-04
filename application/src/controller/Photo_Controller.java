package controller;
import javax.swing.*;
import view.Photo_View;

public class Photo_Controller {
    private Photo_View vue;
    private ClientData clientData;

    public Photo_Controller(Photo_View vue) {
        this.vue = vue;
        this.clientData = null;

        vue.getValiderBtn().addActionListener(e-> validerClient());
    }

    private void validerClient() {
        String nom = vue.getNomField().getText();
        String prenom = vue.getPrenomField().getText();
        String email = vue.getEmailField().getText();
        String tel = vue.getTelField().getText();
        String nbPhotosStr = vue.getNbPhotosField().getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty() || nbPhotosStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        int nbPhotos;
        try {
            nbPhotos = Integer.parseInt(nbPhotosStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Nombre de photos invalide.");
            return;
        }

        clientData = new ClientData(nom, prenom, email, tel, nbPhotos);
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
                "Photos     : " + clientData.nbPhotos + " photo(s)"
            );
        }
    }

    static class ClientData {
        String nom, prenom, email, telephone;
        int nbPhotos;

        ClientData(String nom, String prenom, String email, String telephone, int nbPhotos) {
            this.nom = nom;
            this.prenom = prenom;
            this.email = email;
            this.telephone = telephone;
            this.nbPhotos = nbPhotos;
        }
    }
}
