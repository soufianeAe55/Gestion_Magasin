/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import GestionClient.Client;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Soufiane
 */
public class VentesWindow {
      BorderPane root = new BorderPane();
      Stage window=new Stage();
      VBox vb=new VBox();
      HBox forRef=new HBox();
      HBox SearchPane=new HBox();
      Scene scene=new Scene(root,900,665);
      TabPane tab=new TabPane();
      Tab AddVente;
      Tab DisplayVente;
      Tab PayTab;
      
      
       private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Gestion des ventes");
         window.show();
     }
    private void addNodes(){
       tab.getTabs().addAll(AddVente,DisplayVente);
       root.setTop(tab);
       
      
    }
    private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        root.getStyleClass().add("rootVentes");
    }
    private void addEvents(){
       
       /*search.setOnAction(event->{
         handler.getSearch();
        
       });*/
    }
    
    public VentesWindow (Client data){
        
         AjouterVente add=new AjouterVente(data) ; 
         VenteList list=new VenteList(data);
         AddVente=new Tab("Ajouter une vente",add.getVb());
         DisplayVente=new Tab("Afficher les ventes",list.getVb());
         addStylesToNodes();
         initialWindow();
         addNodes();
         addEvents();
     }
    
}
