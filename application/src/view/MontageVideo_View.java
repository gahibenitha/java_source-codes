package view;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;
import java.util.ArrayList;

public class MontageVideo_View {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserInterface();
        });
    }
}

// Panel personnalisé avec image de fond
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } catch (Exception e) {
            System.out.println("Erreur de chargement de l'image : " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class UserInterface extends JFrame {
    private ArrayList<String> userChoices = new ArrayList<>();

    public UserInterface() {
        setTitle("Interface de Montage Vidéo - Utilisateur");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Utiliser un JPanel avec image de fond
        BackgroundPanel panel = new BackgroundPanel("background.jpg");
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton effetsSpeciauxBtn = new JButton("Effets Spéciaux");
        JButton ajoutMusiqueBtn = new JButton("Ajout Musique");
        JButton ajoutTexteBtn = new JButton("Ajout Texte");
        JButton validerBtn = new JButton("Valider et Voir Admin");

        effetsSpeciauxBtn.addActionListener(_ -> userChoices.add("Effets Spéciaux"));
        ajoutMusiqueBtn.addActionListener(_ -> userChoices.add("Ajout Musique"));
        ajoutTexteBtn.addActionListener(_ -> userChoices.add("Ajout Texte"));

        validerBtn.addActionListener(_ -> {
            new AdminInterface(userChoices);
            dispose();
        });

        panel.add(effetsSpeciauxBtn);
        panel.add(ajoutMusiqueBtn);
        panel.add(ajoutTexteBtn);
        panel.add(validerBtn);

        setContentPane(panel);
        setVisible(true);
    }
}

class AdminInterface extends JFrame {
    public AdminInterface(ArrayList<String> choices) {
        setTitle("Interface Admin - Choix de l'Utilisateur");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel panel = new BackgroundPanel("background.jpg");
        panel.setLayout(new BorderLayout());

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setOpaque(false); // Rendre transparent
        displayArea.setForeground(Color.WHITE); // Texte blanc

        StringBuilder sb = new StringBuilder("L'utilisateur a choisi :\n");
        for (String choice : choices) {
            sb.append("- ").append(choice).append("\n");
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
