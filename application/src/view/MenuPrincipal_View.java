package view;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal_View extends JFrame {
    private JButton accueilBtn;
    private JButton clientBtn;
    private JButton employeBtn;
    private JButton studioBtn;
    private JButton logoutBtn;
    private JLabel welcomeLabel;

    public MenuPrincipal_View(String role) {
        setTitle("Menu Principal - " + role);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Label d'accueil en premier
        welcomeLabel = new JLabel("Bienvenue, " + role);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(welcomeLabel);

        // Ensuite, bouton Accueil
        accueilBtn = new JButton("Accueil");
        accueilBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        accueilBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        mainPanel.add(accueilBtn);
        mainPanel.add(Box.createVerticalStrut(10));

        // Boutons principaux
        clientBtn = new JButton("Gérer Clients");
        employeBtn = new JButton("Gérer Employés");
        studioBtn = new JButton("Gérer Studios");
        logoutBtn = new JButton("Se déconnecter");

        for (JButton btn : new JButton[]{clientBtn, employeBtn, studioBtn, logoutBtn}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            mainPanel.add(btn);
            mainPanel.add(Box.createVerticalStrut(10));
        }

        if (role.equalsIgnoreCase("client") || role.equalsIgnoreCase("utilisateur")) {
            employeBtn.setEnabled(false);
            studioBtn.setEnabled(false);
        }

        add(mainPanel);
    }

    public JButton getAccueilBtn() { return accueilBtn; }
    public JButton getClientBtn() { return clientBtn; }
    public JButton getEmployeBtn() { return employeBtn; }
    public JButton getStudioBtn() { return studioBtn; }
    public JButton getLogoutBtn() { return logoutBtn; }
    public JLabel getWelcomeLabel() { return welcomeLabel; }
}
