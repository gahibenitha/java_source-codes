package view;

import javax.swing.*;
import java.awt.*;

public class Video_View extends JFrame {

    private JTextField nomField, prenomField, emailField, telField, dureeVideoField;
    private JTextArea adminTextArea;
    private JButton validerBtn;

    public Video_View() {
        setTitle("Gestion Vidéo");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des champs
        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        telField = new JTextField(20);
        dureeVideoField = new JTextField(10);

        validerBtn = new JButton("Valider");

        adminTextArea = new JTextArea(10, 40);
        adminTextArea.setEditable(false);

        // Layout
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Email :"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Téléphone :"));
        formPanel.add(telField);
        formPanel.add(new JLabel("Durée vidéo (min) :"));
        formPanel.add(dureeVideoField);

        formPanel.add(new JLabel(""));
        formPanel.add(validerBtn);

        JScrollPane scrollPane = new JScrollPane(adminTextArea);

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Getters utilisés dans le contrôleur
    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getPrenomField() {
        return prenomField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getTelField() {
        return telField;
    }

    public JTextField getDureeVideoField() {
        return dureeVideoField;
    }

    public JTextArea getAdminTextArea() {
        return adminTextArea;
    }

    public JButton getValiderBtn() {
        return validerBtn;
    }

    // Main pour tester la vue seule
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Video_View::new);
    }
}
