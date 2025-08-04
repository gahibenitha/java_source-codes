package view;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal_View extends JFrame {
    private JButton accueilBtn;
    private JButton clientBtn;
    private JButton employeBtn;
    private JButton studioBtn;
    private JButton equipementBtn;
    private JButton logoutBtn;
    private JButton reservationBtn; // Nouveau bouton pour les réservations
    private JLabel welcomeLabel;
    private final String role;

    public MenuPrincipal_View(String role) {
        this.role = role;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Menu Principal - " + role.toUpperCase());
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        // Configuration du style
        Font titleFont = new Font("Arial", Font.BOLD, 20);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        Dimension buttonSize = new Dimension(300, 40);

        // Label d'accueil
        welcomeLabel = new JLabel("Bienvenue, " + role.toUpperCase());
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(welcomeLabel);

        // Création des boutons
        accueilBtn = createStyledButton("Accueil", buttonFont, buttonSize);
        clientBtn = createStyledButton("Gérer Clients", buttonFont, buttonSize);
        employeBtn = createStyledButton("Gérer Employés", buttonFont, buttonSize);
        studioBtn = createStyledButton("Gérer Studios", buttonFont, buttonSize);
        logoutBtn = createStyledButton("Se déconnecter", buttonFont, buttonSize);
        reservationBtn = createStyledButton("Réservation", buttonFont, buttonSize); // Nouveau bouton

        // Bouton spécifique à l'admin (créé seulement si admin)
        if ("admin".equalsIgnoreCase(role)) {
            equipementBtn = createStyledButton("Gérer Équipements", buttonFont, buttonSize);
        }

        // Ajout des boutons
        addButtonToPanel(mainPanel, accueilBtn);
        
        // Ajout conditionnel du bouton réservation (uniquement pour les clients)
        if ("client".equalsIgnoreCase(role)) {
            addButtonToPanel(mainPanel, reservationBtn);
        }
        
        addButtonToPanel(mainPanel, clientBtn);
        addButtonToPanel(mainPanel, employeBtn);
        addButtonToPanel(mainPanel, studioBtn);

        // Ajout conditionnel du bouton équipements
        if ("admin".equalsIgnoreCase(role)) {
            addButtonToPanel(mainPanel, equipementBtn);
        }

        addButtonToPanel(mainPanel, logoutBtn);
        configurePermissions();
        add(mainPanel);
    }

    private JButton createStyledButton(String text, Font font, Dimension size) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(size);
        button.setFocusPainted(false);
        return button;
    }

    private void addButtonToPanel(JPanel panel, JButton button) {
        panel.add(button);
        panel.add(Box.createVerticalStrut(15));
    }

    private void configurePermissions() {
        switch (role.toLowerCase()) {
            case "client":
                employeBtn.setEnabled(false);
                studioBtn.setEnabled(false);
                clientBtn.setEnabled(false); // Les clients ne devraient pas gérer d'autres clients
                break;
            case "technicien":
                clientBtn.setEnabled(false);
                employeBtn.setEnabled(false);
                studioBtn.setEnabled(false);
                reservationBtn.setVisible(false);
                break;
            case "employe":
                employeBtn.setEnabled(false);
                reservationBtn.setVisible(false);
                break;
            case "photographe":
                employeBtn.setEnabled(false);
                clientBtn.setEnabled(false);
                reservationBtn.setVisible(false);
                break;
            case "admin":
                reservationBtn.setVisible(false);
                break;
        }
    }

    // Getters
    public JButton getAccueilBtn() { return accueilBtn; }
    public JButton getClientBtn() { return clientBtn; }
    public JButton getEmployeBtn() { return employeBtn; }
    public JButton getStudioBtn() { return studioBtn; }
    public JButton getEquipementBtn() { 
        return "admin".equalsIgnoreCase(role) ? equipementBtn : null;
    }
    public JButton getLogoutBtn() { return logoutBtn; }
    public JButton getReservationBtn() { return reservationBtn; } // Nouveau getter
    public String getRole() { return role; }
}