package view;

import javax.swing.*;
import java.awt.*;

public class Personne_View extends JFrame {
    private static final String BG_IMAGE_PATH = "/view/background.jpg";
    private static final String[] TYPES_PERSONNE = {"Client", "Photographe", "Vidéographe"};

    private JTextArea adminTextArea;
    private JTextField nomField, prenomField, emailField, telField;
    private JComboBox<String> typeCombo;
    private JButton ajouterBtn, modifierBtn, supprimerBtn, afficherBtn;

    public Personne_View() {
        initUI();
        setupWindow();
    }

    private void initUI() {
        setTitle("Application de Gestion - Client / Admin");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Administrateur", createAdminPanel());
        tabs.add("Client", createClientPanel());

        BackgroundPanel backgroundPanel = new BackgroundPanel(BG_IMAGE_PATH);
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(tabs, BorderLayout.CENTER);
        setContentPane(backgroundPanel);
    }

    private void setupWindow() {
        setVisible(true);
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(createFormPanel(), BorderLayout.NORTH);
        panel.add(createButtonPanel(), BorderLayout.CENTER);
        panel.add(createScrollPane(), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setOpaque(false);

        nomField = createTextField();
        prenomField = createTextField();
        emailField = createTextField();
        telField = createTextField();
        typeCombo = new JComboBox<>(TYPES_PERSONNE);

        addFormField(formPanel, "Nom :", nomField);
        addFormField(formPanel, "Prénom :", prenomField);
        addFormField(formPanel, "Email :", emailField);
        addFormField(formPanel, "Téléphone :", telField);
        addFormField(formPanel, "Type :", typeCombo);

        return formPanel;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lbl);
        panel.add(field);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setOpaque(false);

        ajouterBtn = createButton("Ajouter", Color.GREEN.darker());
        modifierBtn = createButton("Modifier", Color.BLUE);
        supprimerBtn = createButton("Supprimer", Color.RED);
        afficherBtn = createButton("Afficher", Color.ORANGE);

        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);
        buttonPanel.add(afficherBtn);

        return buttonPanel;
    }

    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(120, 35));
        return btn;
    }

    private JScrollPane createScrollPane() {
        adminTextArea = new JTextArea(15, 50);
        adminTextArea.setEditable(false);
        adminTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(adminTextArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Liste des Personnes"));
        return scroll;
    }

    private JPanel createClientPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nom = createTextField();
        JTextField prenom = createTextField();
        JTextField email = createTextField();
        JTextField tel = createTextField();

        addClientField(panel, gbc, "Nom :", nom, 0);
        addClientField(panel, gbc, "Prénom :", prenom, 1);
        addClientField(panel, gbc, "Email :", email, 2);
        addClientField(panel, gbc, "Téléphone :", tel, 3);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton validerBtn = createButton("Valider", new Color(0, 150, 0));
        validerBtn.addActionListener(e-> showConfirmation(nom.getText(), prenom.getText()));
        panel.add(validerBtn, gbc);

        return panel;
    }

    private void addClientField(JPanel panel, GridBagConstraints gbc, String label, JTextField field, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void showConfirmation(String nom, String prenom) {
        JOptionPane.showMessageDialog(this,
            "<html><b>Informations enregistrées :</b><br>" +
            nom + " " + prenom + "</html>",
            "Confirmation",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters
    public JTextField getNomField() { return nomField; }
    public JTextField getPrenomField() { return prenomField; }
    public JTextField getEmailField() { return emailField; }
    public JTextField getTelField() { return telField; }
    public JComboBox<String> getTypeCombo() { return typeCombo; }
    public JTextArea getAdminTextArea() { return adminTextArea; }
    public JButton getAjouterBtn() { return ajouterBtn; }
    public JButton getModifierBtn() { return modifierBtn; }
    public JButton getSupprimerBtn() { return supprimerBtn; }
    public JButton getAfficherBtn() { return afficherBtn; }

    public void clearFields() {
        nomField.setText("");
        prenomField.setText("");
        emailField.setText("");
        telField.setText("");
        typeCombo.setSelectedIndex(0);
        nomField.requestFocus();
    }

    static class BackgroundPanel extends JPanel {
        private final Image background;

        public BackgroundPanel(String path) {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                background = new ImageIcon(imgURL).getImage();
            } else {
                System.err.println("Image non trouvée : " + path);
                background = null;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (background != null) {
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new Personne_View();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
