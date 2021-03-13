/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import gestionpro.Produit;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public interface IPRODAO extends IDAO <Produit> {
    public List <Produit> getAll(String s);
    
}
