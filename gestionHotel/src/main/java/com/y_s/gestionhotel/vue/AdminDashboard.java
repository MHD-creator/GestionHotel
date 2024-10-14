package com.y_s.gestionhotel.vue;

import com.y_s.gestionhotel.entites.*;
import com.y_s.gestionhotel.services.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdminDashboard extends Application {

    private Connection connection;
    private ChambreService chambreService;
    private ClientService clientService;
    private ReservationService reservationService;

    @Override
    public void start(Stage primaryStage) {
        connectToDatabase();

        // Initialisation des services
        chambreService = new ChambreService(connection);
        clientService = new ClientService(connection);
        reservationService = new ReservationService(connection);

        primaryStage.setTitle("Administration de l'Hôtel");

        Label title = new Label("Panneau d'administration");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // --- Sections ---
        VBox roomSection = createRoomSection();
        VBox reservationSection = createReservationSection();
        VBox clientSection = createClientSection();

        VBox mainLayout = new VBox(20, title, roomSection, reservationSection, clientSection);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #ecf0f1;");

        Scene scene = new Scene(mainLayout, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Gestion des chambres
    private VBox createRoomSection() {
        Label roomsLabel = new Label("Gestion des chambres");
        roomsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2980b9;");

        TableView<Chambre> roomTable = new TableView<>();
        roomTable.getColumns().addAll(
            createTableColumn("Numéro de Chambre", "roomNumber", 100),
            createTableColumn("Type de Chambre", "roomType", 150),
            createTableColumn("Prix/Nuit", "roomPrice", 100)
        );
        roomTable.getItems().setAll(chambreService.fetchChambres());

        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Numéro de Chambre");
        TextField roomTypeField = new TextField();
        roomTypeField.setPromptText("Type de Chambre");
        TextField roomPriceField = new TextField();
        roomPriceField.setPromptText("Prix/Nuit");

        Button addRoomButton = new Button("Ajouter Chambre");
        addRoomButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5 10;");
        addRoomButton.setOnAction(e -> {
            try {
                String roomNumber = roomNumberField.getText();
                String roomType = roomTypeField.getText();
                double roomPrice = Double.parseDouble(roomPriceField.getText());

                chambreService.addChambre(roomNumber, roomType, roomPrice);
                roomTable.getItems().setAll(chambreService.fetchChambres()); // Rafraîchir

                roomNumberField.clear();
                roomTypeField.clear();
                roomPriceField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "Le prix de la chambre doit être un nombre valide.");
            }
        });

        HBox roomForm = new HBox(10, roomNumberField, roomTypeField, roomPriceField, addRoomButton);
        roomForm.setAlignment(Pos.CENTER);

        VBox roomSection = new VBox(10, roomsLabel, roomTable, roomForm);
        roomSection.setPadding(new Insets(10));
        roomSection.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-background-color: #ffffff;");

        return roomSection;
    }

    // Gestion des clients
    private VBox createClientSection() {
        Label clientsLabel = new Label("Gestion des clients");
        clientsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2980b9;");

        TableView<Client> clientTable = new TableView<>();
        clientTable.getColumns().addAll(
            createTableColumn("ID Client", "clientId", 100),
            createTableColumn("Nom du Client", "clientName", 200)
        );
        clientTable.getItems().setAll(clientService.fetchClients());

        TextField clientNameField = new TextField();
        clientNameField.setPromptText("Nom du Client");

        Button addClientButton = new Button("Ajouter Client");
        addClientButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5 10;");
        addClientButton.setOnAction(e -> {
            if (!clientNameField.getText().trim().isEmpty()) {
                clientService.addClient(clientNameField.getText());
                clientTable.getItems().setAll(clientService.fetchClients());
                clientNameField.clear();
            } else {
                showAlert("Erreur", "Veuillez entrer un nom de client valide.");
            }
        });

        HBox clientForm = new HBox(10, clientNameField, addClientButton);
        clientForm.setAlignment(Pos.CENTER);

        VBox clientSection = new VBox(10, clientsLabel, clientTable, clientForm);
        clientSection.setPadding(new Insets(10));
        clientSection.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-background-color: #ffffff;");

        return clientSection;
    }

    // Gestion des réservations
    private VBox createReservationSection() {
        Label reservationLabel = new Label("Gestion des réservations");
        reservationLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2980b9;");

        TableView<Reservation> reservationTable = new TableView<>();
        reservationTable.getColumns().addAll(
            createTableColumn("ID Réservation", "reservationId", 100),
            createTableColumn("Nom du Client", "clientName", 150),
            createTableColumn("Chambre Réservée", "roomReserved", 150),
            createTableColumn("Durée (jours)", "duration", 100)
        );
        reservationTable.getItems().setAll(reservationService.fetchReservations());

        TextField clientNameField = new TextField();
        clientNameField.setPromptText("Nom du client");
        TextField roomReservedField = new TextField();
        roomReservedField.setPromptText("Chambre réservée");
        TextField durationField = new TextField();
        durationField.setPromptText("Durée (en jours)");

        Button addReservationButton = new Button("Ajouter Réservation");
        addReservationButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-padding: 5 10;");
        addReservationButton.setOnAction(e -> {
            try {
                String clientName = clientNameField.getText();
                String roomReserved = roomReservedField.getText();
                int duration = Integer.parseInt(durationField.getText());

                reservationService.addReservation(clientName, roomReserved, duration);
                reservationTable.getItems().setAll(reservationService.fetchReservations());

                clientNameField.clear();
                roomReservedField.clear();
                durationField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Erreur", "La durée doit être un nombre entier valide.");
            }
        });

        HBox reservationForm = new HBox(10, clientNameField, roomReservedField, durationField, addReservationButton);
        reservationForm.setAlignment(Pos.CENTER);

        VBox reservationSection = new VBox(10, reservationLabel, reservationTable, reservationForm);
        reservationSection.setPadding(new Insets(10));
        reservationSection.setStyle("-fx-border-color: #bdc3c7; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-background-color: #ffffff;");

        return reservationSection;
    }

    private void connectToDatabase() {
        try {
            // Chargement explicite du driver JDBC PostgreSQL (optionnel pour les versions récentes)
            Class.forName("org.postgresql.Driver");

            // Connexion à la base de données
            connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/hotel",  // URL de la base de données
                "postgres",  // Nom d'utilisateur
                "123456");   // Mot de passe

            System.out.println("Connexion réussie à la base de données PostgreSQL.");

        } catch (ClassNotFoundException e) {
            showAlert("Erreur de Connexion", "Le driver PostgreSQL n'a pas été trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            showAlert("Erreur de Connexion", "Impossible de se connecter à la base de données.");
            e.printStackTrace();
        }
    }

    // Méthode pour créer les colonnes du tableau
    private <T> TableColumn<T, String> createTableColumn(String title, String property, double minWidth) {
        TableColumn<T, String> column = new TableColumn<>(title);
        column.setMinWidth(minWidth);
        column.setCellValueFactory(new PropertyValueFactory<>(property)); // Liaison avec la propriété
        column.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");
        return column;
    }

    // Méthode pour afficher les alertes
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
