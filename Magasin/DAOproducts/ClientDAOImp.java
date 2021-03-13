/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import GestionClient.Client;
import gestionpro.Produit;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class ClientDAOImp extends AbstractDao implements IclientDAO {

    @Override
    public void add(Client obj) {
         String requet="insert into client (prenom,nom,email,tele,address) values (?,?,?,?,?) ";
         PreparedStatement  pst=null;
         ResultSet res;
         Produit p=null;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,obj.getPrenom());
            pst.setString(2,obj.getNom());
            pst.setString(3,obj.getEmail());
            pst.setString(4,obj.getTele());
            pst.setString(4,obj.getTele());
            pst.setString(5,obj.getAddress());
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void update(Client obj) {
        
        String requet="update client set prenom=?, nom=?, email=?, tele=?, address=? where id= ?";
         PreparedStatement  pst=null;
         ResultSet res;
         Produit p=null;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,obj.getPrenom());
            pst.setString(2,obj.getNom());
            pst.setString(3,obj.getEmail());
            pst.setString(4,obj.getTele());
            pst.setString(4,obj.getTele());
            pst.setString(5,obj.getAddress());
             pst.setLong(6,obj.getId());
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }
    

    @Override
    public void delete(long id) {
        String requet="delete from client where id= ?";
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
    public Client getOne(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Client> getAll() {
        List <Client> list= new ArrayList<>();
        String requet="select * from client";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
             //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
             // Date date=res.getDate("date");
          list.add(new Client(res.getLong("id"),res.getString("prenom"),res.getString("nom"),res.getString("email"),res.getString("tele"),res.getString("address")));
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Client> getAll(String s) {
        List <Client> list= new ArrayList<>();
        String requet="select * from client where nom like ? ";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,s+"%");
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
              //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
           
             list.add(new Client(res.getLong("id"),res.getString("prenom"),res.getString("nom"),res.getString("email"),res.getString("tele"),res.getString("address")));
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
    }
    
}
