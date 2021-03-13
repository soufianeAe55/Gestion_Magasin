/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import DAOproducts.ClientDAOImp;
import DAOproducts.IPRODAO;
import DAOproducts.IProduitImp;
import DAOproducts.IclientDAO;
import Paiements.ListePaiements;
import gestionpro.Produit;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class ClientHandler {
    ListClient lc=null;
    FormClient fc=null;
    ManageClient mc=null;
    ListePaiements ps=null;
    
    public ClientHandler(ListClient l) {
        this.lc=l;
    }
    public ClientHandler(FormClient l) {
        this.fc=l;
    }
    public ClientHandler(ManageClient l) {
        this.mc=l;
    }
    public ClientHandler (ListePaiements e){
        this.ps=e;
       
        
    }
    public void getClients(){
        
        IclientDAO cdao= new ClientDAOImp();
        List list=cdao.getAll();
        lc.ClientList.addAll(list);
        
    }
    public void getClients2(){
        
        IclientDAO cdao= new ClientDAOImp();
        List list=cdao.getAll();
        ps.ClientList.addAll(list);
    }
    public void getClientsPai(){
        
        IclientDAO cdao= new ClientDAOImp();
        List list=cdao.getAll();
        ps.ClientList.addAll(list);
    }
    
    public void refresh(){
      IclientDAO cdao=new ClientDAOImp();
       List <Client> list= cdao.getAll();
        lc.ClientList.clear();
        lc.ClientList.addAll(list);
       
     }
     public void addCl(){
        String prenom=fc.prenomCLText.getText();
        String nom=fc.nomClText.getText();
        String email=fc.emailClText.getText();
        String tele=fc.teleClText.getText();
        String address=fc.adresseClText.getText();
        Client p=new Client(0,prenom,nom,email,tele,address);
        IclientDAO cdao=new ClientDAOImp();
        cdao.add(p);
        
    }
     
      public void updateCl(Client e){
        String prenom=mc.prenomCLText.getText();
        String nom=mc.nomClText.getText();
        String email=mc.emailClText.getText();
        String tele=mc.teleClText.getText();
        String address=mc.adresseClText.getText();
        Client p=new Client(e.getId(),prenom,nom,email,tele,address);
        IclientDAO cdao=new ClientDAOImp();
        cdao.update(p);
    }
    public void deleteCl(long id){
        
        IclientDAO cdao=new ClientDAOImp();
        cdao.delete(id);
    } 
    public void getSearch(){
       
       String mot=lc.SearchText.getText();
       IclientDAO cdao=new ClientDAOImp();
       List <Client> list= cdao.getAll(mot);
       lc.ClientList.clear();
       lc.ClientList.addAll(list);
        
    } 
    
}
