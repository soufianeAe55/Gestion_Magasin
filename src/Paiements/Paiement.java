/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paiements;

import java.time.LocalDate;

/**
 *
 * @author Soufiane
 */
public class Paiement {
    private long id;
    private LocalDate date;
    private double montant;
    private String type;
    private long numCH;
    private LocalDate dateCH;
    private String banque;
    private String nomCH;
    private long idVente;

    public Paiement(long id, LocalDate date, double montant, String type,long idv) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.type = type;
        this.idVente=idv;
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

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getNumCH() {
        return numCH;
    }

    public void setNumCH(long numCH) {
        this.numCH = numCH;
    }

    public LocalDate getDateCH() {
        return dateCH;
    }

    public void setDateCH(LocalDate dateCH) {
        this.dateCH = dateCH;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getNomCH() {
        return nomCH;
    }

    public void setNomCH(String nomCH) {
        this.nomCH = nomCH;
    }

    public long getIdVente() {
        return idVente;
    }

    public void setIdVente(long idVente) {
        this.idVente = idVente;
    }
    
    
     
}
