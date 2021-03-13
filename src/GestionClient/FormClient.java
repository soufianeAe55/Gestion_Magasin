/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import gestionpro.AddHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Soufiane
 */
public class FormClient {
     ClientHandler handler= new ClientHandler(this);
     VBox root = new VBox();
     Scene scene = new Scene(root, 550, 600);
     Stage window=new Stage();
     Label label= new Label("Noveau Client");
     Label prenomCL= new Label("Prénom");
     TextField prenomCLText=new TextField();
     Label nomCl= new Label("Nom");
     TextField nomClText=new TextField();
     Label emailCl= new Label("Email");
     TextField emailClText=new TextField();
     Label teleCl= new Label("Téléphone");
     TextField teleClText=new TextField();
     Label adresseCl= new Label("Adresse");
     TextField adresseClText=new TextField();
     
     HBox hb=new HBox();
     Button add=new Button("Ajouter");
     Button cancel=new Button("Annuler");
     
     private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");

        label.getStyleClass().add("labelTitle");
        label.setMinWidth(580);

        prenomCL.getStyleClass().add("labelForm");
        nomCl.getStyleClass().add("labelForm");
        emailCl.getStyleClass().add("labelForm");
        teleCl.getStyleClass().add("labelForm");
        adresseCl.getStyleClass().add("labelForm");

        prenomCLText.getStyleClass().add("textfieldwidth");
        nomClText.getStyleClass().add("textfieldwidth");
        emailClText.getStyleClass().add("textfieldwidth");
        teleClText.getStyleClass().add("textfieldwidth");
        adresseClText.getStyleClass().add("textfieldwidth");

        add.getStyleClass().add("buttonStyle");
        cancel.getStyleClass().add("buttonStyle");

        root.getStyleClass().add("formContainer");
        root.setSpacing(15);
    }
     private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Ajouter un Client");
         window.show();
     }
    private void addNodes(){
        root.getChildren().add(label);
        root.getChildren().addAll(prenomCL,prenomCLText,nomCl,nomClText,emailCl,emailClText,teleCl,teleClText,adresseCl,adresseClText);
       
        hb.getChildren().addAll(add,cancel);
        root.getChildren().add(hb);
    }
    private void addStylesToNodes1(){
        root.setSpacing(15);
        hb.setSpacing(15);
        
    }
    
    
    private void addEvents(){
       cancel.setOnAction(event->{
           window.close();
       });
       add.setOnAction(event->{
           handler.addCl();
       });
       window.setOnCloseRequest(event->{
       event.consume();
    });
    }
    public FormClient() {
        initialWindow();
        addNodes();
        addStylesToNodes();
        addEvents();
    }
}
