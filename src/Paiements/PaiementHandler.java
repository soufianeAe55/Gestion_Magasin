/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paiements;

import DAOproducts.IpaiementDAO;
import DAOproducts.PaiementDAOImp;
import GestionVente.Vente;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDate;
import javafx.scene.control.Alert;

/**
 *
 * @author Soufiane
 */
public class PaiementHandler {
    ReglementWindow pw=null;
     private Socket socketEnd2=null;
     private static final int port=3333;
     private OutputStream output=null;
     private InputStream input=null;
     
    public PaiementHandler(ReglementWindow p) {
        this.pw=p;
    }
     public void add(Vente v){
         
         IpaiementDAO pdao=new PaiementDAOImp();
        
        if(!pw.NumPaiText.getText().trim().isEmpty() && pw.DatePaiText.getValue() != null && !pw.MonPaiText.getText().trim().isEmpty() ){  
             long numPai= Integer.valueOf(pw.NumPaiText.getText());
            LocalDate datePai= pw.DatePaiText.getValue();
            double monPai= Double.valueOf(pw.MonPaiText.getText());
            String typePai= pw.listView.getSelectionModel().getSelectedItem();
            Paiement p=new Paiement(numPai,datePai,monPai,typePai,v.getId());
            
            if(v.getTotal() >= monPai){
              if(pw.NumCHText.getText().trim().isEmpty() && !pdao.contains(numPai)){
                  //
                    pdao.add(p);
                   System.out.println("add");
                   
              }else if(!pw.NumCHText.getText().trim().isEmpty() && !pw.NomCHText.getText().trim().isEmpty() ){
                    long numCH= Integer.valueOf(pw.NumCHText.getText());
                    LocalDate dateCH=pw.DateCHText.getValue();
                    String banque=pw.listViewBanque.getSelectionModel().getSelectedItem();
                    String nom=pw.NomCHText.getText();
                    p.setNumCH(numCH);
                    p.setDateCH(dateCH);
                    p.setBanque(banque);
                    p.setNomCH(nom);
                    pdao.update(p);
                  System.out.println("update");
               }
               pw.paiList.clear();
               pw.paiList.addAll(pdao.getAll(v.getId()));
            }else{
                 Alert a=new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.WARNING);
                a.setTitle("Montant!");
                a.setContentText("Vous devez mettre un montant égale ou inférieur à celle du vente");
                a.show();
            }
        }else{
             Alert a=new Alert(Alert.AlertType.NONE);
             a.setAlertType(Alert.AlertType.WARNING);
             a.setTitle("Champs!");
             a.setContentText("Vous devez remplir tout les champs");
             a.show();
        }
        
     }
     public int getId(){
         IpaiementDAO pdao=new PaiementDAOImp();
         return pdao.getLaSTid();
     }
     public void getP(long id){
          IpaiementDAO pdao=new PaiementDAOImp();
          pw.paiList.addAll(pdao.getAll(id));
     }
    public void delete(Vente v){
        Paiement p=pw.tablePai.getSelectionModel().getSelectedItem();
        IpaiementDAO pdao=new PaiementDAOImp();
        pdao.delete(p.getId());
        pw.paiList.clear();
        pw.paiList.addAll(pdao.getAll(v.getId()));
        
    } 
    public void addPaiementParCarte(Vente v){
          IpaiementDAO pdao=new PaiementDAOImp();
         
         try{
            socketEnd2=new Socket("127.0.01",port);
            output=socketEnd2.getOutputStream();
            input=socketEnd2.getInputStream();
            ObjectOutputStream out=new ObjectOutputStream(output);
            ObjectInputStream in=new ObjectInputStream(input); 
            out.writeObject(pw.NumCompteText.getText());
            out.writeObject(Double.valueOf(pw.MonPaiText.getText()));
            out.writeObject(pw.DatePaiText.getValue());
            
            String msg=(String)in.readObject();
            Alert a=new Alert(Alert.AlertType.NONE);
            if(msg.equals("FAILED")){
 
                     a.setAlertType(Alert.AlertType.WARNING);
                     a.setTitle("Sold insuffisant!");
                     a.setContentText("Vous ne pouvez pas effectuer ce paiement car votre solde est insuffisant!");
                     a.show();
            }else if(msg.equals("NOT_EXIST!")){
                
                  a.setAlertType(Alert.AlertType.WARNING);
                     a.setTitle("Sold insuffisant!");
                     a.setContentText("Le compte n'exsit pas!");
                     a.show();
               
            } else{
                
                if(!pw.NumCompteText.getText().trim().isEmpty() && !pw.NumPaiText.getText().trim().isEmpty() && pw.DatePaiText.getValue() != null && !pw.MonPaiText.getText().trim().isEmpty()){
                  
                    long numPai= Integer.valueOf(pw.NumPaiText.getText());
                    LocalDate datePai= pw.DatePaiText.getValue();
                    double monPai= Double.valueOf(pw.MonPaiText.getText());
                    String typePai= pw.listView.getSelectionModel().getSelectedItem();
                    Paiement p=new Paiement(numPai,datePai,monPai,typePai,v.getId());
                    pdao.add(p);
                    
                     a.setAlertType(Alert.AlertType.INFORMATION);
                     a.setTitle("Champs!");
                     a.setContentText(msg);
                     a.show();
                }else{
                    
                     a.setAlertType(Alert.AlertType.WARNING);
                     a.setTitle("Champs!");
                     a.setContentText("Vous devez remplir tout les champs");
                     a.show();
                }
                
            }
                
            
         }catch(Exception e){
             System.out.println(e);
         }
     
      
        pw.paiList.clear();
        pw.paiList.addAll(pdao.getAll(v.getId()));
    }
    
}
