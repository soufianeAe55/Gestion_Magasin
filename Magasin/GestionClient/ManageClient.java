/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import gestionpro.AddHandler;
import gestionpro.Produit;
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
public class ManageClient {
    
     ClientHandler handler= new ClientHandler(this);
     
     VBox root = new VBox();
     Scene scene = new Scene(root, 550, 600);
     Stage window=new Stage();
     Label label= new Label("Gérer un Client");
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
     Button add=new Button("Modifier");
  
     Button delete=new Button("Supprimer");
     
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
        delete.getStyleClass().add("buttonStyle");
        root.getStyleClass().add("formContainer");
      //root.setPadding(15); 
        root.setSpacing(15);
    }
     private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Gérer un Client");
         window.show();
     }
    private void addNodes(){
        root.getChildren().add(label);
        root.getChildren().addAll(prenomCL,prenomCLText,nomCl,nomClText,emailCl,emailClText,teleCl,teleClText,adresseCl,adresseClText);
       
        hb.getChildren().addAll(add,delete);
        root.getChildren().add(hb);
    }
   
    
    private void addEvents(Client e){
            delete.setOnAction(event->{
           handler.deleteCl(e.getId());
         // System.out.println(e);
       });
       add.setOnAction(event->{
           handler.updateCl(e);
           System.out.println(e);
       });
       
    }
    public ManageClient(Client e) {
        initialWindow();
        prenomCLText.setText(e.getPrenom());
        nomClText.setText(e.getNom());
        emailClText.setText(e.getEmail());;
        teleClText.setText(e.getTele());
        adresseClText.setText(e.getAddress());;
        addNodes();
        addStylesToNodes();
        addEvents(e);
    }
}
