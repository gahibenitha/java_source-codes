package controller;
import javax.swing.*;
import view.Studio_View;


public class Studio_Controller {
    private Studio_View vue;

    public Studio_Controller(Studio_View vue) {
        this.vue = vue;

        vue.getResBtn().addActionListener(_ -> ouvrirSection("Réservation"));
        vue.getPhotoBtn().addActionListener(_ -> ouvrirSection("Photographe"));
        vue.getVideoBtn().addActionListener(_ -> ouvrirSection("Vidéographe"));
        vue.getImpBtn().addActionListener(_-> ouvrirSection("Impression"));
        vue.getAdminBtn().addActionListener(_ -> modifierInfosStudio());
    }

    private void ouvrirSection(String nom) {
        JOptionPane.showMessageDialog(null, nom + " ouvert.");
    }

    private void modifierInfosStudio() {
        String nom = JOptionPane.showInputDialog(null, "Nouveau nom du studio :", vue.getNomLabel().getText());
        if (nom != null && !nom.isEmpty()) {
            vue.getNomLabel().setText(nom);
        }

        String adresse = JOptionPane.showInputDialog(null, "Nouvelle adresse :", vue.getAdresseLabel().getText());
        if (adresse != null && !adresse.isEmpty()) {
            vue.getAdresseLabel().setText(adresse);
        }
    }
}
