// package modele;

// public class Videographe extends Personne {

//     // Attributs spécifiques
//     private String specialisation;
//     private int experience;

//     // Constructeur minimal
//     public Videographe(String nom, String prenom, String email, String telephone) {
//         super(nom, prenom, email, telephone);
//     }

//     // Constructeur complet
//     public Videographe(int id, String nom, String prenom, String email, String telephone,
//                        String specialisation, int experience) {
//         super(id, nom, prenom, email, telephone);
//         this.specialisation = specialisation;
//         this.experience = experience;
//     }

//     // Getters et Setters
//     public String getSpecialisation() {
//         return specialisation;
//     }

//     public void setSpecialisation(String specialisation) {
//         this.specialisation = specialisation;
//     }

//     public int getExperience() {
//         return experience;
//     }

//     public void setExperience(int experience) {
//         this.experience = experience;
//     }

//     // Méthode pour afficher les informations du vidéographe
//     @Override
//     public void afficherInfo() {
//         super.afficherInfo(); // Cette méthode doit exister dans Personne
//         System.out.println("Spécialisation : " + specialisation);
//         System.out.println("Expérience : " + experience + " ans");
//     }
// }
