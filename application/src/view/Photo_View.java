package view;

import javax.swing.*;

public class Photo_View extends JFrame {
    private JTextField nomField, prenomField, emailField, telField, nbPhotosField;
    private JButton validerBtn;
    private JTextArea adminTextArea;

    public Photo_View() {
        // Initialisation des composants (à adapter selon ton layout)
        nomField = new JTextField(15);
        prenomField = new JTextField(15);
        emailField = new JTextField(15);
        telField = new JTextField(15);
        nbPhotosField = new JTextField(5);
        validerBtn = new JButton("Valider");
        adminTextArea = new JTextArea(10, 30);
        
        // Layout etc. (omise ici pour la clarté)
    }

    // 👉 Méthodes d'accès utilisées dans ton contrôleur :
    public JButton getValiderBtn() {
        return validerBtn;
    }

    public JTextField getNomField() {
        return nomField;
    }

    public JTextField getPrenomField() {
        return prenomField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getTelField() {
        return telField;
    }

    public JTextField getNbPhotosField() {
        return nbPhotosField;
    }

    public JTextArea getAdminTextArea() {
        return adminTextArea;
    }
}
