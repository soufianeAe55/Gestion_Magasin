/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVente;

import gestionpro.Produit;

/**
 *
 * @author Soufiane
 */
public class LigneDeCommande {
    private long id;
    private Produit p;
    private String designation;
    private double prix;
    private int qte;
    private double total;

    public LigneDeCommande(long id,Produit p, int qte) {
        this.id=id;
        this.p = p;
        this.qte = qte;
        this.total = this.p.getPrix()*this.qte;
        this.designation=this.p.getDesignation();
        this.prix=this.p.getPrix();
    }

    public String getDesignation() {
        return designation;
    }

    public double getPrix() {
        return prix;
    }
   
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
        this.total = this.p.getPrix()*this.qte;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
        this.total = this.p.getPrix()*this.qte;
    }

    public double getTotal() {
        return total;
    }
    
    
}
