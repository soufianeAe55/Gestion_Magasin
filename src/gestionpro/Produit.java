/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpro;

import java.time.LocalDate;
import javafx.scene.control.Button;

/**
 *
 * @author Soufiane
 */
public class Produit {
    private long id;
    private String designation;
    private int qte;
    private double prix;
    private LocalDate date;
    private double total;
   

    public long getId() {
        return id;
    }

    
    
    public Produit(long id, String designation, int qte, double prix, LocalDate date) {
       
        this.id = id;
        this.designation = designation;
        this.qte = qte;
        this.prix = prix;
        this.date = date;
        setTotal(qte*prix);
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", designation=" + designation + ", qte=" + qte + ", prix=" + prix + ", date=" + date + '}';
    }
    
    
    
}
