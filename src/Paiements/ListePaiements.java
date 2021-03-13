/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paiements;

import GestionClient.Client;
import GestionClient.ClientHandler;
import GestionClient.ManageClient;
import GestionVente.UpdateVente;
import GestionVente.Vente;
import GestionVente.VenteHandler;
import GestionVente.VentesWindow;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Soufiane
 */
public class ListePaiements {
    ClientHandler handler=new ClientHandler(this);
    Stage window=new Stage();
    VBox vb=new VBox();
     HBox forRef=new HBox();
     HBox SearchPane=new HBox();
    Scene scene=new Scene(vb,900,600);
    Label listePros= new Label("Listes des clients");
    HBox hb =new HBox();
    Label TotalPros= new Label("Total: ");
    Label TotalNumberPros= new Label("0.000");
    TableColumn <Client,Long> colId= new TableColumn<>("id");
    TableColumn <Client,String> colPrenom= new TableColumn<>("Prénom");
    TableColumn <Client,String> colNom= new TableColumn<>("Nom");
    TableColumn <Client,String> colEmail= new TableColumn<>("Email");
    TableColumn <Client,String> colTele= new TableColumn<>("Téléphone");
    TableColumn <Client,String> colAddress= new TableColumn<>("Adresse");
    Button refresh= new Button("Actualiser");
    TextField SearchText=new TextField();
    Button search=new Button("Chercher");
    
    
    TableView <Client> table= new TableView<>();
    public ObservableList <Client> ClientList =FXCollections.observableArrayList();
   
   private void addButtonToTable() {
       
        TableColumn<Client, Void> colBtn = new TableColumn("Gérer");

        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<TableColumn<Client, Void>, TableCell<Client, Void>>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                final TableCell<Client, Void> cell = new TableCell<Client, Void>() {

                    private final Button btn = new Button("Gérer");
                    

                    {
                        btn.getStyleClass().add("gereBtn");
                        btn.setOnAction((ActionEvent event) -> {
                            Client data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            new ManageClient(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        //colBtn.setPrefWidth(100);

        table.getColumns().add(colBtn);

    } 
   private void DoubleClickTable(){
       
         table.setRowFactory( tv -> {
            TableRow<Client> row = new TableRow<>();
            
            row.setOnMouseClicked(event -> {
                
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                Client rowData = row.getItem();
                System.out.println(rowData);
                new PaiementsClient(rowData);
            
            }
         });
         return row ;
        });
   }
   private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        
        vb.getStyleClass().add("proListContainer");
        listePros.getStyleClass().add("labelTitle");
        listePros.setMinWidth(window.getWidth());
        table.getStyleClass().add("table-row-cell");
        TotalNumberPros.getStyleClass().add("TotalPro");
        TotalPros.getStyleClass().add("TotalPro1");
        search.getStyleClass().add("search");
        refresh.getStyleClass().add("refresh");
        refresh.setMinSize(125,40);
        refresh.setMaxSize(125, 40);
       
        forRef.setAlignment(Pos.CENTER_RIGHT);
    }
    private void updateColumns(){
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(70);
        colPrenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        colPrenom.setPrefWidth(100);
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colNom.setPrefWidth(100);
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colEmail.setPrefWidth(200);
        colTele.setCellValueFactory(new PropertyValueFactory("tele"));
        colTele.setPrefWidth(150);
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colAddress.setPrefWidth(150);
        
        
        
        
    }
    private void addColumnsToTable(){
        table.getColumns().addAll(colId,colPrenom,colNom,colEmail,colTele,colAddress);
        table.setItems(ClientList);
    }
   
    private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Liste des clients");
         window.show();
     }
    private void addNodes(){
       
        SearchPane.getChildren().addAll(SearchText,search);
        SearchPane.setSpacing(10);
        SearchPane.setAlignment(Pos.CENTER_LEFT);
        forRef.getChildren().addAll(SearchPane,refresh);
        forRef.setSpacing(250);
        vb.getChildren().addAll(listePros,forRef,table);
    }
    
    private void addEvents(){
       refresh.setOnAction(event->{
         handler.refresh();
        
       });
       search.setOnAction(event->{
         handler.getSearch();
        
       });
    }
    
    public ListePaiements (){
      
        addStylesToNodes();
        initialWindow();
        updateColumns();
        addColumnsToTable();
        addButtonToTable();
        DoubleClickTable();
        handler.getClientsPai();
        addNodes();
        addEvents();
     }
}
