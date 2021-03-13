/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class ListProducts {
    ProduitListHandler handler=new ProduitListHandler();
    Stage window=new Stage();
    VBox vb=new VBox();
     HBox forRef=new HBox();
     HBox SearchPane=new HBox();
    Scene scene=new Scene(vb,900,600);
    Label listePros= new Label("Listes des produits");
    HBox hb =new HBox();
    Label TotalPros= new Label("Total: ");
    Label TotalNumberPros= new Label("0.000");
    TableColumn <Produit,Long> colId= new TableColumn<>("id");
    TableColumn <Produit,String> colDes= new TableColumn<>("Designation");
    TableColumn <Produit,Double> colPrix= new TableColumn<>("Prix");
    TableColumn <Produit,Integer> colQte= new TableColumn<>("Quantite");
    TableColumn <Produit,LocalDate> colDate= new TableColumn<>("Date");
    TableColumn <Produit,Double> colTot= new TableColumn<>("Total");
    Button refresh= new Button("Actualiser");
    TextField SearchText=new TextField();
    Button search=new Button("Chercher");
    
    
    TableView <Produit> table= new TableView<>();
    ObservableList <Produit> produitsList =FXCollections.observableArrayList();
    private void calculTotal(){
        double t=0;
        for(Produit p:produitsList){
            t+=p.getTotal();
        }
        TotalNumberPros.setText(t+"");
    }
   private void addButtonToTable() {
       
        TableColumn<Produit, Void> colBtn = new TableColumn("Gérer");

        Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>> cellFactory = new Callback<TableColumn<Produit, Void>, TableCell<Produit, Void>>() {
            @Override
            public TableCell<Produit, Void> call(final TableColumn<Produit, Void> param) {
                final TableCell<Produit, Void> cell = new TableCell<Produit, Void>() {

                    private final Button btn = new Button("Gérer");
                    

                    {
                        btn.getStyleClass().add("gereBtn");
                        btn.setOnAction((ActionEvent event) -> {
                            Produit data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);
                            new ManageProduct(data);
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
   
   private void addStylesToNodes() {
        scene.getStylesheets().add("css/styles.css");
        
        vb.getStyleClass().add("proListContainer");
        listePros.getStyleClass().add("labelTitle");
        listePros.setMinWidth(window.getWidth());
        table.getStyleClass().add("table-row-cell");
        TotalNumberPros.getStyleClass().add("TotalPro");
        TotalPros.getStyleClass().add("TotalPro1");
        refresh.getStyleClass().add("refresh");
        search.getStyleClass().add("search");
        refresh.setMinSize(125,40);
        refresh.setMaxSize(125, 40);
       
        forRef.setAlignment(Pos.CENTER_RIGHT);
    }
    private void updateColumns(){
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(70);
        colDes.setCellValueFactory(new PropertyValueFactory("designation"));
        colDes.setPrefWidth(200);
        colPrix.setCellValueFactory(new PropertyValueFactory("prix"));
        colPrix.setPrefWidth(100);
        colQte.setCellValueFactory(new PropertyValueFactory("qte"));
        colQte.setPrefWidth(70);
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colDate.setPrefWidth(170);
        colTot.setCellValueFactory(new PropertyValueFactory("total"));
        colTot.setPrefWidth(150);
        
        
        
        
    }
    private void addColumnsToTable(){
        table.getColumns().addAll(colId,colDes,colPrix,colQte,colDate,colTot);
        table.setItems(produitsList);
    }
   
    private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Liste des Produits");
         window.show();
     }
    private void addNodes(){
        hb.getChildren().addAll(TotalPros,TotalNumberPros);
        
        SearchPane.getChildren().addAll(SearchText,search);
        SearchPane.setSpacing(10);
        SearchPane.setAlignment(Pos.CENTER_LEFT);
        forRef.getChildren().addAll(SearchPane,refresh);
        forRef.setSpacing(250);
        vb.getChildren().addAll(listePros,forRef,table,hb);
    }
    
    private void addEvents(){
       refresh.setOnAction(event->{
         handler.refresh();
        
       });
       search.setOnAction(event->{
         handler.getSearch();
        
       });
    }
    
    public ListProducts (){
        handler.setLp(this);
        addStylesToNodes();
        initialWindow();
        updateColumns();
        
        addColumnsToTable();
        addButtonToTable();
        handler.getProducts();
        
        calculTotal();
        addNodes();
        addEvents();
     }
    
}
