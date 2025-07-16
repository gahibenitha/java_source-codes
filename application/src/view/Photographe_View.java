package view;

import javax.swing.*;

public class Photographe_View extends JFrame {
    private JButton reserverBtn = new JButton("Réserver");
    private JButton confirmerBtn = new JButton("Confirmer");
    private JTextField photographeNomField = new JTextField(20);
    private JTextField specialiteField = new JTextField(20);
    private JTextArea clientInfoArea = new JTextArea(5, 20);
    private JTextArea adminInfoArea = new JTextArea(5, 20);

    public Photographe_View() {
        // initialisation interface (ajouter boutons, champs, etc.)
    }

    public JButton getReserverBtn() {
        return reserverBtn;
    }

    public JButton getConfirmerBtn() {
        return confirmerBtn;
    }

    public JTextField getPhotographeNomField() {
        return photographeNomField;
    }

    public JTextField getSpecialiteField() {
        return specialiteField;
    }

    public JTextArea getClientInfoArea() {
        return clientInfoArea;
    }

    public JTextArea getAdminInfoArea() {
        return adminInfoArea;
    }
}
