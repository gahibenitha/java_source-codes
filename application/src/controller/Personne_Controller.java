package controller;

import java.util.ArrayList;
import javax.swing.*;
import modele.Personne;
import view.Personne_View;

public class Personne_Controller {
    private final ArrayList<Personne> personnes;
    private final Personne_View vue;

    public Personne_Controller(Personne_View vue) {
        this.vue = vue;
        this.personnes = new ArrayList<>();
        initControllers();
    }

    private void initControllers() {
        vue.getAjouterBtn().addActionListener(e -> ajouterPersonne());
        vue.getModifierBtn().addActionListener(e -> modifierPersonne());
        vue.getSupprimerBtn().addActionListener(e -> supprimerPersonne());
        vue.getAfficherBtn().addActionListener(e -> afficherPersonnes());
    }

    private void ajouterPersonne() {
        String nom = vue.getNomField().getText().trim();
        String prenom = vue.getPrenomField().getText().trim();
        String email = vue.getEmailField().getText().trim();
        String tel = vue.getTelField().getText().trim();
        String type = (String) vue.getTypeCombo().getSelectedItem();

        if (!validerChamps(nom, prenom, email, tel)) return;
        if (type == null) {
            afficherErreur("Veuillez sélectionner un type de personne.");
            return;
        }

        try {
            Personne p = creerPersonneSelonType(type, nom, prenom, email, tel);
            personnes.add(p);
            afficherMessageSucces(type + " ajouté avec succès");
            vue.clearFields();
        } catch (Exception e) {
            afficherErreur("Erreur lors de la création : " + e.getMessage());
        }
    }

    private boolean validerChamps(String... champs) {
        for (String champ : champs) {
            if (champ == null || champ.isEmpty()) {
                afficherErreur("Tous les champs sont obligatoires.");
                return false;
            }
        }
        return true;
    }

    private Personne creerPersonneSelonType(String type, String nom, String prenom, String email, String tel) {
        // Pour l'instant on retourne juste une Personne, on pourrait étendre ici selon le type (Client, Agent, etc.)
        return new Personne(nom, prenom, email, tel);
    }

    private void modifierPersonne() {
        String nom = vue.getNomField().getText().trim();

        personnes.stream()
            .filter(p -> p.getNom().equalsIgnoreCase(nom))
            .findFirst()
            .ifPresentOrElse(
                p -> {
                    p.setPrenom(vue.getPrenomField().getText().trim());
                    p.setEmail(vue.getEmailField().getText().trim());
                    p.setTelephone(vue.getTelField().getText().trim());
                    afficherMessageSucces("Données modifiées avec succès.");
                },
                () -> afficherErreur("Personne non trouvée.")
            );
    }

    private void supprimerPersonne() {
        String nom = vue.getNomField().getText().trim();
        boolean removed = personnes.removeIf(p -> p.getNom().equalsIgnoreCase(nom));

        if (removed) {
            afficherMessageSucces("Personne supprimée avec succès.");
            vue.clearFields();
        } else {
            afficherErreur("Personne non trouvée.");
        }
    }

    private void afficherPersonnes() {
        if (personnes.isEmpty()) {
            vue.getAdminTextArea().setText("Aucune personne enregistrée.");
            return;
        }

        StringBuilder sb = new StringBuilder("Liste des personnes :\n\n");
        for (Personne p : personnes) {
            sb.append(p).append("\n\n");
        }
        vue.getAdminTextArea().setText(sb.toString());
    }

    private void afficherMessageSucces(String message) {
        JOptionPane.showMessageDialog(vue, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    private void afficherErreur(String message) {
        JOptionPane.showMessageDialog(vue, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
