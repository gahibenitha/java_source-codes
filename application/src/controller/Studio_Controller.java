package controller;

import javax.swing.*;
import view.Studio_View;

public class Studio_Controller {
    private final Studio_View vue;

    public Studio_Controller(Studio_View vue) {
        this.vue = vue;

        // Remplacement du `_` interdit par `e` dans les lambdas
        vue.getResBtn().addActionListener(e -> ouvrirSection("Réservation"));
        vue.getPhotoBtn().addActionListener(e -> ouvrirSection("Photographe"));
        vue.getVideoBtn().addActionListener(e -> ouvrirSection("Vidéographe"));
        vue.getImpBtn().addActionListener(e -> ouvrirSection("Impression"));
        vue.getAdminBtn().addActionListener(e -> modifierInfosStudio());
    }

    private void ouvrirSection(String nom) {
        JOptionPane.showMessageDialog(null, nom + " ouvert.");
    }

    private void modifierInfosStudio() {
        String nom = JOptionPane.showInputDialog(null, "Nouveau nom du studio :", vue.getNomLabel().getText());
        if (nom != null && !nom.trim().isEmpty()) {
            vue.getNomLabel().setText(nom.trim());
        }

        String adresse = JOptionPane.showInputDialog(null, "Nouvelle adresse :", vue.getAdresseLabel().getText());
        if (adresse != null && !adresse.trim().isEmpty()) {
            vue.getAdresseLabel().setText(adresse.trim());
        }
    }
}
