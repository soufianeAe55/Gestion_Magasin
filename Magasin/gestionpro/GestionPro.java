/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

import GestionClient.FormClient;
import GestionClient.ListClient;
import Paiements.ListePaiements;
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
public class GestionPro extends Application {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 1100, 550);
     MenuItem NewProduct=new MenuItem("Noveau");
     MenuItem ListProduct=new MenuItem("Liste");
     MenuItem NewClient=new MenuItem("Noveau");
     MenuItem ListClient=new MenuItem("Liste");
     MenuItem ListVente=new MenuItem("Liste");
     MenuItem ListPaiements=new MenuItem("Liste");
     MenuItem helpItem=new MenuItem("?");
    
     private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css"); 
		root.getStyleClass().add("rootstyle1");
	}
    private void  CreateMenu(){
        
        MenuBar menuBar= new MenuBar();
        Menu produitsMenu= new Menu("Produits");
        Menu ClientsMenu= new Menu("Clients");
        Menu VentesMenu= new Menu("Ventes");
        Menu PaiementsMenu= new Menu("Paiements");
        Menu helpMenu= new Menu("Help");
        
        
        produitsMenu.getItems().addAll(NewProduct,ListProduct);
        ClientsMenu.getItems().addAll(NewClient,ListClient);
        VentesMenu.getItems().add(ListVente);
        PaiementsMenu.getItems().add(ListPaiements);
        helpMenu.getItems().addAll(helpItem);
        
        menuBar.getMenus().addAll(produitsMenu,ClientsMenu,VentesMenu,PaiementsMenu,helpMenu);
        root.setTop(menuBar);
        
    }
     private void  AddEvent(){
            NewProduct.setOnAction(event->{
               new FormProduct();
            });
            ListProduct.setOnAction(event->{
               new ListProducts();
            });
            NewClient.setOnAction(event->{
               new FormClient();
            });
            ListClient.setOnAction(event->{
              new ListClient();
            });
            ListVente.setOnAction(event->{
              new ListClient();
            });
            ListPaiements.setOnAction(event->{
             new ListePaiements();
            });
            
     }
    @Override
    public void start(Stage primaryStage) {
        addStylesToNodes();
        CreateMenu();
        AddEvent();
        primaryStage.setTitle("Gestion d'un magasin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args
    ) {
        launch(args);
    }
    
}
