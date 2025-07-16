package controller;

import javax.swing.*;
import java.util.ArrayList;
import view.Personne_View;
import modele.Personne;
import modele.Photographe;
import modele.Videographe;
import modele.Client;

public class Personne_Controller {
    private final ArrayList<Personne> personnes;
    private final Personne_View vue;

    public Personne_Controller(Personne_View vue) {
        this.vue = vue;
        this.personnes = new ArrayList<>();

        // Gestionnaires d'événements
        vue.getAjouterBtn().addActionListener(_-> ajouterPersonne());
        vue.getModifierBtn().addActionListener(_-> modifierPersonne());
        vue.getSupprimerBtn().addActionListener(_ -> supprimerPersonne());
        vue.getAfficherBtn().addActionListener(_ -> afficherPersonnes());
    }

    private void ajouterPersonne() {
        String nom = vue.getNomField().getText().trim();
        String prenom = vue.getPrenomField().getText().trim();
        String email = vue.getEmailField().getText().trim();
        String tel = vue.getTelField().getText().trim();
        String type = (String) vue.getTypeCombo().getSelectedItem();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        Personne p;
        switch (type) {
            case "Photographe" -> p = new Photographe(nom, prenom, email, tel);
            case "Vidéographe" -> p = new Videographe(nom, prenom, email, tel);
            default -> p = new Client(nom, prenom, email, tel);
        }

        personnes.add(p);
        JOptionPane.showMessageDialog(null, type + " ajouté !");
        vue.clearFields();
    }

    private void modifierPersonne() {
        String nom = vue.getNomField().getText().trim();
        boolean found = false;
        for (Personne p : personnes) {
            if (p.getNom().equalsIgnoreCase(nom)) {
                p.setPrenom(vue.getPrenomField().getText().trim());
                p.setEmail(vue.getEmailField().getText().trim());
                p.setTelephone(vue.getTelField().getText().trim());
                JOptionPane.showMessageDialog(null, "✅ Données modifiées.");
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "❌ Personne non trouvée.");
        }
    }

    private void supprimerPersonne() {
        String nom = vue.getNomField().getText().trim();
        boolean removed = personnes.removeIf(p -> p.getNom().equalsIgnoreCase(nom));
        if (removed) {
            JOptionPane.showMessageDialog(null, "🗑️ Personne supprimée.");
        } else {
            JOptionPane.showMessageDialog(null, "❌ Personne non trouvée.");
        }
    }

    private void afficherPersonnes() {
        StringBuilder sb = new StringBuilder();
        for (Personne p : personnes) {
            sb.append(p).append("\n");
        }
        vue.getAdminTextArea().setText(sb.toString());
    }
}
