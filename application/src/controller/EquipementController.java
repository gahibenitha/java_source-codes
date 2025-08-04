package controller;

import modele.Equipement;
import view.Equipement_View_Admin;
import view.MenuPrincipal_View;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class EquipementController {
    private final Equipement_View_Admin view;
    private final List<Equipement> equipements;
    private final DefaultTableModel tableModel;
    private final String role;

    public EquipementController(Equipement_View_Admin view, String role) {
        this.view = view;
        this.role = role;
        this.equipements = new ArrayList<>();
        
        // Initialisation du modèle de table
        String[] columnNames = {"ID", "Nom", "Marque", "Type", "État"};
        this.tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        initialiserDonneesTest();
        view.getTableEquipements().setModel(tableModel);
        updateTableModel();
        addListeners();
    }

    private void initialiserDonneesTest() {
        equipements.add(new Equipement(1, "Ordinateur", "Dell", "Informatique", "Fonctionnel"));
        equipements.add(new Equipement(2, "Imprimante", "HP", "Bureau", "En panne"));
    }

    private void updateTableModel() {
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0);
            for (Equipement equip : equipements) {
                tableModel.addRow(new Object[]{
                    equip.getId(),
                    equip.getNom(),
                    equip.getMarque(),
                    equip.getType(),
                    equip.getEtat()
                });
            }
        });
    }

    private void addListeners() {
        view.getAjouterBtn().addActionListener(this::ajouterEquipement);
        view.getModifierBtn().addActionListener(this::modifierEquipement);
        view.getSupprimerBtn().addActionListener(this::supprimerEquipement);
        view.getRetourBtn().addActionListener(this::retourAuMenuPrincipal);
        view.getSearchField().addActionListener(this::rechercherEquipement);
    }

    private void ajouterEquipement(ActionEvent e) {
        EquipementDialog dialog = new EquipementDialog("Ajouter un équipement", null);
        
        if (dialog.getOption() == JOptionPane.OK_OPTION) {
            Equipement newEquip = new Equipement(
                genererNouvelId(),
                dialog.getNom(),
                dialog.getMarque(),
                dialog.getType(),
                dialog.getEtat()
            );
            
            equipements.add(newEquip);
            updateTableModel();
            JOptionPane.showMessageDialog(view, "Équipement ajouté avec succès!");
        }
    }

    private void modifierEquipement(ActionEvent e) {
        int selectedRow = view.getTableEquipements().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un équipement", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Equipement equip = getEquipementSelectionne();
        if (equip != null) {
            EquipementDialog dialog = new EquipementDialog("Modifier l'équipement", equip);
            
            if (dialog.getOption() == JOptionPane.OK_OPTION) {
                equip.setNom(dialog.getNom());
                equip.setMarque(dialog.getMarque());
                equip.setType(dialog.getType());
                equip.setEtat(dialog.getEtat());
                updateTableModel();
                JOptionPane.showMessageDialog(view, "Équipement modifié avec succès!");
            }
        }
    }

    private void supprimerEquipement(ActionEvent e) {
        int selectedRow = view.getTableEquipements().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un équipement", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(
            view, 
            "Êtes-vous sûr de vouloir supprimer cet équipement?", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            Equipement equip = getEquipementSelectionne();
            equipements.remove(equip);
            updateTableModel();
            JOptionPane.showMessageDialog(view, "Équipement supprimé avec succès!");
        }
    }

    private void rechercherEquipement(ActionEvent e) {
        String searchTerm = view.getSearchField().getText().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            updateTableModel();
            return;
        }
        
        List<Equipement> results = new ArrayList<>();
        for (Equipement equip : equipements) {
            if (equip.getNom().toLowerCase().contains(searchTerm) ||
                equip.getMarque().toLowerCase().contains(searchTerm) ||
                equip.getType().toLowerCase().contains(searchTerm) ||
                equip.getEtat().toLowerCase().contains(searchTerm)) {
                results.add(equip);
            }
        }
        
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0);
            for (Equipement equip : results) {
                tableModel.addRow(new Object[]{
                    equip.getId(),
                    equip.getNom(),
                    equip.getMarque(),
                    equip.getType(),
                    equip.getEtat()
                });
            }
        });
    }

    private void retourAuMenuPrincipal(ActionEvent e) {
        view.dispose();
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal_View menuView = new MenuPrincipal_View(role);
            new MenuPrincipal_Controller(menuView);
            menuView.setVisible(true);
        });
    }

    private int genererNouvelId() {
        return equipements.isEmpty() ? 1 : equipements.get(equipements.size()-1).getId() + 1;
    }

    private Equipement getEquipementSelectionne() {
        int selectedRow = view.getTableEquipements().getSelectedRow();
        int id = (int) tableModel.getValueAt(selectedRow, 0);
        for (Equipement equip : equipements) {
            if (equip.getId() == id) {
                return equip;
            }
        }
        return null;
    }

    private class EquipementDialog {
        private final JTextField nomField, marqueField, typeField, etatField;
        private final int option;
        
        public EquipementDialog(String title, Equipement equip) {
            JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
            
            nomField = new JTextField(equip != null ? equip.getNom() : "");
            marqueField = new JTextField(equip != null ? equip.getMarque() : "");
            typeField = new JTextField(equip != null ? equip.getType() : "");
            etatField = new JTextField(equip != null ? equip.getEtat() : "");
            
            panel.add(new JLabel("Nom:"));
            panel.add(nomField);
            panel.add(new JLabel("Marque:"));
            panel.add(marqueField);
            panel.add(new JLabel("Type:"));
            panel.add(typeField);
            panel.add(new JLabel("État:"));
            panel.add(etatField);
            
            option = JOptionPane.showConfirmDialog(
                view, 
                panel, 
                title, 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
        }
        
        public int getOption() { return option; }
        public String getNom() { return nomField.getText().trim(); }
        public String getMarque() { return marqueField.getText().trim(); }
        public String getType() { return typeField.getText().trim(); }
        public String getEtat() { return etatField.getText().trim(); }
    }
}