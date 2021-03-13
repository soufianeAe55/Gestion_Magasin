/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import partie2.Operation;

/**
 *
 * @author Soufiane
 */
public class OperationImp extends AbstractDAO implements IoperationDAO {

    @Override
    public void add(Operation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Operation obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Operation getOne(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Operation> getAll(long id) {
         List <Operation> list= new ArrayList<>();
        String requet="select * from operation where id_compte= ?";
         PreparedStatement  pst=null;
         ResultSet res;
        try{
            pst= connexion.prepareStatement(requet);
            pst.setLong(1, id);
            res=pst.executeQuery();
            System.out.println("success Req!");
            while(res.next()){
               System.out.println(res.getLong("id"));
             // Date date=res.getDate("date");
          list.add(new Operation(res.getLong("id"),res.getString("type"),res.getDouble("montant"),res.getDate("date").toLocalDate()));
            }
            
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Operation> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
