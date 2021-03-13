/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paiements;

import GestionClient.Client;
import GestionVente.LigneDeCommande;
import GestionVente.UpdateLigne;
import GestionVente.Vente;
import GestionVente.VenteHandler;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Soufiane
 */
public class ReglementWindow {
      PaiementHandler handler=new PaiementHandler(this);
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
       VBox BoxCH=new VBox();
      HBox Hcontainer=new HBox();
      Button save=new Button("+");
      Button bill=new Button("-");
      //paimenet fields
      Label NumPai=new Label("N de paiement: ");
      TextField NumPaiText= new TextField();
      Label DatePai=new Label("Date:");
      DatePicker DatePaiText= new DatePicker();
      Label MonPai=new Label("Montant: ");
      TextField MonPaiText= new TextField();
      Label TypePai=new Label("Type:");
      
      ObservableList<String> Types = FXCollections.observableArrayList("PDIST",
           "ESPECE", "CHEQUE");
      
      ListView<String> listView;
       //Chaque fields
       Label NumCompte=new Label("Compte bancaire: ");
      TextField NumCompteText= new TextField();
      Label NumCH=new Label("N de Cheque: ");
      TextField NumCHText= new TextField();
      Label DateEcheance=new Label("Date echeance:");
      DatePicker DateCHText= new DatePicker();
      ObservableList<String> Banques = FXCollections.observableArrayList("BMCE","ATW", "CIH");
      ListView<String> listViewBanque = new ListView<String>(Banques);
      Label NomCH=new Label("Nom:");
      TextField NomCHText= new TextField();
      Button savePai=new Button("Enregistrer"); 
      
      //Detail Vente Composant
      Label detailB=new Label("Detail de Vente");
      HBox clientBox=new HBox();
      Label Nvente=new Label("N vente :  ");
      Label NventeValue=new Label(""); 
      Label ClientName=new Label("Client :  ");
      Label ClientNameText= new Label("");
      HBox dateBox=new HBox();
      Label dateVente=new Label("Date : ");
      Label dateVenteValue=new Label("");
     
 
      //Regl Vente Composant
      Label RegB=new Label("Detail de paiement");
      Label DisplayLigne=new Label("Les Ligne de commande");
      Label LigneTit=new Label("Paiements");
      Label Total=new Label("Total :  ");
      Label TotalContent=new Label("0.00 Dhs");
      HBox TotalBox=new HBox();
   
       //Tbale des Lignes
       
       TableColumn <Paiement,Long> colId= new TableColumn<>("id");
       TableColumn <Paiement,LocalDate> colDate= new TableColumn<>("Date");
       TableColumn <Paiement,Double> colMontant= new TableColumn<>("Montant");
       TableColumn <Paiement,String> colType= new TableColumn<>("Type");
       TableColumn <Paiement,Long> colNumCH= new TableColumn<>("Num Ch");
       TableColumn <Paiement,LocalDate> colDateCH= new TableColumn<>("Date Ch");
       TableColumn <Paiement,String> colBanque= new TableColumn<>("Bangue");
       TableColumn <Paiement,String> colProp= new TableColumn<>("Proprietaire");
       TableView <Paiement> tablePai= new TableView<>();
       ObservableList <Paiement> paiList =FXCollections.observableArrayList();
       
     
     
     private void updateColumnsLigne(){
       
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(50);
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colDate.setPrefWidth(90);
        colMontant.setCellValueFactory(new PropertyValueFactory("montant"));
        colMontant.setPrefWidth(70);
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colType.setPrefWidth(70);
        colNumCH.setCellValueFactory(new PropertyValueFactory("numCH"));
        colNumCH.setPrefWidth(70);
        colDateCH.setCellValueFactory(new PropertyValueFactory("dateCH"));
        colDateCH.setPrefWidth(90);
        colBanque.setCellValueFactory(new PropertyValueFactory("banque"));
        colBanque.setPrefWidth(90);
        colProp.setCellValueFactory(new PropertyValueFactory("nomCH"));
        colProp.setPrefWidth(80);
    }
     
    private void addColumnsToTableLigne(){
        tablePai.getColumns().addAll(colId,colDate,colMontant,colType,colNumCH,colBanque,colProp);
        tablePai.setItems(paiList);
        BoxCH.getChildren().addAll(this.NumCH,this.NumCHText,this.DateEcheance,this.DateCHText,this.listViewBanque,this.NomCH
                    ,this.NomCHText);
    }
   
    private void DoubleClickTable(){
       
         tablePai.setRowFactory( tv -> {
            TableRow<Paiement> row = new TableRow<>();
            
            row.setOnMouseClicked(event -> {
                
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                Paiement rowData = row.getItem();
                  if(Vcontainer2.getChildren().contains(NumCompte)){
                     Vcontainer2.getChildren().removeAll(NumCompte,NumCompteText);
                  }
                 if(rowData.getType().equals("CHEQUE")){
                      Vcontainer2.getChildren().remove(BoxCH);
                  
                    NumPaiText.setText(rowData.getId()+"");
                    DatePaiText.setValue(rowData.getDate());
                    MonPaiText.setText(rowData.getMontant()+"");
                    
                    if(rowData.getNumCH() != 0){
                        
                        NumCHText.setText(rowData.getNumCH()+"");
                        DateCHText.setValue(rowData.getDateCH());
                        NomCHText.setText(rowData.getNomCH());
                        
                    }else{
                         NumCHText.setText("");
                        DateCHText.setValue(null);
                        NomCHText.setText("");
                    }
                   if(!Vcontainer2.getChildren().contains(BoxCH)){
                   
                    
                    Vcontainer2.getChildren().remove(this.savePai);
                    BoxCH.setSpacing(4);
                    Vcontainer2.getChildren().addAll(BoxCH,this.savePai);
                   }
             
                 }else{
                    Vcontainer2.getChildren().remove(BoxCH);
                    NumCHText.setText("");
                    DateCHText.setValue(null);
                    NomCHText.setText("");
                    
                    NumPaiText.setText(rowData.getId()+"");
                    DatePaiText.setValue(rowData.getDate());
                    MonPaiText.setText(rowData.getMontant()+"");
                 }
               
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
      venteOP.setSpacing(4);
      vb.getChildren().add(venteOP);
      
      //detail------------------------------
      HBox NventeBox=new HBox();
      NventeBox.getChildren().addAll(Nvente,NventeValue);
      clientBox.getChildren().addAll(ClientName,ClientNameText);
      dateBox.getChildren().addAll(dateVente,dateVenteValue);
       TotalBox.getChildren().addAll(Total,TotalContent);
      V1.getChildren().addAll(NventeBox,clientBox,TotalBox,dateBox);
      V1.setSpacing(15);
  
      Vcontainer1.getChildren().addAll(detailB,V1);
      
      //Reg--------------------
      VBox VmainContainer=new VBox();
      VBox VmainContainer2=new VBox();
      Vcontainer2.getChildren().addAll(RegB,this.NumPai,this.NumPaiText,this.DatePai,this.DatePaiText,this.MonPai,
      this.MonPaiText,TypePai,this.listView,this.savePai);
      Vcontainer2.setSpacing(3);
      Vcontainer1.getChildren().addAll(LigneTit,tablePai);
      Hcontainer.getChildren().addAll(Vcontainer1,Vcontainer2);
      
      Hcontainer.setSpacing(9);
      Vcontainer1.setMinWidth(515);
      Vcontainer2.setMinWidth(365);
      vb.getChildren().addAll(Hcontainer);
      vb.setSpacing(25);
      
    }
    private void addStylesToNodes() {
        vb.getStylesheets().add("css/styles.css");
        listView.setPrefWidth(100);
        listView.setPrefHeight(50);
        listViewBanque.setPrefWidth(100);
        listViewBanque.setPrefHeight(60);
        vb.getStyleClass().add("vbVente");
        detailB.getStyleClass().add("DetailTit");
        DisplayLigne.getStyleClass().add("DetailTit");
       
        detailB.setMinWidth(515);
        RegB.setMinWidth(365);
        DisplayLigne.setMinWidth(430);
        LigneTit.setMinWidth(515);
        RegB.getStyleClass().add("DetailTit");
        LigneTit.getStyleClass().add("DetailTit");
        V1.getStyleClass().add("TOTvBox");
        NventeValue.getStyleClass().add("VenteInfo");
        ClientNameText.getStyleClass().add("VenteInfo");
        TotalContent.getStyleClass().add("VenteInfo");
        dateVenteValue.getStyleClass().add("VenteInfo");
    }
    private void addEvents(Vente e){
       
      
       savePai.setOnAction(event->{
          
           if(listView.getSelectionModel().getSelectedItem()!=null){
 
                if(listView.getSelectionModel().getSelectedItem().equals("PDIST")){
                    handler.addPaiementParCarte(e);
                }else{
                    handler.add(e);
                }
         }else{
                Alert a=new Alert(Alert.AlertType.NONE);
                     a.setAlertType(Alert.AlertType.WARNING);
                     a.setTitle("Champs!");
                     a.setContentText("Vous devez remplir tout les champs");
                     a.show();
           }
          
       });
       save.setOnAction(event->{
           MonPaiText.setText(totalActual(e)+"");
           NumPaiText.setText(handler.getId()+"");
           DatePaiText.setValue(null);
           Vcontainer2.getChildren().removeAll(BoxCH,this.savePai);
           Vcontainer2.getChildren().add(savePai);
            NumCHText.setText("");
            DateCHText.setValue(null);
            NomCHText.setText("");
           
       });
       bill.setOnAction(event->{
           handler.delete(e);
           MonPaiText.setText(totalActual(e)+"");
           NumPaiText.setText(handler.getId()+"");
           DatePaiText.setValue(null);
           Vcontainer2.getChildren().removeAll(BoxCH,this.savePai);
           Vcontainer2.getChildren().add(savePai);
            NumCHText.setText("");
            DateCHText.setValue(null);
            NomCHText.setText("");
       });
       
    }
    private void initialWindow(){
         window.setScene(scene);
         window.setTitle("Paiements");
         window.show();
     }
   public double totalActual(Vente data){
       double t=data.getTotal();
         for(Paiement p :paiList){
               t-=p.getMontant();
           }
         if(t==0){
             savePai.setDisable(true);
             save.setDisable(true);
         }
         return t;
   }
   private void addNumCompte(){
        
     listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
 
            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                   
                   String data = listView.getSelectionModel().getSelectedItem();
                     if(data.equals("PDIST")){
                         Vcontainer2.getChildren().remove(savePai);
                         Vcontainer2.getChildren().addAll(NumCompte,NumCompteText,savePai);
                      }else{
                        
                          Vcontainer2.getChildren().removeAll(NumCompte,NumCompteText);
                      }
                    
                }
            }
        });
        
   
       
   }
    public ReglementWindow (Vente data){
         
        listView = new ListView<String>(Types);
        NumPaiText.setText(handler.getId()+"");
        dateVenteValue.setText(data.getDate()+"");
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
        addNumCompte();
        addNodes();
        handler.getP(data.getId());
        MonPaiText.setText(totalActual(data)+"");
        addEvents(data);
     }
}
