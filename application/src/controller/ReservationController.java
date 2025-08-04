package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import view.Client_View_Admin;
import view.ReservationView;

public class ReservationController {
    private Object view; // Peut être ReservationView ou Client_View_Admin
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss 'CAT' dd/MM/yyyy");

    // ✅ Constructeur pour ReservationView
    public ReservationController(ReservationView view) {
        this.view = view;
    }

    // ✅ Constructeur pour Client_View_Admin
    public ReservationController(Client_View_Admin view) {
        this.view = view;
    }

    // Exemple de méthodes communes
    public boolean validerInscription(String nom, String prenom, String tel, String email, String adresse, String sexe, String idCarte) {
        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty() || adresse.isEmpty() || sexe.isEmpty() || idCarte.isEmpty()) {
            if (view instanceof ReservationView) {
                ((ReservationView) view).showErrorMessage("Veuillez remplir tous les champs.");
            }
            return false;
        }
        return true;
    }

    public boolean validerReservation(String typeSeance, String date, String heure, String typePackage, String service) {
        if (typeSeance.isEmpty() || date.isEmpty() || heure.isEmpty() || typePackage.isEmpty() || service.isEmpty()) {
            if (view instanceof ReservationView) {
                ((ReservationView) view).showErrorMessage("Veuillez compléter toutes les informations de réservation.");
            }
            return false;
        }
        return true;
    }

    public String genererMessageConfirmation() {
        String timestamp = dateFormat.format(new Date());
        return "Réservation confirmée avec succès.\nDate de confirmation : " + timestamp;
    }
}
