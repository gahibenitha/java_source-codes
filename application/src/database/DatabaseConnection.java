package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/mlr4?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // 🔁 ton mot de passe root MySQL

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion à la base réussie !");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ Pilote JDBC introuvable : " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("❌ Échec de la connexion MySQL : " + e.getMessage());
            }
        }
        if (connection == null) {
            System.err.println("⚠️ Connexion null : vérifie les identifiants.");
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Déconnexion réussie !");
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur de déconnexion : " + e.getMessage());
        }
    }
}
