/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie2;

import java.net.Socket;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Soufiane
 */
public class Partie2 extends Application {
     BorderPane root = new BorderPane();
     Scene scene = new Scene(root, 900, 450);
     MenuItem NewCompte=new MenuItem("Noveau");
     MenuItem DisplayCompte=new MenuItem("Afficher un Compte");
    
      private void  CreateMenu(){
        MenuBar menuBar= new MenuBar();
        Menu CompteMenu= new Menu("Comptes");
        
        CompteMenu.getItems().addAll(NewCompte,DisplayCompte);
        menuBar.getMenus().addAll(CompteMenu);
       root.setTop(menuBar);
        
      }
       private void  AddEvent(){
            NewCompte.setOnAction(event->{
                new AjouterCompte();
            });
            DisplayCompte.setOnAction(event->{
              new AfficheDetails();
            });
       }
    @Override
    public void start(Stage primaryStage) {
      
      CreateMenu();
       AddEvent();
      primaryStage.setTitle("Gestion des comptes");
      primaryStage.setScene(scene);
      primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          try{
            CompteHandler server=new CompteHandler();
           server.start();
          }catch( Exception e){
              
          }
          launch(args);
    }
    
}
