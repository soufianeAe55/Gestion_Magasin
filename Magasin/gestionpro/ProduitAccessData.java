/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class ProduitAccessData {
    String db="db_magasine";
    String user="root";
    String pwd="";
    String url="jdbc:mysql://localhost:3306/"+db;
    Connection connexion=null;
    public ProduitAccessData() throws SQLException{
        
     connexion=DriverManager.getConnection(url, user, pwd);
        System.out.println("success!");
    }
    
    List <Produit> getAll() throws SQLException{
        List <Produit> list= new ArrayList<>();
        String requet="select * from produit";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
              //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
              Date date=res.getDate("date");
              list.add(new Produit(res.getLong("id"),res.getString("designation"),res.getInt("qte"),res.getDouble("prix"),date.toLocalDate()));
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
        
    }
    
    List <Produit> getByKeyWord(String des) throws SQLException{
        List <Produit> list= new ArrayList<>();
        String requet="select * from produit where designation like ? ";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,des);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
              //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
              Date date=res.getDate("date");
              list.add(new Produit(res.getLong("id"),res.getString("designation"),res.getInt("qte"),res.getDouble("prix"),date.toLocalDate()));
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
        
    }
          
}
