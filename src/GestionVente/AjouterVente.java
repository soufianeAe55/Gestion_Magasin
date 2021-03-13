/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import GestionClient.Client;
import gestionpro.Produit;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
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
public class AjouterVente {
    
      VenteHandler handler=new VenteHandler(this);
      private long numVente;
      private double TotalVent;
      Produit temp;
      VBox vb=new VBox();
      HBox venteOP=new HBox();
      VBox Vcontainer1=new VBox();
      VBox Vcontainer2=new VBox();
      VBox V1=new VBox();
      VBox R1=new VBox();
      HBox Hcontainer=new HBox();
      Button save=new Button("Enregistrer");
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
      
      VBox formLigneContainer=new VBox();
      HBox LigneFormContainerH=new HBox();
      Label designationLabel=new Label("Designation : ");
      TextField designationTextF= new TextField();
      HBox desiBox=new HBox();
      Label qteLabel=new Label("Quantite : ");
      TextField qteTextF= new TextField();
      HBox qteBox=new HBox();
      Label prixLabel=new Label("Prix : ");
      TextField prixTextF= new TextField();
      HBox prixBox=new HBox();
      Button add=new Button("+");
      Button soustra=new Button("-");
      //Regl Vente Composant
      Label RegB=new Label("Reglement de Vente");
      Label DisplayLigne=new Label("Les Ligne de commande");
      Label LigneTit=new Label("Ajouter une ligne de commande");
      Label Total=new Label("Total :  ");
      Label TotalContent=new Label("0.00 Dhs");
      HBox TotalBox=new HBox();
      //Table de produits
       TableColumn <Produit,Long> colId= new TableColumn<>("id");
       TableColumn <Produit,String> colDes= new TableColumn<>("Designation");
       TableColumn <Produit,Double> colPrix= new TableColumn<>("Prix");
       TableColumn <Produit,Integer> colQte= new TableColumn<>("Quantite");
       TableColumn <Produit,LocalDate> colDate= new TableColumn<>("Date");
       TableColumn <Produit,Double> colTot= new TableColumn<>("Total");
       TableView <Produit> table= new TableView<>();
       ObservableList <Produit> produitsList =FXCollections.observableArrayList();
       //Tbale des Lignes
       
       TableColumn <LigneDeCommande,Long> colIdLigne= new TableColumn<>("id");
       TableColumn <LigneDeCommande,String> colDesLigne= new TableColumn<>("Designation");
       TableColumn <LigneDeCommande,Double> colPrixLigne= new TableColumn<>("Prix");
       TableColumn <LigneDeCommande,Integer> colQteLigne= new TableColumn<>("Quantite");
       TableColumn <LigneDeCommande,Double> colTotLigne= new TableColumn<>("Total");
       TableView <LigneDeCommande> tableLigne= new TableView<>();
       ObservableList <LigneDeCommande> LigneList =FXCollections.observableArrayList();
       
     
     private double calculeTotal(){
         
         double res=0;
         for(LigneDeCommande l:LigneList){
             res+=l.getTotal();
         }
         this.TotalVent=res;
         return this.TotalVent;
     }  
      
     private void updateColumnsLigne(){
       
        colDesLigne.setCellValueFactory(new PropertyValueFactory("designation"));
        colDesLigne.setPrefWidth(150);
        colPrixLigne.setCellValueFactory(new PropertyValueFactory("prix"));
        colPrixLigne.setPrefWidth(105);
        colQteLigne.setCellValueFactory(new PropertyValueFactory("qte"));
        colQteLigne.setPrefWidth(90);
        colTotLigne.setCellValueFactory(new PropertyValueFactory("total"));
        colTotLigne.setPrefWidth(95);
          
    }
    private void addColumnsToTableLigne(){
        tableLigne.getColumns().addAll(colDesLigne,colPrixLigne,colQteLigne,colTotLigne);
        tableLigne.setItems(LigneList);
    }
     private void updateColumns(){
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(70);
        colDes.setCellValueFactory(new PropertyValueFactory("designation"));
        colDes.setPrefWidth(100);
        colPrix.setCellValueFactory(new PropertyValueFactory("prix"));
        colPrix.setPrefWidth(70);
        colQte.setCellValueFactory(new PropertyValueFactory("qte"));
        colQte.setPrefWidth(70);
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colDate.setPrefWidth(100);
        colTot.setCellValueFactory(new PropertyValueFactory("total"));
        colTot.setPrefWidth(70);
          
    }
    private void addColumnsToTable(){
        table.getColumns().addAll(colId,colDes,colPrix,colQte,colDate,colTot);
        table.setItems(produitsList);
    }
    private void DoubleClickTable(){
       
         table.setRowFactory( tv -> {
            TableRow<Produit> row = new TableRow<>();
            
            row.setOnMouseClicked(event -> {
                
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                Produit rowData = row.getItem();
                System.out.println(rowData);
                this.temp=rowData;
                designationTextF.setText(rowData.getDesignation());
                qteTextF.setText(String.valueOf(rowData.getQte()));
                prixTextF.setText(String.valueOf(rowData.getPrix()));
                
            
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
      desiBox.getChildren().addAll(designationLabel,designationTextF);
      qteBox.getChildren().addAll(qteLabel,qteTextF);
      qteBox.setSpacing(16);
      prixBox.getChildren().addAll(prixLabel,prixTextF);
      prixBox.setSpacing(41);
      formLigneContainer.getChildren().addAll(desiBox,qteBox,prixBox);
      formLigneContainer.setSpacing(8);
      HBox btsLing=new HBox();
      btsLing.getChildren().addAll(add,soustra);
      btsLing.setSpacing(10);
      LigneFormContainerH.getChildren().addAll(formLigneContainer,btsLing);
      LigneFormContainerH.setSpacing(35);
      Vcontainer1.getChildren().addAll(detailB,V1,LigneTit,LigneFormContainerH,table);
      
      //Reg--------------------
      TotalBox.getChildren().addAll(Total,TotalContent);
      Vcontainer2.getChildren().addAll(RegB,TotalBox,DisplayLigne,tableLigne);
      Hcontainer.getChildren().addAll(Vcontainer1,Vcontainer2);
      Hcontainer.setSpacing(5);
      Vcontainer1.setMinWidth(430);
      Vcontainer2.setMinWidth(430);
      vb.getChildren().addAll(Hcontainer);
      vb.setSpacing(25);
      
    }
    private void addStylesToNodes() {
        vb.getStylesheets().add("css/styles.css");
        vb.getStyleClass().add("vbVente");
        detailB.getStyleClass().add("DetailTit");
        DisplayLigne.getStyleClass().add("DetailTit");
        LigneFormContainerH.getStyleClass().add("ligneComm");
        detailB.setMinWidth(430);
        RegB.setMinWidth(430);
        DisplayLigne.setMinWidth(430);
        LigneTit.setMinWidth(430);
        add.getStyleClass().add("addLign");
        soustra.getStyleClass().add("SousLign");
        
        RegB.getStyleClass().add("DetailTit");
        LigneTit.getStyleClass().add("DetailTit");
        TotalBox.getStyleClass().add("TOTvBox2");
        V1.getStyleClass().add("TOTvBox");
        NventeValue.getStyleClass().add("VenteInfo");
        ClientNameText.getStyleClass().add("VenteInfo");
        TotalContent.getStyleClass().add("VenteInfo");
    }
    private void addEvents(Client e){
       
       add.setOnAction(event->{
        String desi=designationTextF.getText();
        String qt=qteTextF.getText();
        String pr=prixTextF.getText();
        
        if(!desi.trim().isEmpty() && !qt.trim().isEmpty() && !pr.trim().isEmpty() ){  
               
             if(Integer.valueOf(qt) <= this.temp.getQte()){
                int q=Integer.valueOf(qteTextF.getText());  
                LigneDeCommande newl=new LigneDeCommande(0,this.temp,q);
                LigneList.add(newl);
                double t=this.calculeTotal();
                TotalContent.setText(t+"Dhs");
             }else{
                  Alert a=new Alert(AlertType.WARNING);
                  a.setTitle("Quantite Incorrect");
                  a.setContentText("Vous devez mettre une quantité inférieure ou égale à celle du produit!");
                  a.show();
             }
         }else{
             Alert a=new Alert(AlertType.NONE);
             a.setAlertType(AlertType.WARNING);
             a.setTitle("Selectionner un Produit");
             a.setContentText("Vous devez selectionner un produit");
             a.show();
         }
       });
       
       soustra.setOnAction(event->{
           
         tableLigne.getItems().removeAll(tableLigne.getSelectionModel().getSelectedItem());
         double t=this.calculeTotal();
         TotalContent.setText(t+"Dhs");
       });
       save.setOnAction(event->{
           
           handler.saveVente(new Vente(this.numVente,DateVentePicker.getValue(),e,this.LigneList));
       });
       
    }
    
    public AjouterVente (Client data){
        ClientNameText.setText(data.getPrenom()+" "+data.getNom());
        System.out.println(data);
        this.numVente=handler.getLastNumV();
        NventeValue.setText(this.numVente+"");
        addStylesToNodes();
        updateColumns();
        addColumnsToTable();
        updateColumnsLigne();
        addColumnsToTableLigne();
        DoubleClickTable();
        handler.getAllProduct();
        addNodes();
        addEvents(data);
     }
    
}
