package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Client_View extends JFrame {
    private JTextField nomField, prenomField, emailField, descriptionField, telephoneField, adresseField;
    private JButton ajouterBtn, modifierBtn, supprimerBtn, retourBtn, enregistrerBtn;
    private JTable tableClients;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public Client_View() {
        setTitle("Gestion des Clients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        descriptionField = new JTextField(20);
        telephoneField = new JTextField(20);
        adresseField = new JTextField(20);

        formPanel.add(new JLabel("Nom :")); formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :")); formPanel.add(prenomField);
        formPanel.add(new JLabel("Email :")); formPanel.add(emailField);
        formPanel.add(new JLabel("Téléphone :")); formPanel.add(telephoneField);
        formPanel.add(new JLabel("Adresse :")); formPanel.add(adresseField);
        formPanel.add(new JLabel("Description :")); formPanel.add(descriptionField);

        ajouterBtn = new JButton("Ajouter");
        modifierBtn = new JButton("Modifier");
        formPanel.add(ajouterBtn);
        formPanel.add(modifierBtn);

        enregistrerBtn = new JButton("Enregistrer le client");
        formPanel.add(enregistrerBtn);
        formPanel.add(new JLabel(""));

        String[] columns = {"#", "ID", "Nom", "Prénom", "Email", "Téléphone", "Adresse", "Description"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 0 || columnIndex == 1) ? Integer.class : String.class;
            }
        };
        tableClients = new JTable(tableModel);
        scrollPane = new JScrollPane(tableClients);

        supprimerBtn = new JButton("Supprimer");
        retourBtn = new JButton("← Retour à l'accueil menu principal");

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(supprimerBtn);
        bottomPanel.add(retourBtn);

        JPanel global = new JPanel(new BorderLayout());
        global.add(formPanel, BorderLayout.NORTH);
        global.add(scrollPane, BorderLayout.CENTER);
        global.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(global);
    }

    public JTextField getNomField() { return nomField; }
    public JTextField getPrenomField() { return prenomField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getTelephoneField() { return telephoneField; }
    public JTextField getAdresseField() { return adresseField; }
    public JTextField getDescriptionField() { return descriptionField; }
    public JButton getAjouterBtn() { return ajouterBtn; }
    public JButton getModifierBtn() { return modifierBtn; }
    public JButton getSupprimerBtn() { return supprimerBtn; }
    public JButton getRetourBtn() { return retourBtn; }
    public JButton getEnregistrerBtn() { return enregistrerBtn; }
    public JTable getTableClients() { return tableClients; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JScrollPane getScrollPane() { return scrollPane; }

    public void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        emailField.setText("");
        telephoneField.setText("");
        adresseField.setText("");
        descriptionField.setText("");
    }
}