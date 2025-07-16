package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Employe_View extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField posteField;

    private JButton ajouterBtn, modifierBtn, supprimerBtn, enregistrerBtn, retourBtn;
    private JTable tableEmployes;
    private DefaultTableModel tableModel;

    public Employe_View() {
        setTitle("Gestion des Employés");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Formulaire
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Informations Employé"));

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        posteField = new JTextField(20);

        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Poste :"));
        formPanel.add(posteField);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        ajouterBtn = new JButton("Ajouter");
        modifierBtn = new JButton("Modifier");
        supprimerBtn = new JButton("Supprimer");
        enregistrerBtn = new JButton("Enregistrer");
        retourBtn = new JButton("Retour");

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);
        buttonPanel.add(enregistrerBtn);
        buttonPanel.add(retourBtn);

        // Tableau
        String[] columnNames = {"Nom", "Prénom", "Poste"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableEmployes = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(tableEmployes);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Liste des employés"));

        // Layout
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(tableScroll, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Getters
    public JTextField getNomField() { return nomField; }
    public JTextField getPrenomField() { return prenomField; }
    public JTextField getPosteField() { return posteField; }

    public JButton getAjouterBtn() { return ajouterBtn; }
    public JButton getModifierBtn() { return modifierBtn; }
    public JButton getSupprimerBtn() { return supprimerBtn; }
    public JButton getEnregistrerBtn() { return enregistrerBtn; }
    public JButton getRetourBtn() { return retourBtn; }

    public JTable getTableEmployes() { return tableEmployes; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        posteField.setText("");
    }
}
