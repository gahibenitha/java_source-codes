package view;

import javax.swing.*;
import java.awt.*;

public class Videographe_View extends JFrame {
    private JTextField videographeNomField;
    private JTextField specialiteField;
    private JTextArea clientInfoArea;
    private JTextArea adminInfoArea;
    private JButton reserverBtn;
    private JButton confirmerBtn;

    public Videographe_View() {
        setTitle("Réservation Vidéographe");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        videographeNomField = new JTextField(20);
        specialiteField = new JTextField(20);
        clientInfoArea = new JTextArea(5, 40);
        clientInfoArea.setEditable(false);
        adminInfoArea = new JTextArea(5, 40);
        adminInfoArea.setEditable(false);

        reserverBtn = new JButton("Réserver");
        confirmerBtn = new JButton("Confirmer");

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Nom vidéographe :"));
        inputPanel.add(videographeNomField);
        inputPanel.add(new JLabel("Spécialité :"));
        inputPanel.add(specialiteField);
        inputPanel.add(reserverBtn);
        inputPanel.add(confirmerBtn);

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        textPanel.add(new JScrollPane(clientInfoArea));
        textPanel.add(new JScrollPane(adminInfoArea));

        setLayout(new BorderLayout(5, 5));
        add(inputPanel, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public JTextField getVideographeNomField() {
        return videographeNomField;
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

    public JButton getReserverBtn() {
        return reserverBtn;
    }

    public JButton getConfirmerBtn() {
        return confirmerBtn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Videographe_View::new);
    }
}
