package com.y_s.gestionhotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    
    // url de connexion de la base de données
    private String dbUrl = "jdbc:postgresql://localhost:5432/hotel";
    private String dbUser = "postgres"; // Correction du nom d'utilisateur
    private String dbPass = "123456";
    
    // Méthode pour se connecter à la base de données
    public Connection dbConnecter() {
        Connection connection = null;
        
        try {
            // Chargement du driver PostgreSQL
            Class.forName("org.postgresql.Driver");
            
            // Établir la connexion
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            System.out.println("Connexion établie avec succès !");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du driver PostgreSQL : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
        }
        
        return connection; // Retourner la connexion établie ou null en cas d'échec
    }
    
    // Méthode pour fermer la connexion à la base de données
    public void fermerConnexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }
}
