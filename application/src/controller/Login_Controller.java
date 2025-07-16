package controller;

import view.Login_View;
import view.MenuPrincipal_View;

import javax.swing.JOptionPane;

public class Login_Controller {
    private final Login_View view;

    public Login_Controller(Login_View view) {
        this.view = view;

        view.getLoginButton().addActionListener(_ -> {
            String email = view.getEmailField().getText();
            String password = new String(view.getPasswordField().getPassword());

            if (email.equals("admin@example.com") && password.equals("admin")) {
                openMenu("admin");
            } else if (email.equals("client@example.com") && password.equals("client")) {
                openMenu("client");
            } else {
                JOptionPane.showMessageDialog(view, "Email ou mot de passe incorrect");
            }
        });
    }

    private void openMenu(String role) {
        view.dispose(); // Ferme la fenêtre de connexion

        MenuPrincipal_View menuView = new MenuPrincipal_View(role);
        new MenuPrincipal_Controller(menuView); // ✅ Appel corrigé
        menuView.setVisible(true);
    }
}
