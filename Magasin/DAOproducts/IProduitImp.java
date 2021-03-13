/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import gestionpro.Produit;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class IProduitImp extends AbstractDao implements IPRODAO {

    @Override
    public List<Produit> getAll(String s) {
        List <Produit> list= new ArrayList<>();
        String requet="select * from produit where designation like ? ";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,s+"%");
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

    @Override
    public void add(Produit obj) {
        
        String requet="insert into produit (designation,qte,prix,date) values (?,?,?,?) ";
         PreparedStatement  pst=null;
         ResultSet res;
         Produit p=null;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,obj.getDesignation());
            pst.setInt(2,obj.getQte());
            pst.setDouble(3,obj.getPrix());
            Date date=Date.valueOf(obj.getDate());
            pst.setDate(4,date);
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        
    }
    
    public void update(Produit obj) {
      
        
        String requet="update produit set designation=?, qte=?, prix=?, date=? where id= ?";
         PreparedStatement  pst=null;
         ResultSet res;
         Produit p=null;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,obj.getDesignation());
            pst.setInt(2,obj.getQte());
            pst.setDouble(3,obj.getPrix());
            Date date=Date.valueOf(obj.getDate());
            pst.setDate(4,date);
            pst.setLong(5,obj.getId());
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        
    }

    @Override
    public void delete(long id) {
        
         String requet="delete from produit where id= ?";
         PreparedStatement  pst=null;
         ResultSet res;
         Produit p=null;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1,id);
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public Produit getOne(long id) {
        List <Produit> list= new ArrayList<>();
        String requet="select * from produit where id=? ";
         PreparedStatement  pst=null;
         ResultSet res;
         Produit p=null;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1,id);
            res=pst.executeQuery();
            System.out.println("success Req!");
            if(res.next()){
              //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
              Date date=res.getDate("date");
               p =new Produit(res.getLong("id"),res.getString("designation"),res.getInt("qte"),res.getDouble("prix"),date.toLocalDate());
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return p;
    }

    @Override
    public List<Produit> getAll() {
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
    
}
