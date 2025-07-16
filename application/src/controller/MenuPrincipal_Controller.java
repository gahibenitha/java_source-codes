package controller;

import view.MenuPrincipal_View;
import view.Client_View;

import javax.swing.*;

public class MenuPrincipal_Controller {
    private final MenuPrincipal_View view;

    public MenuPrincipal_Controller(MenuPrincipal_View view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getAccueilBtn().addActionListener(e ->
                JOptionPane.showMessageDialog(view, "Vous êtes déjà sur la page d'accueil."));

        view.getClientBtn().addActionListener(e -> {
            view.dispose(); // Fermer le menu principal
            SwingUtilities.invokeLater(() -> {
                Client_View clientView = new Client_View();
                new Client_Controller(clientView);
                clientView.setVisible(true);
            });
        });

        view.getEmployeBtn().addActionListener(e ->
                JOptionPane.showMessageDialog(view, "Ouverture de la gestion des employés."));

        view.getStudioBtn().addActionListener(e ->
                JOptionPane.showMessageDialog(view, "Ouverture de la gestion des studios."));

        view.getLogoutBtn().addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(view, "Se déconnecter ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                view.dispose(); // Fermer la fenêtre
                // Ici, vous pouvez rediriger vers Login_View si nécessaire
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal_View view = new MenuPrincipal_View("admin");
            new MenuPrincipal_Controller(view);
            view.setVisible(true);
        });
    }
}
