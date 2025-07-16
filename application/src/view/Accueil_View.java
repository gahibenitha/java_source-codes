package view;

import javax.swing.*;
import java.awt.*;

public class Accueil_View extends JFrame {
    private JLabel titleLabel;
    private JLabel messageLabel;
    private JButton entrerButton;

    public Accueil_View() {
        setTitle("Accueil");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panneau principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 20)); // Aligné à droite

        // Titre
        titleLabel = new JLabel("Bienvenue dans l'application");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(titleLabel);

        panel.add(Box.createVerticalStrut(15));

        // Message
        messageLabel = new JLabel("Veuillez cliquer sur le bouton pour commencer");
        messageLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(messageLabel);

        panel.add(Box.createVerticalStrut(25));

        // Bouton Entrer
        entrerButton = new JButton("Entrer");
        entrerButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        panel.add(entrerButton);

        add(panel);
    }

    public JButton getEntrerButton() { return entrerButton; }
    public JLabel getTitleLabel() { return titleLabel; }
    public JLabel getMessageLabel() { return messageLabel; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Accueil_View().setVisible(true);
        });
    }
}
