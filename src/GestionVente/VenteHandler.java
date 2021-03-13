/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import DAOproducts.ClientDAOImp;
import DAOproducts.IPRODAO;
import DAOproducts.IProduitImp;
import DAOproducts.IVenteDAO;
import DAOproducts.IclientDAO;
import DAOproducts.Ivente;
import GestionClient.Client;
import Paiements.ListePaiements;
import Paiements.PaiementVente;
import gestionpro.Produit;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class VenteHandler {
    AjouterVente Av=null;
    VenteList lv=null;
    UpdateVente uv=null;
    ListePaiements ps=null;
    PaiementVente pv=null;
    
    public VenteHandler (PaiementVente e){
        this.pv=e;

    }
    
    public VenteHandler (AjouterVente e){
        this.Av=e;
    }
    public VenteHandler (VenteList e){
        this.lv=e;
    }
    public VenteHandler (ListePaiements e){
        this.ps=e;

    }
    public VenteHandler (UpdateVente up){
        this.uv=up;
    }
    
    public long getLastNumV(){
        IVenteDAO Iv=new Ivente();
        long id=Iv.getLastId();
        
        return id+1;
    }
    
    public void getAllProduct(){
         IPRODAO pdao=new IProduitImp();
       List <Produit> list= pdao.getAll();
       
       Av.produitsList.addAll(list);
    }
    
    public void saveVente(Vente v){
        IVenteDAO Iv=new Ivente();
        Iv.add(v);
    }
    public void updateVente(long id,LocalDate date,double total,List <LigneDeCommande> liste){
        IVenteDAO Iv=new Ivente();
        
        Iv.update(id,date,total,liste);
    }
    public void getVentes(Client c){
        IVenteDAO Iv=new Ivente();
        List <Vente> list=Iv.getAll(c);
       
        lv.VenteList.addAll(list);

    }
    public void getVentes2(Client c){
        IVenteDAO Iv=new Ivente();
        List <Vente> list=Iv.getAll(c);
       
        pv.VenteList.addAll(list);

    }
    public void refresh(Client c){
      IVenteDAO Iv=new Ivente();
      List <Vente> list=Iv.getAll(c);
       lv.VenteList.clear();
      lv.VenteList.addAll(list); 
     }
    public void getSearch(Client c){
       
       LocalDate date=lv.SearchText.getValue();
       
       IVenteDAO Iv=new Ivente();
       List <Vente> list=Iv.getAll(c,date);
       lv.VenteList.clear();
       lv.VenteList.addAll(list); 
        
    } 
    public void getSearch2(Client c){
       
       LocalDate date=pv.SearchText.getValue();
       
       IVenteDAO Iv=new Ivente();
       List <Vente> list=Iv.getAll(c,date);
       pv.VenteList.clear();
       pv.VenteList.addAll(list); 
        
    } 
    
    public void deleteVente(long id,Client c){
        IVenteDAO Iv=new Ivente();
        Iv.delete(id);
        List <Vente> list=Iv.getAll(c);
        lv.VenteList.clear();
        lv.VenteList.addAll(list);
        
    }
}
