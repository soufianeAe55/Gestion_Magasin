/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class Program {
    public static void main(String[] args) throws SQLException {
        ProduitAccessData pData= new ProduitAccessData();
        List <Produit> list=pData.getAll();
    }
}
