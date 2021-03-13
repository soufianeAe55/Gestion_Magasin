/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

import DAOproducts.IPRODAO;
import DAOproducts.IProduitImp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class AddHandler {
     FormProduct fp=null;
     ManageProduct mp=null;

    public AddHandler (FormProduct fp) {
        this.fp = fp;
    }
    public AddHandler (ManageProduct mp) {
        this.mp = mp;
    }
    
    public void addPro(){
        String designation=fp.desProText.getText();
        int qte=Integer.valueOf(fp.qteProText.getText());
        double prix=Integer.valueOf(fp.PrixProText.getText());
        LocalDate date=fp.dateProText.getValue();
        Produit p=new Produit(0,designation,qte,prix,date);
        IPRODAO pdao=new IProduitImp();
        pdao.add(p);
    }
     public void updatePro(Produit e){
        String designation=mp.desProText.getText();
        int qte=Integer.valueOf(mp.qteProText.getText());
        double prix=Double.valueOf(mp.PrixProText.getText());
        LocalDate date=mp.dateProText.getValue();
        Produit p=new Produit(e.getId(),designation,qte,prix,date);
        IPRODAO pdao=new IProduitImp();
        pdao.update(p);
    }
    public void deletePro(long id){
        
        IPRODAO pdao=new IProduitImp();
        pdao.delete(id);
    } 
    
}
