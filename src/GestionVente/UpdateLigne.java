/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import gestionpro.AddHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class UpdateLigne {
    // AddHandler handler= new AddHandler(this);
     VBox root = new VBox();
     Scene scene = new Scene(root, 400, 350);
     Stage window=new Stage();
     Label label= new Label("Modifier une Ligne");
    
      Label qtePro= new Label("Quantite");
     TextField qteProText=new TextField();
     
      HBox hb=new HBox();
     Button add=new Button("Modifier");
     Button cancel=new Button("Annuler");
     
     private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");

        label.getStyleClass().add("labelTitle20");
        label.setMinWidth(580);

      
        qtePro.getStyleClass().add("labelForm");
        
        
        qteProText.getStyleClass().add("textfieldwidth");
       
        add.getStyleClass().add("buttonStyle");
        cancel.getStyleClass().add("buttonStyle");

        root.getStyleClass().add("formContainer");
        root.setSpacing(15);
    }
     private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Modifier une Ligne");
         window.show();
     }
    private void addNodes(){
        root.getChildren().add(label);
        root.getChildren().addAll(qtePro,qteProText);
       
        hb.getChildren().addAll(add,cancel);
        root.getChildren().add(hb);
    }
    private void addStylesToNodes1(){
        root.setSpacing(15);
        hb.setSpacing(15);
        
    }
    
    
    private void addEvents(UpdateVente v,LigneDeCommande li,int i){
       cancel.setOnAction(event->{
           window.close();
       });
       add.setOnAction(event->{
           String qt=qteProText.getText();
           
         if( !qt.trim().isEmpty()){ 
            li.setQte(Integer.valueOf(qt));
            v.LigneList.set(i, li);
            v.calculeTotal();
            v.TotalContent.setText(v.TotalVent+"");
              window.close();
         }else{
             Alert a=new Alert(Alert.AlertType.WARNING);
             a.setTitle("Quantite vide");
             a.setContentText("Vous devez mettre une quantité!");
             a.show();
         }
       });
       window.setOnCloseRequest(event->{
       event.consume();
    });
    }
    public UpdateLigne(UpdateVente v,LigneDeCommande li,int i) {
        
        initialWindow();
        addNodes();
        addStylesToNodes();
        addEvents(v,li,i);
    }
    
}
