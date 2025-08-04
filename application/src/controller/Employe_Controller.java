package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modele.Employe;
import view.Employe_View_Admin;
import view.MenuPrincipal_View;

public class Employe_Controller {
    private final Employe_View_Admin view;
    private final String role;
    private DefaultTableModel tableModel;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Employe_Controller(Employe_View_Admin view, String role) {
        this.view = view;
        this.role = role;
        initController();
    }

    private void initController() {
        initializeTableModel();
        loadSampleData();
        setupButtonActions();
        checkPermissions();
    }

    private void initializeTableModel() {
        String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Téléphone", "Poste", "Salaire", "Date Embauche"};
        this.tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        view.getTableEmployes().setModel(tableModel);
    }

    private void loadSampleData() {
        Object[][] sampleData = {
            {1, "Dupont", "Jean", "jean.dupont@email.com", "0123456789", "Manager", "5000", "2023-01-15"},
            {2, "Martin", "Sophie", "sophie.martin@email.com", "0987654321", "Développeur", "4000", "2023-03-20"}
        };

        for (Object[] row : sampleData) {
            tableModel.addRow(row);
        }
    }

    private void setupButtonActions() {
        view.getAjouterBtn().addActionListener(this::handleAjouterAction);
        view.getModifierBtn().addActionListener(this::handleModifierAction);
        view.getSupprimerBtn().addActionListener(this::handleSupprimerAction);
        view.getRetourBtn().addActionListener(this::handleRetourAction);
    }

    private void handleRetourAction(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
            view,
            "Voulez-vous retourner au menu principal?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            SwingUtilities.invokeLater(() -> {
                MenuPrincipal_View menuView = new MenuPrincipal_View(role);
                new MenuPrincipal_Controller(menuView);
                menuView.setVisible(true);
            });
        }
    }

    private void handleAjouterAction(ActionEvent e) {
        if (!"admin".equals(role)) {
            showErrorMessage("Action non autorisée");
            return;
        }
        showEmployeeDialog("Ajouter un employé", null);
    }

    private void handleModifierAction(ActionEvent e) {
        if (!"admin".equals(role)) {
            showErrorMessage("Action non autorisée");
            return;
        }

        int selectedRow = view.getTableEmployes().getSelectedRow();
        if (selectedRow == -1) {
            showWarningMessage("Veuillez sélectionner un employé");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nom = (String) tableModel.getValueAt(selectedRow, 1);
        String prenom = (String) tableModel.getValueAt(selectedRow, 2);
        String email = (String) tableModel.getValueAt(selectedRow, 3);
        String telephone = (String) tableModel.getValueAt(selectedRow, 4);
        String poste = (String) tableModel.getValueAt(selectedRow, 5);
        String salaire = (String) tableModel.getValueAt(selectedRow, 6);
        String dateEmbauche = (String) tableModel.getValueAt(selectedRow, 7);

        Employe employe = new Employe(id, nom, prenom, email, telephone, poste, salaire, dateEmbauche);
        showEmployeeDialog("Modifier un employé", employe);
    }

    private void handleSupprimerAction(ActionEvent e) {
        if (!"admin".equals(role)) {
            showErrorMessage("Action non autorisée");
            return;
        }

        int selectedRow = view.getTableEmployes().getSelectedRow();
        if (selectedRow == -1) {
            showWarningMessage("Veuillez sélectionner un employé");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            view,
            "Êtes-vous sûr de vouloir supprimer cet employé?",
            "Confirmation",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(selectedRow);
            showSuccessMessage("Employé supprimé avec succès");
        }
    }

    private void showEmployeeDialog(String title, Employe employe) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField nomField = new JTextField(employe != null ? employe.getNom() : "");
        JTextField prenomField = new JTextField(employe != null ? employe.getPrenom() : "");
        JTextField emailField = new JTextField(employe != null ? employe.getEmail() : "");
        JTextField telField = new JTextField(employe != null ? employe.getTelephone() : "");
        JTextField posteField = new JTextField(employe != null ? employe.getPoste() : "");
        JTextField salaireField = new JTextField(employe != null ? employe.getSalaire() : "");
        JTextField dateField = new JTextField(employe != null ? employe.getDateEmbauche() : dateFormat.format(new Date()));

        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Téléphone:"));
        formPanel.add(telField);
        formPanel.add(new JLabel("Poste:"));
        formPanel.add(posteField);
        formPanel.add(new JLabel("Salaire:"));
        formPanel.add(salaireField);
        formPanel.add(new JLabel("Date Embauche:"));
        formPanel.add(dateField);

        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener(e -> {
            try {
                validateAndSaveEmployee(dialog, employe, nomField, prenomField, emailField,
                        telField, posteField, salaireField, dateField);
            } catch (IllegalArgumentException ex) {
                showErrorMessage(ex.getMessage());
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(validerButton);
        buttonPanel.add(annulerButton);

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    }

    private void validateAndSaveEmployee(JDialog dialog, Employe employe,
                                         JTextField nomField, JTextField prenomField,
                                         JTextField emailField, JTextField telField,
                                         JTextField posteField, JTextField salaireField,
                                         JTextField dateField) {
        if (nomField.getText().trim().isEmpty() || prenomField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom et prénom sont obligatoires");
        }

        if (employe == null) {
            // Ajout
            Object[] rowData = {
                tableModel.getRowCount() + 1,
                nomField.getText().trim(),
                prenomField.getText().trim(),
                emailField.getText().trim(),
                telField.getText().trim(),
                posteField.getText().trim(),
                salaireField.getText().trim(),
                dateField.getText().trim()
            };
            tableModel.addRow(rowData);
            showSuccessMessage("Employé ajouté avec succès");
        } else {
            // Modification
            int selectedRow = view.getTableEmployes().getSelectedRow();
            tableModel.setValueAt(nomField.getText().trim(), selectedRow, 1);
            tableModel.setValueAt(prenomField.getText().trim(), selectedRow, 2);
            tableModel.setValueAt(emailField.getText().trim(), selectedRow, 3);
            tableModel.setValueAt(telField.getText().trim(), selectedRow, 4);
            tableModel.setValueAt(posteField.getText().trim(), selectedRow, 5);
            tableModel.setValueAt(salaireField.getText().trim(), selectedRow, 6);
            tableModel.setValueAt(dateField.getText().trim(), selectedRow, 7);
            showSuccessMessage("Employé modifié avec succès");
        }
        dialog.dispose();
    }

    private void checkPermissions() {
        boolean isAdmin = "admin".equals(role);
        view.getAjouterBtn().setEnabled(isAdmin);
        view.getModifierBtn().setEnabled(isAdmin);
        view.getSupprimerBtn().setEnabled(isAdmin);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(view, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(view, message, "Avertissement", JOptionPane.WARNING_MESSAGE);
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(view, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }
}
