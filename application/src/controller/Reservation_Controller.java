package controller;
import java.util.ArrayList;
import javax.swing.*;
import view.Reservation_View;


public class Reservation_Controller {
    private Reservation_View vue;
    private ArrayList<Reservation> reservations;

    public Reservation_Controller(Reservation_View vue) {
        this.vue = vue;
        this.reservations = new ArrayList<>();

        vue.getReserverBtn().addActionListener(_-> envoyerReservation());
        vue.getReservationList().addActionListener(_ -> afficherReservation());
        vue.getEnvoyerBtn().addActionListener(_-> envoyerReponse());
    }

    private void envoyerReservation() {
        String type = (String) vue.getTypeBox().getSelectedItem();
        String date = vue.getDateField().getText().trim();
        String heure = vue.getHeureField().getText().trim();
        String duree = vue.getDureeField().getText().trim();

        if (date.isEmpty() || heure.isEmpty() || duree.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            return;
        }

        Reservation r = new Reservation(type, date, heure, duree);
        reservations.add(r);
        vue.getReservationList().addItem("Réservation #" + (reservations.size() - 1));

        vue.getClientArea().setText("Réservation envoyée !\n" + r);
    }

    private void afficherReservation() {
        int index = vue.getReservationList().getSelectedIndex();
        if (index >= 0 && index < reservations.size()) {
            vue.getAdminArea().setText(reservations.get(index).toString());
        }
    }

    private void envoyerReponse() {
        int index = vue.getReservationList().getSelectedIndex();
        if (index >= 0 && index < reservations.size()) {
            try {
                double prix = Double.parseDouble(vue.getPrixField().getText());
                String statut = (String) vue.getStatutBox().getSelectedItem();
                Reservation r = reservations.get(index);
                r.setPrix(prix);
                r.setStatut(statut);
                vue.getAdminArea().setText("Réponse envoyée au client.\n" + r);
                vue.getClientArea().setText("Réservation mise à jour :\n" + r +
                        "\n\nConfirmez-vous cette réservation ?");
                int confirmation = JOptionPane.showConfirmDialog(null, "Acceptez-vous le prix ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    r.setConfirmation("Acceptée par le client");
                    vue.getClientArea().setText("Réservation confirmée !\n" + r);
                } else {
                    r.setConfirmation("Refusée par le client");
                    vue.getClientArea().setText("Réservation annulée par le client.\n" + r);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Prix invalide.");
            }
        }
    }

    static class Reservation {
        String type, date, heure, duree;
        double prix = 0;
        String statut = "En attente";
        String confirmation = "Non confirmé";

        public Reservation(String type, String date, String heure, String duree) {
            this.type = type;
            this.date = date;
            this.heure = heure;
            this.duree = duree;
        }

        public void setPrix(double prix) { this.prix = prix; }
        public void setStatut(String statut) { this.statut = statut; }
        public void setConfirmation(String confirmation) { this.confirmation = confirmation; }

        @Override
        public String toString() {
            return "Type        : " + type +
                 "\nDate        : " + date +
                 "\nHeure       : " + heure +
                 "\nDurée       : " + duree +
                 "\nStatut      : " + statut +
                 "\nPrix        : " + prix + " €" +
                 "\nConfirmation: " + confirmation;
        }
    }
}

