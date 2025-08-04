package dao;

import modele.Equipement;
import java.util.List;

public interface EquipementDAO {
    void ajouter(Equipement equipement);
    void modifier(Equipement equipement);
    void supprimer(int id);
    Equipement trouverParId(int id);
    List<Equipement> trouverTous();
    List<Equipement> rechercher(String terme);
}