/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author Soufiane
 */
public interface IDAO <T> {
    public void add(T obj);
     public void update(T obj);
    public void delete(long id);
    public T getOne(long id);
    public List <T> getAll();
}
