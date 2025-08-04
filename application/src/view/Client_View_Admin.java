package view;

import javax.swing.*;
import java.awt.*;

public class Client_View_Admin extends JFrame {

    public Client_View_Admin() {
        setTitle("Interface Admin - Clients");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Exemple d’interface simple, à adapter selon ton projet
        JLabel label = new JLabel("Interface Client Admin");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    // Méthode pour afficher un message d'erreur dans un dialog
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    // Tu peux aussi ajouter d’autres méthodes similaires si besoin, par exemple :
    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Succès", JOptionPane.INFORMATION_MESSAGE);
    }

    // Méthode main pour tester la fenêtre
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Client_View_Admin frame = new Client_View_Admin();
            frame.setVisible(true);

            // Test de la méthode showErrorMessage
            frame.showErrorMessage("Ceci est un message d'erreur de test.");
        });
    }
}
