/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie2;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Soufiane
 */
public class AfficheDetails {
     CompteHandler handler=new CompteHandler(this);
     VBox root = new VBox();
     Scene scene = new Scene(root, 600, 550);
     Stage window=new Stage();
     HBox hb=new HBox();
     Label numCompte=new Label("Compte:  ");
     
     Label NomCompte=new Label("Nom:  ");
     Label NomCompteValue=new Label("");
     HBox nomBox=new HBox();
     Label soldeCompte=new Label("Solde:  ");
     Label soldeCompteValue=new Label("");
     HBox SoldeBox=new HBox();
     TextField numCompteText=new TextField();
     Button add=new Button("Afficher les details du compte");
     TableColumn <Operation,LocalDate> colDate= new TableColumn<>("Date");
     TableColumn <Operation,String> colOp= new TableColumn<>("Operation");
     TableColumn <Operation,Double> colMontant= new TableColumn<>("Montant");
   
    TableView <Operation> table= new TableView<>();
    ObservableList <Operation> OperationList =FXCollections.observableArrayList();
    
    private void updateColumns(){

        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colDate.setPrefWidth(170);
        colOp.setCellValueFactory(new PropertyValueFactory("type"));
        colOp.setPrefWidth(170);
        colMontant.setCellValueFactory(new PropertyValueFactory("montant"));
        colMontant.setPrefWidth(150);

        
    }
    private void addColumnsToTable(){
        table.getColumns().addAll(colDate,colOp,colMontant);
        table.setItems(OperationList);
    } 
    private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Afficher les details du compte");
         window.show();
     }
     private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");

      
  
        add.getStyleClass().add("buttonStyle");
    
        root.getStyleClass().add("formContainer");
        root.setSpacing(15);
    }
    private void addNodes(){
       
        hb.getChildren().addAll(numCompte,numCompteText,add);
        hb.setSpacing(10);
        nomBox.getChildren().addAll(NomCompte,NomCompteValue);
        SoldeBox.getChildren().addAll(soldeCompte,soldeCompteValue);
        root.getChildren().addAll(hb,nomBox,SoldeBox,table);
    }
  
   
   private void addEvents(){
       
       add.setOnAction(event->{
           handler.getCompte();
       });
      
    }
   
    public AfficheDetails() {
        initialWindow();
        addNodes();
        addColumnsToTable();
        updateColumns();
        addStylesToNodes();
        addEvents();
    }
}
