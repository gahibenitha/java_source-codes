package controller;

import modele.Employe;
import view.Employe_View;
import view.Accueil_View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.nio.file.*;
import java.util.ArrayList;

public class Employe_Controller {
    private final Employe_View view;
    private final ArrayList<Employe> listeEmployes = new ArrayList<>();

    public Employe_Controller(Employe_View view) {
        this.view = view;

        view.getAjouterBtn().addActionListener(_ -> ajouter());
        view.getModifierBtn().addActionListener(_ -> modifier());
        view.getSupprimerBtn().addActionListener(_ -> supprimer());
        view.getEnregistrerBtn().addActionListener(_ -> enregistrer());
        view.getRetourBtn().addActionListener(_ -> retour());
    }

    private void ajouter() {
        String nom = view.getNomField().getText().trim();
        String prenom = view.getPrenomField().getText().trim();
        String poste = view.getPosteField().getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || poste.isEmpty()) {
            view.showMessage("Veuillez remplir tous les champs !");
            return;
        }

        Employe emp = new Employe(nom, prenom, poste);
        listeEmployes.add(emp);

        view.getTableModel().addRow(new Object[]{emp.getNom(), emp.getPrenom(), emp.getPoste()});
        view.clearFields();
        view.showMessage("Employé ajouté !");
    }

    private void modifier() {
        int row = view.getTableEmployes().getSelectedRow();
        if (row == -1) {
            view.showMessage("Sélectionnez un employé à modifier.");
            return;
        }

        String nom = view.getNomField().getText().trim();
        String prenom = view.getPrenomField().getText().trim();
        String poste = view.getPosteField().getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || poste.isEmpty()) {
            view.showMessage("Veuillez remplir tous les champs !");
            return;
        }

        Employe emp = new Employe(nom, prenom, poste);
        listeEmployes.set(row, emp);

        view.getTableModel().setValueAt(nom, row, 0);
        view.getTableModel().setValueAt(prenom, row, 1);
        view.getTableModel().setValueAt(poste, row, 2);
        view.clearFields();
        view.showMessage("Employé modifié !");
    }

    private void supprimer() {
        int row = view.getTableEmployes().getSelectedRow();
        if (row == -1) {
            view.showMessage("Sélectionnez un employé à supprimer.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Confirmer la suppression ?", "Suppression", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listeEmployes.remove(row);
            view.getTableModel().removeRow(row);
            view.clearFields();
            view.showMessage("Employé supprimé !");
        }
    }

    private void enregistrer() {
        StringBuilder data = new StringBuilder();
        for (Employe emp : listeEmployes) {
            data.append(emp.getNom()).append(",")
                .append(emp.getPrenom()).append(",")
                .append(emp.getPoste()).append("\n");
        }

        try {
            Files.write(Paths.get("employes.csv"), data.toString().getBytes());
            view.showMessage("Données enregistrées dans employes.csv !");
        } catch (Exception e) {
            view.showMessage("Erreur d'enregistrement : " + e.getMessage());
        }
    }

    private void retour() {
        view.dispose();
        new Accueil_View();
    }
}
