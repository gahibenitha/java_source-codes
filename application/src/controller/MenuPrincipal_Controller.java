package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import view.Client_View_Admin;
import view.Employe_View_Admin;
import view.Equipement_View_Admin;
import view.MenuPrincipal_View;
import view.ReservationView;

public class MenuPrincipal_Controller {
    private final MenuPrincipal_View view;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss 'CAT' dd/MM/yyyy");

    public MenuPrincipal_Controller(MenuPrincipal_View view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Bouton Accueil
        view.getAccueilBtn().addActionListener(e -> 
            JOptionPane.showMessageDialog(view, "Vous êtes sur la page d'accueil",
                                       "Information", JOptionPane.INFORMATION_MESSAGE));
        
        // Bouton Clients
        view.getClientBtn().addActionListener(e -> openView("client"));
        
        // Bouton Employés
        view.getEmployeBtn().addActionListener(e -> openView("employe"));
        
        // Bouton Studios
        view.getStudioBtn().addActionListener(_e-> 
            JOptionPane.showMessageDialog(view, "Gestion des studios",
                                       "Information", JOptionPane.INFORMATION_MESSAGE));

        // Bouton Équipements
        JButton equipementBtn = view.getEquipementBtn();
        if (equipementBtn != null) {
            equipementBtn.addActionListener(e-> openView("equipement"));
        }

        // Bouton Réservations
        JButton reservationBtn = view.getReservationBtn();
        if (reservationBtn != null) {
            reservationBtn.addActionListener(e -> openView("reservation"));
        }

        // Bouton Déconnexion
        view.getLogoutBtn().addActionListener(e-> logout());
    }

    private void openView(String viewType) {
        view.dispose();
        SwingUtilities.invokeLater(() -> {
            try {
                switch (viewType) {
                    case "client":
                        Client_View_Admin clientView = new Client_View_Admin();
                        // Si vous avez un ClientController spécifique :
                        // new ClientController(clientView, view.getRole());
                        // Sinon utiliser ReservationController :
                        new ReservationController(clientView);
                        clientView.setVisible(true);
                        break;
                    case "employe":
                        Employe_View_Admin employeView = new Employe_View_Admin();
                        new Employe_Controller(employeView, view.getRole());
                        employeView.setVisible(true);
                        break;
                    case "equipement":
                        Equipement_View_Admin equipementView = new Equipement_View_Admin();
                        new EquipementController(equipementView, view.getRole());
                        equipementView.setVisible(true);
                        break;
                    case "reservation":
                        ReservationView reservationView = new ReservationView();
                        new ReservationController(reservationView);
                        reservationView.setVisible(true);
                        break;
                }
            } catch (Exception ex) {
                showError("Erreur lors de l'ouverture de la vue " + viewType, ex);
                returnToMainMenu();
            }
        });
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(view, 
                "Voulez-vous vraiment vous déconnecter ?", 
                "Confirmation", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            showMessage("Déconnexion réussie");
            System.exit(0);
        }
    }

    private void returnToMainMenu() {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal_View menuView = new MenuPrincipal_View(view.getRole());
            new MenuPrincipal_Controller(menuView);
            menuView.setVisible(true);
        });
    }

    private void showError(String message, Exception e) {
        String timestamp = dateFormat.format(new Date());
        String fullMessage = message + "\n\nDétails : " + e.getMessage() + "\n\nHeure : " + timestamp;
        JOptionPane.showMessageDialog(null, fullMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }

    private void showMessage(String message) {
        String timestamp = dateFormat.format(new Date());
        JOptionPane.showMessageDialog(null, message + "\n\nHeure : " + timestamp, 
                                    "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal_View view = new MenuPrincipal_View("admin"); // Rôle par défaut
            new MenuPrincipal_Controller(view);
            view.setVisible(true);
        });
    }
}