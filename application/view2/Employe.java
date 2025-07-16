package view2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Employe extends JFrame {
    private JTextField idField, nomField, prenomField, posteField;
    private JButton afficherButton, insertButton, updateButton, deleteButton;

    public Employe() {
        setTitle("Interface Admin - Employé");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // === Chargement de l'image de fond ===
        ImageIcon backgroundIcon = new ImageIcon("background.jpg"); // Assure-toi que l'image est bien présente
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // === Panel transparent pour les composants ===
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setOpaque(false); // rend le panneau transparent
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // === Champs ===
        idField = new JTextField();
        nomField = new JTextField();
        prenomField = new JTextField();
        posteField = new JTextField();

        // === Boutons ===
        afficherButton = new JButton("Afficher");
        insertButton = new JButton("Insérer");
        updateButton = new JButton("Modifier");
        deleteButton = new JButton("Supprimer");

        // === Ajout au panel ===
        panel.add(new JLabel("ID Employé :"));
        panel.add(idField);
        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom :"));
        panel.add(prenomField);
        panel.add(new JLabel("Poste :"));
        panel.add(posteField);

        panel.add(afficherButton);
        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        background.add(panel, BorderLayout.CENTER);
        setVisible(true);

        // === Actions simples ===
        afficherButton.addActionListener(e -> {
            String info = "ID: " + idField.getText() +
                          "\nNom: " + nomField.getText() +
                          "\nPrénom: " + prenomField.getText() +
                          "\nPoste: " + posteField.getText();
            JOptionPane.showMessageDialog(this, info, "Information Employé", JOptionPane.INFORMATION_MESSAGE);
        });

        insertButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Employé inséré avec succès."));
        updateButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Informations modifiées."));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Employé supprimé."));
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> new AdminInterface().setVisible(true));
    // }
}

