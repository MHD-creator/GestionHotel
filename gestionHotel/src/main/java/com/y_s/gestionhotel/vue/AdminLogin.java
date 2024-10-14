package com.y_s.gestionhotel.vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import com.y_s.gestionhotel.vue.AdminDashboard;

public class AdminLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Page de connexion
        Label usernameLabel = new Label("Nom d'utilisateur :");
        TextField usernameField = new TextField();
        
        Label passwordLabel = new Label("Mot de passe :");
        PasswordField passwordField = new PasswordField();
        
        Button loginButton = new Button("Se connecter");
        Label messageLabel = new Label();  // Pour les messages d'erreur ou de succès

        // Action du bouton de connexion
        loginButton.setOnAction(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                messageLabel.setText("Veuillez remplir tous les champs !");
            } else {
                // Fermer la fenêtre actuelle (Login)
                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();

                // Ouvrir le tableau de bord Admin
                AdminDashboard adminDashboard = new AdminDashboard();
                Stage dashboardStage = new Stage();
                try {
                    adminDashboard.start(dashboardStage);  // Appel de la méthode start() de AdminDashboard
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Disposition de la page de connexion
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(messageLabel, 1, 3);

        // Créer la scène de connexion
        Scene loginScene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Connexion Admin");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
