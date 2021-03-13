/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.time.LocalDate;
import partie2.Compte;

/**
 *
 * @author Soufiane
 */
public interface ICompteDAO extends IDAO <Compte> {
    Compte getAll(String num);
    public void add(long id,String Type,double m,LocalDate date);
}
