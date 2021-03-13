/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie2;

import DAO.CompteDAOImp;
import DAO.ICompteDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Soufiane
 */
public class CompteHandler extends Thread {
    AjouterCompte ajout=null;
    AfficheDetails aff=null;
    private ServerSocket serverSocket=null;
    private Socket socketEnd1=null;
    private static final int port=3333;
    
    
    CompteHandler() throws IOException{
        
    }
    public void accepteConnexion(){
        
         
    }
    @Override
    public void run(){
        try{
            System.out.println("server...");
             serverSocket=new ServerSocket(port);
              System.out.println("server strated");
              System.out.println("att conn...");

            
              while(true){  
                socketEnd1=serverSocket.accept();
                ServerThread st=new ServerThread(socketEnd1);
                st.start();
              }
         }catch(Exception e){
             System.out.println(e);
         } 
    }
    CompteHandler(AjouterCompte a){
        this.ajout=a;
    }
    
    CompteHandler(AfficheDetails aff){
        this.aff=aff;
        
    }
    
    
    public void add (){
        String prenom=ajout.PrenomCompteText.getText();
        String nom=ajout.NomCompteText.getText();
        String numCompte=ajout.NumCompteText.getText();
        
        Compte c=new Compte(0,numCompte,nom,prenom);
        ICompteDAO cdao=new CompteDAOImp();
        cdao.add(c);
    }
    public void getCompte(){
        String num=aff.numCompteText.getText();
        ICompteDAO cdao=new CompteDAOImp();
        Compte c=cdao.getAll(num);
        double s=0;
        for(Operation o :c.getOps()){
            
            if(o.getType().equals("VERS")){
                 s+=o.getMontant();
                
            }else if(o.getType().equals("RETR")|| o.getType().equals("PDIST")){
                s-=o.getMontant();
                
            }
           
        }
        this.aff.NomCompteValue.setText(c.getNom());
        this.aff.soldeCompteValue.setText(s+"");
        this.aff.OperationList.clear();
        this.aff.OperationList.addAll(c.getOps());
    }
      
}
