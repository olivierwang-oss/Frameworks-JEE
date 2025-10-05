package com.support.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de configuration pour la connexion à la base de données MySQL
 */
public class DatabaseConfig {

    // Configuration de la connexion (à adapter selon votre environnement)
    private static final String URL  = "jdbc:mysql://localhost:3306/PIERRE?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "olivier";
    private static final String PASSWORD = "Gd2tA-VP";

    // Chargement du driver JDBC
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL JDBC introuvable", e);
        }
    }

    /**
     * Obtenir une connexion à la base de données
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Fermer une connexion de manière sécurisée
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
