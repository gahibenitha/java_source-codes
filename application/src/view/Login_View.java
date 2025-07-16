package view;

import javax.swing.*;

public class Login_View extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JComboBox<String> roleComboBox;

    public Login_View() {
        setTitle("Connexion");
        setSize(300, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 30, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 30, 150, 25);
        add(emailField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(10, 70, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 150, 25);
        add(passwordField);

        JLabel roleLabel = new JLabel("Rôle:");
        roleLabel.setBounds(30, 110, 80, 25);
        add(roleLabel);

        String[] roles = {"Administrateur", "Utilisateur"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(100, 110, 150, 25);
        add(roleComboBox);

        loginButton = new JButton("Connexion");
        loginButton.setBounds(100, 150, 150, 25);
        add(loginButton);
    }

    public JTextField getEmailField() { return emailField; }
    public JPasswordField getPasswordField() { return passwordField; }
    public JButton getLoginButton() { return loginButton; }
    public JComboBox<String> getRoleComboBox() { return roleComboBox; }
}
