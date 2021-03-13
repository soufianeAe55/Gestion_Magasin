/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOproducts;

import Paiements.Paiement;
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
public class PaiementDAOImp extends AbstractDao implements IpaiementDAO {

    @Override
    public void add(Paiement obj) {
         String requet="insert into paiement (idPai,idVente,date,montant,type) values (?,?,?,?,?) ";
         PreparedStatement  pst=null;
         ResultSet res;
       
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1,obj.getId());
            pst.setLong(2,obj.getIdVente());
            Date date=Date.valueOf(obj.getDate());
            pst.setDate(3,date);
            pst.setDouble(4,obj.getMontant());
             pst.setString(5,obj.getType());
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void update(Paiement obj) {
         String requet="update paiement set date= ?, montant = ?, numCH=?, dateCH=?, banque=?, nomCH=? where idPai= ? ";
         PreparedStatement  pst=null;
         ResultSet res;
       
        try{
            pst= connexion.prepareStatement(requet);
           
            Date date=Date.valueOf(obj.getDate());
            pst.setDate(1,date);
            pst.setDouble(2,obj.getMontant());
            pst.setLong(3,obj.getNumCH());
            Date date2=Date.valueOf(obj.getDateCH());
            pst.setDate(4,date2);
            pst.setString(5,obj.getBanque());
            pst.setString(6,obj.getNomCH());
            pst.setLong(7,obj.getId());
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void delete(long id) {
         String requet="delete from paiement where idPai= ?";
         PreparedStatement  pst=null;
         ResultSet res;
         
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
    public Paiement getOne(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paiement> getAll(long id) {
         List <Paiement> list= new ArrayList<>();
        String requet="select * from paiement where idVente= ?";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1, id);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
             //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
              Date date=res.getDate("date");
              Paiement test=new Paiement(res.getLong("idPai"),date.toLocalDate(),res.getDouble("montant"),res.getString("type"),res.getLong("idVente"));
             if(res.getLong("numCH") == 0){
                 
                test.setNumCH(res.getLong("numCH"));
                Date date2=res.getDate("dateCH");
               // test.setDateCH(date2.toLocalDate());
                test.setBanque("----");
                test.setNomCH("----");

             }else{
                test.setNumCH(res.getLong("numCH"));
                Date date2=res.getDate("dateCH");
                test.setDateCH(date2.toLocalDate());
                test.setBanque(res.getString("banque"));
                test.setNomCH(res.getString("nomCH")); 
             }
              list.add(test);
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public int getLaSTid() {
         String requet="select * from paiement";
         PreparedStatement  pst=null;
         ResultSet res;
         int id=0;
        try{
            pst= connexion.prepareStatement(requet);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
                id=res.getInt("idPai");
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
       return id+1;
    }

    @Override
    public List<Paiement> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(long id) {
         String requet="select * from paiement";
         PreparedStatement  pst=null;
         ResultSet res;
         
        try{
            pst= connexion.prepareStatement(requet);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
                if(res.getLong("idPai")== id ) return true;
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return false;
    }
    
}
