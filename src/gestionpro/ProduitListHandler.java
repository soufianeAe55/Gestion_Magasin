/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

import DAOproducts.IPRODAO;
import DAOproducts.IProduitImp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class ProduitListHandler {
    ListProducts lp=null;

    public void setLp(ListProducts lp) {
        this.lp = lp;
    }
     public void refresh(){
      IPRODAO pdao=new IProduitImp();
       List <Produit> list= pdao.getAll();
        lp.produitsList.clear();
        lp.produitsList.addAll(list);
       
     }
public void getProducts(){
     
       IPRODAO pdao=new IProduitImp();
       List <Produit> list= pdao.getAll();
       
       lp.produitsList.addAll(list);
        
    }
public void getSearch(){
       
       String mot=lp.SearchText.getText();
       IPRODAO pdao=new IProduitImp();
       List <Produit> list= pdao.getAll(mot);
       lp.produitsList.clear();
       lp.produitsList.addAll(list);
        
    } 
    
}
