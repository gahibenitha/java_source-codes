package view;

import javax.swing.*;

public class Login_View extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JComboBox<String> roleComboBox;

    public Login_View() {
        setTitle("Connexion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        pack();  // Ajuste la taille selon les composants
    }

    private void initComponents() {
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordField = new JPasswordField(20);

        JLabel roleLabel = new JLabel("Rôle:");
        String[] roles = {"Administrateur", "Utilisateur"};
        roleComboBox = new JComboBox<>(roles);

        loginButton = new JButton("Connexion");

        // Utilisation de GroupLayout pour un bon positionnement
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(emailLabel)
                    .addComponent(passwordLabel)
                    .addComponent(roleLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(emailField)
                    .addComponent(passwordField)
                    .addComponent(roleComboBox)
                    .addComponent(loginButton, GroupLayout.Alignment.CENTER))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(roleLabel)
                    .addComponent(roleComboBox))
                .addComponent(loginButton)
        );

        add(panel);
    }

    // Getters pour contrôleur
    public JTextField getEmailField() { return emailField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
    public JComboBox<String> getRoleComboBox() { return roleComboBox; }
}
