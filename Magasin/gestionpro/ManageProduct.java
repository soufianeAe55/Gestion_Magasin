/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

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
public class ManageProduct {
    AddHandler handler= new AddHandler(this);
     VBox root = new VBox();
     Scene scene = new Scene(root, 600, 550);
     Stage window=new Stage();
     Label label= new Label("Gérer un Produit");
     Label desgPro= new Label("Desgination");
     TextField desProText=new TextField();
     Label PrixPro= new Label("Prix");
     TextField PrixProText=new TextField();
      Label qtePro= new Label("Quantite");
     TextField qteProText=new TextField();
      Label DatePro= new Label("Date");
     DatePicker dateProText=new DatePicker();
      HBox hb=new HBox();
     Button add=new Button("Modifier");
     Button delete=new Button("Supprimer");
     
     private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");

        label.getStyleClass().add("labelTitle");
        label.setMinWidth(580);

        desgPro.getStyleClass().add("labelForm");
        qtePro.getStyleClass().add("labelForm");
        PrixPro.getStyleClass().add("labelForm");
        DatePro.getStyleClass().add("labelForm");

        desProText.getStyleClass().add("textfieldwidth");
        qteProText.getStyleClass().add("textfieldwidth");
        PrixProText.getStyleClass().add("textfieldwidth");
        PrixProText.getStyleClass().add("textfieldwidth");

        add.getStyleClass().add("buttonStyle");
        delete.getStyleClass().add("buttonStyle");
         root.getStyleClass().add("formContainer");
      //  root.setPadding(15); 
        root.setSpacing(15);
    }
     private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Gérer un Produit");
         window.show();
     }
    private void addNodes(){
        root.getChildren().add(label);
        root.getChildren().addAll(desgPro,desProText,PrixPro,PrixProText,qtePro,qteProText,DatePro,dateProText);
       
        hb.getChildren().addAll(add,delete);
        root.getChildren().add(hb);
    }
    private void addStylesToNodes1(){
        root.setSpacing(15);
        hb.setSpacing(15);
        
    }
    
    
    private void addEvents(Produit e){
       delete.setOnAction(event->{
           handler.deletePro(e.getId());
         // System.out.println(e);
       });
       add.setOnAction(event->{
           handler.updatePro(e);
           System.out.println(e);
       });
       
    }
    public ManageProduct(Produit e) {
        initialWindow();
        desProText.setText(e.getDesignation());
        qteProText.setText(String.valueOf(e.getQte()));
        PrixProText.setText(String.valueOf(e.getPrix()));;
        dateProText.setValue(e.getDate());
        addNodes();
        addStylesToNodes();
        addEvents(e);
    }
}
