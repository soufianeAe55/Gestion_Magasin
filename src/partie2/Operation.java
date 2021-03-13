/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partie2;

import java.time.LocalDate;

/**
 *
 * @author Soufiane
 */
public class Operation {
    private long id;
    private Compte c;
    private String type;
    private double montant;
    private LocalDate date;

    public Operation(long id, String type,double montant ,LocalDate date) {
        this.id = id;
       
        this.type = type;
        this.date = date;
        this.montant=montant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Compte getC() {
        return c;
    }

    public void setC(Compte c) {
        this.c = c;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    
}
