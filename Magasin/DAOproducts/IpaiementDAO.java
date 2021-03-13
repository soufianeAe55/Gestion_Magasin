/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import Paiements.Paiement;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public interface IpaiementDAO extends IDAO <Paiement> {
    int getLaSTid();
    List<Paiement> getAll(long id);
    boolean contains(long id);
        
    
}
