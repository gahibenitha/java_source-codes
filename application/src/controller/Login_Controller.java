package controller;

import view.Login_View;
import view.MenuPrincipal_View;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Login_Controller {
    private final Login_View loginView;

    public Login_Controller(Login_View view) {
        this.loginView = view;
        initController();
    }

    private void initController() {
        loginView.getLoginButton().addActionListener(this::handleLogin);
    }

    private void handleLogin(ActionEvent e) {
        String email = loginView.getEmailField().getText().trim();
        String password = new String(loginView.getPasswordField().getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, 
                "Veuillez remplir tous les champs", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérification des identifiants
        String role = authenticate(email, password);
        
        if (role != null) {
            openMenu(role);
        } else {
            JOptionPane.showMessageDialog(loginView, 
                "Email ou mot de passe incorrect", 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private String authenticate(String email, String password) {
        // Simulation de base de données
        if (email.equals("admin@example.com") && password.equals("admin")) {
            return "admin";
        } else if (email.equals("employe@example.com") && password.equals("employe")) {
            return "employe";
        } else if (email.equals("client@example.com") && password.equals("client")) {
            return "client";
        }
        return null;
    }

    public void openMenu(String role) {
        loginView.dispose();
        SwingUtilities.invokeLater(() -> {
            try {
                MenuPrincipal_View menuView = new MenuPrincipal_View(role);
                new MenuPrincipal_Controller(menuView);
                menuView.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Erreur lors de l'ouverture du menu principal: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
                // Réafficher la vue de login en cas d'erreur
                loginView.setVisible(true);
            }
        });
    }
}