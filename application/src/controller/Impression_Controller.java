package controller;

import view.Impression_View;
import modele.Impression;

import javax.swing.*;
import java.util.ArrayList;

public class Impression_Controller {
    private Impression_View view;
    private ArrayList<Impression> impressions;
    private int nextId = 1;

    public Impression_Controller(Impression_View view) {
        this.view = view;
        this.impressions = new ArrayList<>();

        // Lier les boutons à leurs actions (Assure-toi que les getters existent dans Impression_View)
        view.getAjouterBtn().addActionListener(_-> insererImpression());
        view.getModifierBtn().addActionListener(_ -> modifierImpression());
        view.getSupprimerBtn().addActionListener(_ -> supprimerImpression());
        view.getAfficherBtn().addActionListener(_ -> afficherImpressions());
    }

    private void insererImpression() {
        try {
            int quantite = Integer.parseInt(view.getQuantiteField().getText());
            String format = (String) view.getFormatBox().getSelectedItem();
            String papier = (String) view.getPapierBox().getSelectedItem();

            Impression imp = new Impression(nextId++, quantite, format, papier);
            impressions.add(imp);

            JOptionPane.showMessageDialog(view, "✅ Commande enregistrée !");
            view.clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ Entrez un nombre valide pour la quantité.");
        }
    }

    private void modifierImpression() {
        try {
            int id = Integer.parseInt(view.getIdField().getText());
            for (Impression imp : impressions) {
                if (imp.getIdImpression() == id) {
                    int quantite = Integer.parseInt(view.getQuantiteField().getText());
                    String format = (String) view.getFormatBox().getSelectedItem();
                    String papier = (String) view.getPapierBox().getSelectedItem();

                    imp.setQuantite(quantite);
                    imp.setFormat(format);
                    imp.setTypePapier(papier);

                    JOptionPane.showMessageDialog(view, "✏️ Impression modifiée !");
                    view.clearFields();
                    return;
                }
            }
            JOptionPane.showMessageDialog(view, "⚠️ Aucune impression avec cet ID.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ ID et quantité doivent être des nombres.");
        }
    }

    private void supprimerImpression() {
        try {
            int id = Integer.parseInt(view.getIdField().getText());
            boolean removed = impressions.removeIf(imp -> imp.getIdImpression() == id);
            if (removed) {
                JOptionPane.showMessageDialog(view, "🗑️ Impression supprimée !");
                view.clearFields();
            } else {
                JOptionPane.showMessageDialog(view, "⚠️ Aucune impression avec cet ID.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "⚠️ Entrez un ID valide.");
        }
    }

    private void afficherImpressions() {
        view.setAdminOutput("📋 Liste des impressions :\n");
        for (Impression imp : impressions) {
            view.appendAdminOutput(imp.toString() + "\n");
        }
    }
}
