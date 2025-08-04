package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Employe_View_Admin extends JFrame {
    private JTable tableEmployes;
    private JButton ajouterBtn, modifierBtn, supprimerBtn, retourBtn;
    private JTextField searchField;
    
    public Employe_View_Admin() {
        setTitle("Gestion des Employés - Admin");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialisation des composants
        tableEmployes = new JTable();
        ajouterBtn = new JButton("Ajouter");
        modifierBtn = new JButton("Modifier");
        supprimerBtn = new JButton("Supprimer");
        retourBtn = new JButton("Retour au menu");
        searchField = new JTextField(20);
        
        // Configuration du layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 0)); // 1 ligne, 4 colonnes
        
        // Panel pour la recherche
        topPanel.add(new JLabel("Rechercher:"));
        topPanel.add(searchField);
        
        // Configuration des boutons
        buttonPanel.add(ajouterBtn);
        buttonPanel.add(modifierBtn);
        buttonPanel.add(supprimerBtn);
        buttonPanel.add(retourBtn);
        
        // Style des boutons
        retourBtn.setBackground(new Color(255, 100, 100)); // Rouge clair pour le bouton retour
        retourBtn.setForeground(Color.WHITE);
        
        // Ajout des panels au mainPanel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(tableEmployes), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Ajout de padding autour des composants
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        add(mainPanel);
    }
    
    // Getters pour les composants
    public JTable getTableEmployes() {
        return tableEmployes;
    }
    
    public JButton getAjouterBtn() {
        return ajouterBtn;
    }
    
    public JButton getModifierBtn() {
        return modifierBtn;
    }
    
    public JButton getSupprimerBtn() {
        return supprimerBtn;
    }
    
    public JButton getRetourBtn() {
        return retourBtn;
    }
    
    public JTextField getSearchField() {
        return searchField;
    }
    
    // Méthode pour ajouter un listener au bouton retour
    public void addRetourListener(ActionListener listener) {
        retourBtn.addActionListener(listener);
    }
}