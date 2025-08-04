package controller;

import java.util.ArrayList;
import javax.swing.*;
import modele.Impression;
import view.Impression_View;

public class Impression_Controller {
    private final Impression_View view;
    private final ArrayList<Impression> impressions;
    private int nextId = 1;

    public Impression_Controller(Impression_View view) {
        this.view = view;
        this.impressions = new ArrayList<>();

        // Lier les boutons à leurs actions avec noms valides
        view.getAjouterBtn().addActionListener(e -> insererImpression());
        view.getModifierBtn().addActionListener(e -> modifierImpression());
        view.getSupprimerBtn().addActionListener(e -> supprimerImpression());
        view.getAfficherBtn().addActionListener(e -> afficherImpressions());
    }

    private void insererImpression() {
        try {
            int quantite = Integer.parseInt(view.getQuantiteField().getText().trim());
            String format = (String) view.getFormatBox().getSelectedItem();
            String papier = (String) view.getPapierBox().getSelectedItem();

            if (format == null || papier == null) {
                JOptionPane.showMessageDialog(view, "⚠️ Veuillez sélectionner un format et un type de papier.");
                return;
            }

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
            int id = Integer.parseInt(view.getIdField().getText().trim());
            for (Impression imp : impressions) {
                if (imp.getIdImpression() == id) {
                    int quantite = Integer.parseInt(view.getQuantiteField().getText().trim());
                    String format = (String) view.getFormatBox().getSelectedItem();
                    String papier = (String) view.getPapierBox().getSelectedItem();

                    if (format == null || papier == null) {
                        JOptionPane.showMessageDialog(view, "⚠️ Veuillez sélectionner un format et un type de papier.");
                        return;
                    }

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
            int id = Integer.parseInt(view.getIdField().getText().trim());
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
        if (impressions.isEmpty()) {
            view.setAdminOutput("📋 Aucune impression enregistrée.\n");
        } else {
            view.setAdminOutput("📋 Liste des impressions :\n");
            for (Impression imp : impressions) {
                view.appendAdminOutput(imp.toString() + "\n");
            }
        }
    }
}
