package controller;

import dao.ClientDAO;
import modele.Client;
import view.Client_View;
import view.MenuPrincipal_View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class Client_Controller {
    private final Client_View view;
    private final ClientDAO clientDAO;

    public Client_Controller(Client_View view) {
        this.view = view;

        try {
            clientDAO = new ClientDAO();
            chargerClientsDepuisBase();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }

        view.getAjouterBtn().addActionListener(_ -> ajouterClient());
        view.getModifierBtn().addActionListener(_ -> JOptionPane.showMessageDialog(view, "Modification en base non implémentée."));
        view.getSupprimerBtn().addActionListener(_ -> JOptionPane.showMessageDialog(view, "Suppression en base non implémentée."));
        view.getRetourBtn().addActionListener(_ -> retourAccueil());
        view.getTableClients().getSelectionModel().addListSelectionListener(_ -> remplirChamps());
    }

    private void chargerClientsDepuisBase() {
        try {
            List<Client> clients = clientDAO.getTousLesClients();
            DefaultTableModel model = view.getTableModel();
            for (Client c : clients) {
                model.addRow(new Object[]{
                        c.getNom(), c.getPrenom(), c.getEmail(), c.getDescription()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erreur de chargement des clients : " + e.getMessage());
        }
    }

    private void ajouterClient() {
        if (!validerChamps()) return;

        Client client = new Client(
                view.getNomField().getText(),
                view.getPrenomField().getText(),
                view.getEmailField().getText(),
                "",
                view.getDescriptionField().getText()
        );

        try {
            clientDAO.ajouterClient(client);
            view.getTableModel().addRow(new Object[]{
                    client.getNom(), client.getPrenom(), client.getEmail(), client.getDescription()
            });
            view.clearFields();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    private void remplirChamps() {
        int row = view.getTableClients().getSelectedRow();
        if (row != -1) {
            view.getNomField().setText(view.getTableModel().getValueAt(row, 0).toString());
            view.getPrenomField().setText(view.getTableModel().getValueAt(row, 1).toString());
            view.getEmailField().setText(view.getTableModel().getValueAt(row, 2).toString());
            view.getDescriptionField().setText(view.getTableModel().getValueAt(row, 3).toString());
        }
    }

    private void retourAccueil() {
        view.dispose();
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal_View accueil = new MenuPrincipal_View("admin");
            new MenuPrincipal_Controller(accueil);
            accueil.setVisible(true);
        });
    }

    private boolean validerChamps() {
        if (view.getNomField().getText().trim().isEmpty()
                || view.getPrenomField().getText().trim().isEmpty()
                || view.getEmailField().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Champs obligatoires manquants.");
            return false;
        }
        return true;
    }
}
