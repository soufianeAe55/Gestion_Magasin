/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import partie2.Operation;

/**
 *
 * @author Soufiane
 */
public interface IoperationDAO extends IDAO <Operation> {
    List <Operation> getAll(long id);
}
