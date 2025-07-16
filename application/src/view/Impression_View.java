package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import modele.Impression;

public class Impression_View extends JFrame {
    private JTextField quantiteField, idField;
    private JComboBox<String> formatBox, papierBox;
    private JTextArea adminOutput;
    private JButton insertBtn, updateBtn, deleteBtn, afficherBtn;

    private final String[] formats = {"A4", "A5", "10x15", "13x18"};
    private final String[] papiers = {"Glacé", "Mat", "Recyclé"};

    public Impression_View() {
        setTitle("Gestion d'Impressions - Utilisateur & Admin");
        setSize(750, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ==== Arrière-plan ====
        ImageIcon bgIcon = new ImageIcon("background.jpg");
        Image bgImg = bgIcon.getImage().getScaledInstance(750, 550, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(bgImg));
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // ==== Panneau utilisateur ====
        JPanel userPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        userPanel.setOpaque(false);
        userPanel.setBorder(BorderFactory.createTitledBorder("Utilisateur - Nouvelle impression"));

        quantiteField = new JTextField();
        formatBox = new JComboBox<>(formats);
        papierBox = new JComboBox<>(papiers);
        insertBtn = new JButton("Commander");
        updateBtn = new JButton("Modifier");

        userPanel.add(new JLabel("Quantité de photos :"));
        userPanel.add(quantiteField);
        userPanel.add(new JLabel("Format :"));
        userPanel.add(formatBox);
        userPanel.add(new JLabel("Papier :"));
        userPanel.add(papierBox);
        userPanel.add(insertBtn);
        userPanel.add(updateBtn);

        // ==== Panneau admin ====
        JPanel adminPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        adminPanel.setOpaque(false);
        adminPanel.setBorder(BorderFactory.createTitledBorder("Admin - Gestion des impressions"));

        idField = new JTextField();
        deleteBtn = new JButton("Supprimer");
        afficherBtn = new JButton("Afficher tout");

        adminPanel.add(new JLabel("ID à gérer :"));
        adminPanel.add(idField);
        adminPanel.add(deleteBtn);
        adminPanel.add(afficherBtn);

        // ==== TextArea de sortie ====
        adminOutput = new JTextArea();
        adminOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(adminOutput);

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.setOpaque(false);
        topPanel.add(userPanel);
        topPanel.add(adminPanel);

        background.add(topPanel, BorderLayout.NORTH);
        background.add(scrollPane, BorderLayout.CENTER);
    }

    // ==== Méthodes pour le contrôleur ====

    public JButton getAjouterBtn() {
        return insertBtn;
    }

    public JButton getModifierBtn() {
        return updateBtn;
    }

    public JButton getSupprimerBtn() {
        return deleteBtn;
    }

    public JButton getAfficherBtn() {
        return afficherBtn;
    }

    public JTextField getIdField() {
        return idField;
    }

    public JTextField getQuantiteField() {
        return quantiteField;
    }

    public JComboBox<String> getFormatBox() {
        return formatBox;
    }

    public JComboBox<String> getPapierBox() {
        return papierBox;
    }

    public void setAdminOutput(String text) {
        adminOutput.setText(text);
    }

    public void appendAdminOutput(String text) {
        adminOutput.append(text);
    }

    public void clearFields() {
        quantiteField.setText("");
        idField.setText("");
        formatBox.setSelectedIndex(0);
        papierBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Impression_View().setVisible(true));
    }
}
