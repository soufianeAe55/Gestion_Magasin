/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import GestionClient.Client;
import GestionVente.LigneDeCommande;
import GestionVente.Vente;
import gestionpro.Produit;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public interface IVenteDAO extends IDAO <Vente> {
    public List <Vente> getAll(Client c,LocalDate s);
    public List <Vente> getAll(Client c);
    public List <LigneDeCommande> getAll(long id);
    public long getLastId();
    public void update(long id,LocalDate date,double total,List <LigneDeCommande> liste);
}
