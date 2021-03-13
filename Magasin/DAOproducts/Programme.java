/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import gestionpro.Produit;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class Programme {
    public static void main(String[] args) {
        IPRODAO pdao=new IProduitImp();
       List <Produit> list= pdao.getAll();
       for(Produit e:list){
           System.out.println(e);
       }
       
       pdao.add(new Produit(0,"galaxy 2",3,8000,LocalDate.now()));
    }
}
