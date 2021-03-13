/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import GestionClient.Client;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public interface IclientDAO extends IDAO <Client> {
    public List <Client> getAll(String s);
}
