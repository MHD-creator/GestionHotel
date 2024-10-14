/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.y_s.gestionhotel.vue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class AdminIndex extends Application{
    @Override
    public void start(Stage primaryStage) {
        //configuration du titre de la fenetre
       primaryStage.setTitle("Gestion d'hotel");
       //titre de la section de l'interface
       Label title = new Label("Bienvenu Cheick");
       title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
       title.centerShapeProperty();
       
        
    }
}
