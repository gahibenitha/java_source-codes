package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Remplace les paramètres ci-dessous par ceux de ta base de données MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/mlr4"; // <-- MODIFIE ICI
    private static final String USER = "benifranck"; // Nom d'utilisateur MySQL
    private static final String PASSWORD = "beni1"; // Mot de passe MySQL

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Charger le driver JDBC (optionnel avec JDBC 4.0+ mais utile pour être explicite)
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Établir la connexion
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à la base de données réussie !");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC non trouvé : " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
            }
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Déconnexion réussie !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la déconnexion : " + e.getMessage());
        }
    }
}
