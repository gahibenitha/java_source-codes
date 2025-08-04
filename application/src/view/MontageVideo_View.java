package view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MontageVideo_View {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserInterface::new);
    }
}

// Panel personnalisé pour afficher une image de fond
class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        Image temp = null;
        try {
            temp = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du chargement de l'image : " + e.getMessage());
        }
        this.backgroundImage = temp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

// Interface principale pour l'utilisateur
class UserInterface extends JFrame {
    private final ArrayList<String> userChoices = new ArrayList<>();

    public UserInterface() {
        setTitle("🎬 Interface de Montage Vidéo - Utilisateur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panneau avec image de fond
        BackgroundPanel panel = new BackgroundPanel("background.jpg");
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Boutons de choix
        JButton effetsSpeciauxBtn = new JButton("✨ Effets Spéciaux");
        JButton ajoutMusiqueBtn = new JButton("🎵 Ajout Musique");
        JButton ajoutTexteBtn = new JButton("📝 Ajout Texte");
        JButton validerBtn = new JButton("✅ Valider et Voir Admin");

        // Actions des boutons
        effetsSpeciauxBtn.addActionListener(e -> userChoices.add("Effets Spéciaux"));
        ajoutMusiqueBtn.addActionListener(e -> userChoices.add("Ajout Musique"));
        ajoutTexteBtn.addActionListener(e -> userChoices.add("Ajout Texte"));

        validerBtn.addActionListener(e -> {
            if (userChoices.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez faire au moins un choix avant de valider.");
            } else {
                new AdminInterface(userChoices);
                dispose();
            }
        });

        // Ajout des boutons au panel
        panel.add(effetsSpeciauxBtn);
        panel.add(ajoutMusiqueBtn);
        panel.add(ajoutTexteBtn);
        panel.add(validerBtn);

        setContentPane(panel);
        setVisible(true);
    }
}

// Interface Admin pour afficher les choix de l'utilisateur
class AdminInterface extends JFrame {
    public AdminInterface(ArrayList<String> choices) {
        setTitle("🛠 Interface Admin - Choix de l'Utilisateur");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel("background.jpg");
        panel.setLayout(new BorderLayout());

        // Zone d'affichage
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setOpaque(false);
        displayArea.setForeground(Color.WHITE);
        displayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Affichage des choix
        StringBuilder sb = new StringBuilder("📋 L'utilisateur a choisi :\n\n");
        for (String choice : choices) {
            sb.append("• ").append(choice).append("\n");
        }
        displayArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        panel.add(scrollPane, BorderLayout.CENTER);
        setContentPane(panel);
        setVisible(true);
    }
}
