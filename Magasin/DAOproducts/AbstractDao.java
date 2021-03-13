/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Soufiane
 */
public class AbstractDao {
    
    protected Connection connexion=SingleConnection.getConnexion();
}
