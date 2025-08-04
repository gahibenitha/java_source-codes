package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Equipement_View_Admin extends JFrame {
    private JTable tableEquipements;
    private JButton ajouterBtn, modifierBtn, supprimerBtn, retourBtn;
    private JTextField searchField;
    
    public Equipement_View_Admin() {
        setTitle("Gestion des Équipements - Admin");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialisation des composants
        tableEquipements = new JTable();
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
        mainPanel.add(new JScrollPane(tableEquipements), BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Ajout de padding autour des composants
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        add(mainPanel);
    }
    
    // Getters pour les composants
    public JTable getTableEquipements() {
        return tableEquipements;
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
    
    // Méthodes pour ajouter des listeners
    public void addAjouterListener(ActionListener listener) {
        ajouterBtn.addActionListener(listener);
    }
    
    public void addModifierListener(ActionListener listener) {
        modifierBtn.addActionListener(listener);
    }
    
    public void addSupprimerListener(ActionListener listener) {
        supprimerBtn.addActionListener(listener);
    }
    
    public void addRetourListener(ActionListener listener) {
        retourBtn.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        searchField.addActionListener(listener);
    }
}