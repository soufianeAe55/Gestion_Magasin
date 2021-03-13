/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import partie2.Compte;
import partie2.Operation;

/**
 *
 * @author Soufiane
 */
public class CompteDAOImp extends AbstractDAO implements ICompteDAO {

    @Override
    public void add(Compte obj) {
        String requet="insert into compte (numero,prenom,nom) values (?,?,?) ";
         PreparedStatement  pst=null;
         ResultSet res;
         
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1,obj.getNumero());
            pst.setString(2,obj.getPrenom());
            pst.setString(3,obj.getNom());
           
           
            pst.executeUpdate();
            
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void update(Compte obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compte getOne(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Compte> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Compte getAll(String num) {
       Compte c=null;
        String requet="select * from compte where numero= ?";
         PreparedStatement  pst=null;
         ResultSet res;
        
         IoperationDAO op=new OperationImp();
        try{
            pst= connexion.prepareStatement(requet);
            pst.setString(1, num);
            
            res=pst.executeQuery();
            if(res.next()){
                op.getAll(res.getLong("id"));
                c=new Compte(res.getLong("id"),res.getString("numero"),res.getString("nom"),res.getString("prenom"));
                c.setOps(op.getAll(res.getLong("id")));
            }else{
                System.out.println("noo");
            }
           
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return c;
    }

    @Override
    public void add(long id, String Type, double m, LocalDate date) {
         String requet="insert into operation (id_compte,type,montant,date) values (?,?,?,?) ";
         PreparedStatement  pst=null;
         ResultSet res;
         
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1,id);
            pst.setString(2,Type);
            pst.setDouble(3,m);
            Date d=Date.valueOf(date);
            pst.setDate(4, d);

            pst.executeUpdate();
           
           
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }

   
    
}
