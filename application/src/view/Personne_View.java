package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import modele.Personne;

public class Personne_View extends JFrame {

    // Liste des personnes pour l'administration
    private ArrayList<Personne> personnes = new ArrayList<>();
    private JTextArea adminTextArea;

    // Champs de saisie partagés
    private JTextField nomField, prenomField, emailField, telField;
    private JComboBox<String> typeCombo;

    // Boutons
    private JButton ajouterBtn;
    private JButton modifierBtn;
    private JButton supprimerBtn;
    private JButton afficherBtn;

    // Interface principale
    public Personne_View() {
        setTitle("Application de Gestion - Client / Admin");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Onglets : Admin & Client
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Administrateur", createAdminPanel());
        tabs.add("Client", createClientPanel());

        setContentPane(new BackgroundPanel("background.jpg"));
        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }

    // Panel Admin
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // Champs de formulaire
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setOpaque(false);

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        emailField = new JTextField(20);
        telField = new JTextField(20);

        typeCombo = new JComboBox<>(new String[]{"Client", "Photographe", "Vidéographe"});

        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Email :"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Téléphone :"));
        formPanel.add(telField);
        formPanel.add(new JLabel("Type :"));
        formPanel.add(typeCombo);

        // Boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        ajouterBtn = new JButton("Ajouter");
        modifierBtn = new JButton("Modifier");
        supprimerBtn = new JButton("Supprimer");
        afficherBtn = new JButton("Afficher");

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);
        buttonPanel.add(afficherBtn);

        // Zone d'affichage
        adminTextArea = new JTextArea(10, 40);
        adminTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(adminTextArea);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scroll, BorderLayout.SOUTH);

        return panel;
    }

    // Panel Client (tu peux garder ton code actuel ici)
    private JPanel createClientPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setOpaque(false);

        JTextField nom = new JTextField();
        JTextField prenom = new JTextField();
        JTextField email = new JTextField();
        JTextField tel = new JTextField();

        panel.add(new JLabel("Nom :"));
        panel.add(nom);
        panel.add(new JLabel("Prénom :"));
        panel.add(prenom);
        panel.add(new JLabel("Email :"));
        panel.add(email);
        panel.add(new JLabel("Téléphone :"));
        panel.add(tel);

        JButton validerBtn = new JButton("Valider");
        validerBtn.addActionListener(_ -> {
            JOptionPane.showMessageDialog(this,
                    "Informations enregistrées :\n" + nom.getText() + " " + prenom.getText(),
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(new JLabel(""));
        panel.add(validerBtn);

        return panel;
    }

    // Getters publics pour que le contrôleur puisse accéder aux composants
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

    public JComboBox<String> getTypeCombo() {
        return typeCombo;
    }

    public JTextArea getAdminTextArea() {
        return adminTextArea;
    }

    public JButton getAjouterBtn() {
        return ajouterBtn;
    }

    public JButton getModifierBtn() {
        return modifierBtn;
    }

    public JButton getSupprimerBtn() {
        return supprimerBtn;
    }

    public JButton getAfficherBtn() {
        return afficherBtn;
    }

    // Méthode pour vider les champs de saisie (appelée dans le contrôleur)
    public void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        emailField.setText("");
        telField.setText("");
        typeCombo.setSelectedIndex(0);
    }

    // Fond d’écran personnalisé (classe interne)
    class BackgroundPanel extends JPanel {
        private Image background;

        public BackgroundPanel(String path) {
            try {
                background = new ImageIcon(path).getImage();
            } catch (Exception e) {
                System.out.println("Erreur chargement fond : " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null)
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Tes classes Personne et dérivées peuvent rester en dehors de cette classe, dans leur propre fichier

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Personne_View::new);
    }
}
