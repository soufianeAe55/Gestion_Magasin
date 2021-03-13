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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class Ivente extends AbstractDao implements IVenteDAO {

    @Override
    public List<Vente> getAll(Client c,LocalDate s) {
        List <Vente> list= new ArrayList<>();
        List <LigneDeCommande> listLigne= new ArrayList<>();
        String requet="select * from vente where idCl= ? and date= ?";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1, c.getId());
            Date date2= Date.valueOf(s);
            pst.setDate(2, date2);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
             //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
                LocalDate date=res.getDate("date").toLocalDate(); 
                list.add(new Vente(res.getLong("id"),date,c,getAll(res.getLong("id"))));
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public void add(Vente obj) {
         String requet="insert into vente (idCl,prenom,nom,date,total) values (?,?,?,?,?) ";
         String requet2="insert into lignes (idVente,designation,qte,prix,total,idProduit) values (?,?,?,?,?,?) ";
         PreparedStatement  pst=null;
         ResultSet res;
        
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1, obj.getC().getId());
            pst.setString(2,obj.getC().getPrenom());
            pst.setString(3,obj.getC().getNom());
            Date date=Date.valueOf(obj.getDate());
            pst.setDate(4,date);
            pst.setDouble(5,obj.getTotal());
            
            pst.executeUpdate();
            for(LigneDeCommande l:obj.getLigne()){
                
                pst= connexion.prepareStatement(requet2);
                    pst.setLong(1,obj.getId());
                    pst.setString(2,l.getDesignation());
                    pst.setInt(3,l.getQte());
                    pst.setDouble(4,l.getPrix());
                    pst.setDouble(5,l.getTotal());
                    pst.setLong(6, l.getP().getId());
                    pst.executeUpdate();
            }
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void update(Vente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(long id) {
         String requet="delete from vente where id= ?";
          String requet2="delete from lignes where idVente= ?";
         PreparedStatement  pst=null;
         ResultSet res;
         Produit p=null;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1,id);
            pst.executeUpdate();
            pst= connexion.prepareStatement(requet2);
            pst.setLong(1,id);
            pst.executeUpdate();
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public Vente getOne(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vente> getAll(Client c) {
        
        List <Vente> list= new ArrayList<>();
        List <LigneDeCommande> listLigne= new ArrayList<>();
        String requet="select * from vente where idCl= ?";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1, c.getId());
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
             //  System.out.println(res.getLong("id")+res.getString("designation")+res.getLong("qte")+res.getDouble("prix")+res.getDate("date"));
                LocalDate date=res.getDate("date").toLocalDate(); 
                list.add(new Vente(res.getLong("id"),date,c,getAll(res.getLong("id"))));
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public long getLastId() {
       
        String requet="select * from vente";
         PreparedStatement  pst=null;
         ResultSet res;
         long id=0;
        try{
            pst= connexion.prepareStatement(requet);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
                id=res.getLong("id");
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
       return id;
    }

    @Override
    public List<LigneDeCommande> getAll(long id) {
       
        List <LigneDeCommande> listLigne= new ArrayList<>();
        String requet="select * from lignes where idVente= ?";
        String requet2="select * from produit where id= ?";
         PreparedStatement  pst=null;
         ResultSet res;
         PreparedStatement  pst2=null;
         ResultSet res2;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1, id);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
                
                pst2= connexion.prepareStatement(requet2);
                pst2.setLong(1, res.getLong("idProduit"));
                res2=pst2.executeQuery();
                if(res2.next()){
                    
                    //System.out.println("is test"+res2.getLong("id")+res2.getString("designation")+res2.getLong("qte")+res2.getDouble("prix")+res2.getDate("date"));
                    Produit p=new Produit(res2.getLong("id"),res2.getString("designation"),res2.getInt("qte"),res2.getDouble("prix"),res2.getDate("date").toLocalDate());
                    listLigne.add(new LigneDeCommande(res.getLong("id"),p,res.getInt("qte")));
                }else{
                     System.out.println("nooooooooo");
                }
                
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return listLigne;
    }

    @Override
    public List<Vente> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(long id, LocalDate date,double total, List<LigneDeCommande> liste) {
        //update client set prenom=?, nom=?, email=?, tele=?, address=? where id= ?
        String requet="update vente set date= ?, total = ? where id= ? ";
         String requet2="update lignes set qte= ?, total=? where idVente= ? and id=?";
         PreparedStatement  pst=null;
         ResultSet res;
        
        try{
            pst= connexion.prepareStatement(requet);
            Date date2=Date.valueOf(date);
            pst.setDate(1,date2);
            pst.setDouble(2,total);
            pst.setLong(3, id);
            pst.executeUpdate();
           
            for(LigneDeCommande l:liste){
                
                pst= connexion.prepareStatement(requet2);

                    pst.setInt(1,l.getQte());
                    pst.setDouble(2,l.getTotal());
                    pst.setLong(3,id);
                    pst.setLong(4,l.getId());
                    pst.executeUpdate();
            }
            System.out.println("success Req!");
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }
    
}
