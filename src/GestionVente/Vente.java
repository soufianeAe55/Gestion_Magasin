/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import GestionClient.Client;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class Vente {
    private long id;
    private String prenom;
    private String nom;
     private LocalDate date;
    private Client c;
    private List <LigneDeCommande> ligne=new ArrayList <LigneDeCommande>();
    private double total;

    public Vente(long id, LocalDate date, Client c, List <LigneDeCommande> l ) {
        this.id = id;
        this.date = date;
        this.prenom=c.getPrenom();
        this.c=c;
        this.nom=c.getNom();
        this.ligne=l;
        calculTotal();
    }

    @Override
    public String toString() {
        return "Vente{" + "id=" + id + ", date=" + date + ", prenom=" + prenom + ", nom=" + nom + ", c=" + c + ", ligne=" + ligne + ", total=" + total + '}';
    }
 
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public List<LigneDeCommande> getLigne() {
        return ligne;
    }

    public void setLigne(List<LigneDeCommande> ligne) {
        this.ligne = ligne;
        calculTotal();
    }

    public double getTotal() {
        return total;
    }
    
    public void calculTotal(){
        double t=0;
        for(LigneDeCommande e:this.ligne){
            t+=e.getTotal();
        }
        this.total=t;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
