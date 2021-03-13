/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import GestionClient.Client;
import GestionClient.ClientHandler;
import GestionClient.ManageClient;
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
public class VenteList {
    VenteHandler handler=new VenteHandler(this);
    Stage window=new Stage();
    VBox vb=new VBox();
     HBox forRef=new HBox();
     HBox SearchPane=new HBox();
    Scene scene=new Scene(vb,900,600);
    Label listePros= new Label("Listes des Ventes");
    HBox hb =new HBox();
    Label TotalPros= new Label("Total: ");
    Label TotalNumberPros= new Label("0.000");
    TableColumn <Vente,Long> colId= new TableColumn<>("id");
    TableColumn <Vente,String> colPrenom= new TableColumn<>("Prénom");
    TableColumn <Vente,String> colNom= new TableColumn<>("Nom");
    TableColumn <Vente,LocalDate> colEmail= new TableColumn<>("Date");
    TableColumn <Vente,Double> colTele= new TableColumn<>("Total");
    Button refresh= new Button("Actualiser");
    DatePicker SearchText=new DatePicker();
    Button search=new Button("Chercher");
    
    
    TableView <Vente> table= new TableView<>();
    ObservableList <Vente> VenteList =FXCollections.observableArrayList();

    public VBox getVb() {
        return vb;
    }
    
    
   
   private void addButtonToTable(Client c) {
       
        TableColumn<Vente, Void> colBtn = new TableColumn("");

        Callback<TableColumn<Vente, Void>, TableCell<Vente, Void>> cellFactory = new Callback<TableColumn<Vente, Void>, TableCell<Vente, Void>>() {
            @Override
            public TableCell<Vente, Void> call(final TableColumn<Vente, Void> param) {
                final TableCell<Vente, Void> cell = new TableCell<Vente, Void>() {

                    private final Button btn = new Button("Supprimer");
                    

                    {
                        btn.getStyleClass().add("gereBtn");
                        btn.setOnAction((ActionEvent event) -> {
                            Vente data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                           handler.deleteVente(data.getId(),c);
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
            TableRow<Vente> row = new TableRow<>();
            
            row.setOnMouseClicked(event -> {
                
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                Vente rowData = row.getItem();
                System.out.println(rowData);
                new UpdateVente(rowData);
            
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
        colId.setPrefWidth(100);
        colPrenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        colPrenom.setPrefWidth(190);
        colNom.setCellValueFactory(new PropertyValueFactory("nom"));
        colNom.setPrefWidth(190);
        colEmail.setCellValueFactory(new PropertyValueFactory("date"));
        colEmail.setPrefWidth(130);
        colTele.setCellValueFactory(new PropertyValueFactory("total"));
        colTele.setPrefWidth(100);
        
        
        
        
        
    }
    private void addColumnsToTable(){
        table.getColumns().addAll(colId,colPrenom,colNom,colEmail,colTele);
        table.setItems(VenteList);
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
    
    private void addEvents(Client c){
       refresh.setOnAction(event->{
         handler.refresh(c);
        
       });
       search.setOnAction(event->{
         handler.getSearch(c);
        
       });
    }
    
    public VenteList (Client c){
      
        addStylesToNodes();
        updateColumns();
        addColumnsToTable();
        addButtonToTable(c);
        DoubleClickTable();
        addNodes();
        handler.getVentes(c);
        addEvents(c);
     }
}
