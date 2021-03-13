/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie2;

import DAO.CompteDAOImp;
import DAO.ICompteDAO;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDate;

/**
 *
 * @author Soufiane
 */
public class ServerThread extends Thread {
    Socket soc=null;
    private OutputStream output=null;
    private InputStream input=null;
    ObjectOutputStream out=null;
    ObjectInputStream in=null;
    
    ServerThread(Socket so){
        try{
            this.soc=so;
            output=this.soc.getOutputStream();
            input=this.soc.getInputStream();
            out=new ObjectOutputStream(output);
            in=new ObjectInputStream(input);

        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void run(){
        try{
           
            String s=(String)in.readObject();
            Double total=(Double)in.readObject();
            LocalDate date=(LocalDate)in.readObject();
            
            Compte c=getCompte(s);
           
            
            if(c!= null){
             double res=calculeSoldCompte(c)-total;
            String nom=c.getNom()+" "+c.getPrenom();
            System.out.println("Sold du compte "+calculeSoldCompte(c));
                if(res >=0){
                    ICompteDAO cdao=new CompteDAOImp();
                    cdao.add(c.getId(),"PDIST",total,date);
                    out.writeObject("Bonjour Mr."+nom+" vous avez effectue un paiement de"+total);
                }else{
                    out.writeObject("FAILED");
                }
            }else{
                out.writeObject("NOT_EXIST!"); 
            }
        }catch(Exception e){
            
        }
    }
    
    public double calculeSoldCompte(Compte c){
         double s=0;
        for(Operation o :c.getOps()){
            System.out.println(o.getType());
            if(o.getType().equals("VERS")){
                 s+=o.getMontant();
                 System.out.println("vers="+s);
            }else if(o.getType().equals("RETR") || o.getType().equals("PDIST") ){
                s-=o.getMontant();
                System.out.println("ret="+s);
            }
           
        }
        return s;
    }
    public Compte getCompte(String num){
          ICompteDAO cdao=new CompteDAOImp();
          Compte c=null;
           c=cdao.getAll(num);
        return c;
    }
    
}
