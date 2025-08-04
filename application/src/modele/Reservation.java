package modele;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    public long id;
    public String typeSeance;
    public LocalDate date;
    public LocalTime heure;
    public String packageType;
    public String service;
    public String status;

    public Reservation() {
    }

    public Reservation(String typeSeance, LocalDate date, LocalTime heure, 
                      String packageType, String service) {
        this.typeSeance = typeSeance;
        this.date = date;
        this.heure = heure;
        this.packageType = packageType;
        this.service = service;
        this.status = "Confirmée";
    }
}