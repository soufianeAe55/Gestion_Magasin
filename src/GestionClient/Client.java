/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

/**
 *
 * @author Soufiane
 */
public class Client {
    private long id;
    private String prenom;
    private String nom;
    private String email;
    private String Tele;
    private String address;

    public Client(long id, String prenom, String nom, String email, String Tele,String address ) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.Tele = Tele;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTele() {
        return Tele;
    }

    public void setTele(String Tele) {
        this.Tele = Tele;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", Tele=" + Tele + ", address=" + address + '}';
    }
    
    
    
}
