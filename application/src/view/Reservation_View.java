package view;

import javax.swing.*;

public class Reservation_View extends JFrame {
    private JComboBox<String> typeBox = new JComboBox<>(new String[]{"Photographe", "Vidéographe", "Client"});
    private JTextField dateField = new JTextField(10);
    private JTextField heureField = new JTextField(5);
    private JTextField dureeField = new JTextField(5);
    private JButton reserverBtn = new JButton("Réserver");
    private JComboBox<String> reservationList = new JComboBox<>();
    private JButton envoyerBtn = new JButton("Envoyer réponse");
    private JTextArea clientArea = new JTextArea(5, 20);
    private JTextArea adminArea = new JTextArea(5, 20);
    private JTextField prixField = new JTextField(10);
    private JComboBox<String> statutBox = new JComboBox<>(new String[]{"En attente", "Confirmée", "Refusée"});

    public Reservation_View() {
        // Initialisation interface (ajouter les composants au JFrame, layouts, etc.)
    }

    // Getters pour accéder aux composants
    public JComboBox<String> getTypeBox() {
        return typeBox;
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JTextField getHeureField() {
        return heureField;
    }

    public JTextField getDureeField() {
        return dureeField;
    }

    public JButton getReserverBtn() {
        return reserverBtn;
    }

    public JComboBox<String> getReservationList() {
        return reservationList;
    }

    public JButton getEnvoyerBtn() {
        return envoyerBtn;
    }

    public JTextArea getClientArea() {
        return clientArea;
    }

    public JTextArea getAdminArea() {
        return adminArea;
    }

    public JTextField getPrixField() {
        return prixField;
    }

    public JComboBox<String> getStatutBox() {
        return statutBox;
    }
}
