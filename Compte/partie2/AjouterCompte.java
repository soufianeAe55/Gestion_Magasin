/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Soufiane
 */
public class AjouterCompte {
    CompteHandler handler=new CompteHandler(this);
     VBox root = new VBox();
     Scene scene = new Scene(root, 600, 550);
     Stage window=new Stage();
     Label label= new Label("Noveau Compte");
     Label NomCompte= new Label("Nom");
     TextField NomCompteText=new TextField();
     Label PrenomCompte= new Label("Prenom");
     TextField PrenomCompteText=new TextField();
     Label NumCompte= new Label("Compte");
     TextField NumCompteText=new TextField();
     HBox hb=new HBox();
     Button add=new Button("Creer nn compte");
     
    private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Ajouter un compte");
         window.show();
     }
     private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");

        label.getStyleClass().add("labelTitle");
        label.setMinWidth(580);

        NomCompte.getStyleClass().add("labelForm");
        PrenomCompte.getStyleClass().add("labelForm");
        NumCompte.getStyleClass().add("labelForm");
        

        NomCompteText.getStyleClass().add("textfieldwidth");
        NomCompteText.getStyleClass().add("textfieldwidth");
        NumCompteText.getStyleClass().add("textfieldwidth");
  
        add.getStyleClass().add("buttonStyle");
    
        root.getStyleClass().add("formContainer");
        root.setSpacing(15);
    }
    private void addNodes(){
        root.getChildren().add(label);
        root.getChildren().addAll(NomCompte,NomCompteText,PrenomCompte,PrenomCompteText,NumCompte,NumCompteText);
        hb.getChildren().addAll(add);
        root.getChildren().add(hb);
    }
  
   
   private void addEvents(){
       
       add.setOnAction(event->{
           handler.add();
       });
      
    }
   
    public AjouterCompte() {
        initialWindow();
        addNodes();
        addStylesToNodes();
        addEvents();
    }
}
