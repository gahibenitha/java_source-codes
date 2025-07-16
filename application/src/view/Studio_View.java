package view;

import javax.swing.*;

public class Studio_View extends JFrame {
    private JButton resBtn = new JButton("Réservation");
    private JButton photoBtn = new JButton("Photographe");
    private JButton videoBtn = new JButton("Vidéographe");
    private JButton impBtn = new JButton("Impression");
    private JButton adminBtn = new JButton("Admin");
    private JLabel nomLabel = new JLabel("Nom du studio");
    private JLabel adresseLabel = new JLabel("Adresse du studio");

    public Studio_View() {
        setTitle("Studio");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        resBtn.setBounds(20, 20, 120, 30);
        photoBtn.setBounds(20, 60, 120, 30);
        videoBtn.setBounds(20, 100, 120, 30);
        impBtn.setBounds(20, 140, 120, 30);
        adminBtn.setBounds(20, 180, 120, 30);
        nomLabel.setBounds(160, 20, 200, 30);
        adresseLabel.setBounds(160, 60, 200, 30);

        add(resBtn);
        add(photoBtn);
        add(videoBtn);
        add(impBtn);
        add(adminBtn);
        add(nomLabel);
        add(adresseLabel);

        setVisible(true);
    }

    public JButton getResBtn() {
        return resBtn;
    }

    public JButton getPhotoBtn() {
        return photoBtn;
    }

    public JButton getVideoBtn() {
        return videoBtn;
    }

    public JButton getImpBtn() {
        return impBtn;
    }

    public JButton getAdminBtn() {
        return adminBtn;
    }

    public JLabel getNomLabel() {
        return nomLabel;
    }

    public JLabel getAdresseLabel() {
        return adresseLabel;
    }
}
