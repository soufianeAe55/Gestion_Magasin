/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Soufiane
 */
public class SingleConnection {
     String db="compte_exam";
    String user="root";
    String pwd="";
    String url="jdbc:mysql://localhost:3306/"+db;
    private static Connection connexion=null;
    
    private SingleConnection(){
        try{
        connexion=DriverManager.getConnection(url, user, pwd);
            System.out.println("test connexion");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static Connection getConnexion(){
       
        if(connexion==null){
                 new SingleConnection();
               
        }
       
         return connexion;
    }
    
}
