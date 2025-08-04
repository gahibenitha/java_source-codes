package view;

import controller.ReservationController;
import java.awt.*;
import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.time.LocalDate;
// import java.time.LocalTime;
// import javax.swing.event.ChangeEvent;
// import javax.swing.event.ChangeListener;

public class ReservationView extends JFrame {
    private ReservationController controller;
    private JTextField nomField, prenomField, telField, emailField, adresseField, idField;
    private JComboBox<String> sexeCombo;
    private JButton validerBtn;
    private String[] selectedService = new String[1];
    private JFrame currentFrame;

    public ReservationView() {
        setTitle("Réservation Studio Nelly Photographie");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        currentFrame = this;

        controller = new ReservationController(this);
        initInscriptionPanel();
    }

    private void initInscriptionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        
        formPanel.add(new JLabel("Nom:"));
        nomField = new JTextField();
        formPanel.add(nomField);

        formPanel.add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        formPanel.add(prenomField);

        formPanel.add(new JLabel("Téléphone:"));
        telField = new JTextField();
        formPanel.add(telField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Adresse:"));
        adresseField = new JTextField();
        formPanel.add(adresseField);

        formPanel.add(new JLabel("Sexe:"));
        sexeCombo = new JComboBox<>(new String[]{"Homme", "Femme"});
        formPanel.add(sexeCombo);

        formPanel.add(new JLabel("N° Carte d'identité:"));
        idField = new JTextField();
        formPanel.add(idField);

        panel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        validerBtn = new JButton("Valider");
        validerBtn.addActionListener(e -> {
            boolean isValid = controller.validerInscription(
                nomField.getText(),
                prenomField.getText(),
                telField.getText(),
                emailField.getText(),
                adresseField.getText(),
                (String) sexeCombo.getSelectedItem(),
                idField.getText()
            );
            if (isValid) {
                showServicePanel();
            }
        });
        buttonPanel.add(validerBtn);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void showServicePanel() {
        JFrame serviceFrame = new JFrame("Réservation du Service");
        serviceFrame.setSize(800, 600);
        serviceFrame.setLocationRelativeTo(null);
        currentFrame = serviceFrame;

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Bouton Retour
        JButton retourBtn = new JButton("Retour");
        retourBtn.addActionListener(e-> {
            serviceFrame.dispose();
            currentFrame = this;
            setVisible(true);
        });

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(retourBtn, BorderLayout.WEST);
        headerPanel.add(new JLabel("Choisissez un service:", JLabel.CENTER), BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel servicePanel = new JPanel(new GridLayout(2, 3, 10, 10));
        
        JButton mariageBtn = new JButton("Mariage");
        mariageBtn.addActionListener(e-> selectedService[0] = "Mariage");
        JButton doteBtn = new JButton("Dot");
        doteBtn.addActionListener(e-> selectedService[0] = "Dot");
        JButton anniversaireBtn = new JButton("Anniversaire");
        anniversaireBtn.addActionListener(e-> selectedService[0] = "Anniversaire");
        JButton baptemeBtn = new JButton("Baptême");
        baptemeBtn.addActionListener(e-> selectedService[0] = "Baptême");
        JButton autresBtn = new JButton("Autres Services");

        servicePanel.add(mariageBtn);
        servicePanel.add(doteBtn);
        servicePanel.add(anniversaireBtn);
        servicePanel.add(baptemeBtn);
        servicePanel.add(autresBtn);

        mainPanel.add(servicePanel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JComboBox<String> seanceType = new JComboBox<>(new String[]{"Photo", "Vidéo", "Photo/Vidéo"});
        JComboBox<String> packageType = new JComboBox<>(new String[]{"Simple", "VIP", "VIVIVIP"});
        
        // Sélecteur de date
        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        
        // Sélecteur d'heure
        JSpinner heureSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor heureEditor = new JSpinner.DateEditor(heureSpinner, "HH:mm");
        heureSpinner.setEditor(heureEditor);

        optionsPanel.add(new JLabel("Type de séance:"));
        optionsPanel.add(seanceType);
        optionsPanel.add(new JLabel("Date:"));
        optionsPanel.add(dateSpinner);
        optionsPanel.add(new JLabel("Heure:"));
        optionsPanel.add(heureSpinner);
        optionsPanel.add(new JLabel("Package:"));
        optionsPanel.add(packageType);

        JButton confirmerBtn = new JButton("Confirmer la réservation");
        optionsPanel.add(new JLabel());
        optionsPanel.add(confirmerBtn);

        JPanel autresServicesPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        autresServicesPanel.setVisible(false);

        String[] autresServices = {"Deuil", "Clip-vidéo", "Shooting", "Vidéo", "Concert", "Conférence", "Émission"};
        for (String service : autresServices) {
            JButton btn = new JButton(service);
            btn.addActionListener(e-> selectedService[0] = service);
            autresServicesPanel.add(btn);
        }

        autresBtn.addActionListener(e-> {
            autresServicesPanel.setVisible(!autresServicesPanel.isVisible());
            serviceFrame.revalidate();
        });

        confirmerBtn.addActionListener(e-> {
            String date = dateEditor.getTextField().getText();
            String heure = heureEditor.getTextField().getText();
            
            boolean isValid = controller.validerReservation(
                (String) seanceType.getSelectedItem(),
                date,
                heure,
                (String) packageType.getSelectedItem(),
                selectedService[0] != null ? selectedService[0] : "Non spécifié"
            );
            if (isValid) {
                JOptionPane.showMessageDialog(serviceFrame, 
                    controller.genererMessageConfirmation(), 
                    "Confirmation", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        mainPanel.add(optionsPanel, BorderLayout.SOUTH);
        mainPanel.add(autresServicesPanel, BorderLayout.EAST);

        serviceFrame.add(mainPanel);
        serviceFrame.setVisible(true);
        setVisible(false); // Cache la fenêtre d'inscription
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(currentFrame, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReservationView().setVisible(true));
    }
}