/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paiements;

/**
 *
 * @author Soufiane
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Soufiane
 */
public class Compte implements Serializable {
  private long id;
  private String numero;
  private String nom;
  private String prenom;
  
    public Compte(long id, String numero, String nom, String prenom) {
        this.id = id;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

  
  
  
}

