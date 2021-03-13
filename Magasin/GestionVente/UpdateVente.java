/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import GestionClient.Client;
import gestionpro.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class UpdateVente {
   
      VenteHandler handler=new VenteHandler(this);
      Stage window=new Stage();
      VBox vb=new VBox();
      Scene scene=new Scene(vb,900,600);
      private long numVente;
      public double TotalVent;
      HBox venteOP=new HBox();
      VBox Vcontainer1=new VBox();
      VBox Vcontainer2=new VBox();
      VBox V1=new VBox();
      VBox R1=new VBox();
      HBox Hcontainer=new HBox();
      Button save=new Button("Modifier");
      Button bill=new Button("Reglement");
   
      //Detail Vente Composant
      Label detailB=new Label("Detail de Vente");
      HBox clientBox=new HBox();
      Label Nvente=new Label("N vente :  ");
      Label NventeValue=new Label(""); 
      Label ClientName=new Label("Client :  ");
      Label ClientNameText= new Label("");
      HBox dateBox=new HBox();
      Label dateVente=new Label("Date : ");
      DatePicker DateVentePicker= new DatePicker();
 
      //Regl Vente Composant
      Label RegB=new Label("Reglement de Vente");
      Label DisplayLigne=new Label("Les Ligne de commande");
      Label LigneTit=new Label("Les Ligne de commande");
      Label Total=new Label("Total :  ");
      Label TotalContent=new Label("0.00 Dhs");
      HBox TotalBox=new HBox();
   
       //Tbale des Lignes
       
       TableColumn <LigneDeCommande,Long> colIdLigne= new TableColumn<>("id");
       TableColumn <LigneDeCommande,String> colDesLigne= new TableColumn<>("Designation");
       TableColumn <LigneDeCommande,Double> colPrixLigne= new TableColumn<>("Prix");
       TableColumn <LigneDeCommande,Integer> colQteLigne= new TableColumn<>("Quantite");
       TableColumn <LigneDeCommande,Double> colTotLigne= new TableColumn<>("Total");
       TableView <LigneDeCommande> tableLigne= new TableView<>();
       ObservableList <LigneDeCommande> LigneList =FXCollections.observableArrayList();
       
     
     
     private void updateColumnsLigne(){
       
        colDesLigne.setCellValueFactory(new PropertyValueFactory("designation"));
        colDesLigne.setPrefWidth(300);
        colPrixLigne.setCellValueFactory(new PropertyValueFactory("prix"));
        colPrixLigne.setPrefWidth(210);
        colQteLigne.setCellValueFactory(new PropertyValueFactory("qte"));
        colQteLigne.setPrefWidth(190);
        colTotLigne.setCellValueFactory(new PropertyValueFactory("total"));
        colTotLigne.setPrefWidth(200);
          
    }
    private void addColumnsToTableLigne(){
        tableLigne.getColumns().addAll(colDesLigne,colPrixLigne,colQteLigne,colTotLigne);
        tableLigne.setItems(LigneList);
    }
   
    private void DoubleClickTable(){
       
         tableLigne.setRowFactory( tv -> {
            TableRow<LigneDeCommande> row = new TableRow<>();
            
            row.setOnMouseClicked(event -> {
                
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                LigneDeCommande rowData = row.getItem();
                System.out.println(rowData); 
               
                
                new UpdateLigne(this,rowData,this.LigneList.indexOf(rowData));
            }
         });
         return row ;
        });
   }
    public VBox getVb(){
          return vb;
      }
      
    
    private void addNodes(){
      venteOP.getChildren().addAll(save,bill);
      venteOP.setSpacing(20);
      vb.getChildren().add(venteOP);
      
      //detail------------------------------
      HBox NventeBox=new HBox();
      NventeBox.getChildren().addAll(Nvente,NventeValue);
      clientBox.getChildren().addAll(ClientName,ClientNameText);
      dateBox.getChildren().addAll(dateVente,DateVentePicker);
      V1.getChildren().addAll(NventeBox,clientBox,dateBox);
      V1.setSpacing(15);
  
      Vcontainer1.getChildren().addAll(detailB,V1);
      
      //Reg--------------------
      VBox VmainContainer=new VBox();
      VBox VmainContainer2=new VBox();
      TotalBox.getChildren().addAll(Total,TotalContent);
      Vcontainer2.getChildren().addAll(RegB,TotalBox);
      Hcontainer.getChildren().addAll(Vcontainer1,Vcontainer2);
      VmainContainer2.getChildren().addAll(LigneTit,tableLigne);
      VmainContainer2.setSpacing(15);
      VmainContainer.getChildren().addAll(Hcontainer,VmainContainer2);
      Hcontainer.setSpacing(5);
      Vcontainer1.setMinWidth(430);
      Vcontainer2.setMinWidth(430);
      vb.getChildren().addAll(VmainContainer);
      vb.setSpacing(25);
      
    }
    private void addStylesToNodes() {
        vb.getStylesheets().add("css/styles.css");
        vb.getStyleClass().add("vbVente");
        detailB.getStyleClass().add("DetailTit");
        DisplayLigne.getStyleClass().add("DetailTit");
       
        detailB.setMinWidth(430);
        RegB.setMinWidth(430);
        DisplayLigne.setMinWidth(430);
        LigneTit.setMinWidth(880);
        RegB.getStyleClass().add("DetailTit");
        LigneTit.getStyleClass().add("DetailTit");
        TotalBox.getStyleClass().add("TOTvBox2");
        V1.getStyleClass().add("TOTvBox");
        NventeValue.getStyleClass().add("VenteInfo");
        ClientNameText.getStyleClass().add("VenteInfo");
        TotalContent.getStyleClass().add("VenteInfo");
    }
    private void addEvents(Vente e){
       
      
       save.setOnAction(event->{
           
           handler.updateVente(this.numVente,DateVentePicker.getValue(),this.TotalVent,this.LigneList);
       });
       
    }
    private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Liste des clients");
         window.show();
     }
    public void calculeTotal(){
         
         double res=0;
         for(LigneDeCommande l:LigneList){
             res+=l.getTotal();
         }
         this.TotalVent=res;
         //return this.TotalVent;
     }  
    public UpdateVente (Vente data){
        this.LigneList.addAll(data.getLigne());
        DateVentePicker.setValue(data.getDate());
        ClientNameText.setText(data.getPrenom()+" "+data.getNom());
        this.numVente=data.getId();
        NventeValue.setText(this.numVente+"");
        this.TotalVent=data.getTotal();
        TotalContent.setText(this.TotalVent+"Dhs");
        addStylesToNodes();
        initialWindow();
        updateColumnsLigne();
        addColumnsToTableLigne();
        DoubleClickTable();
         calculeTotal();
        //handler.getAllProduct();
        addNodes();
        addEvents(data);
     }
    
}
