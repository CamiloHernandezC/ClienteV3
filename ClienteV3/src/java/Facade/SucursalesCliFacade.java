/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.SucursalesCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class SucursalesCliFacade extends AbstractFacade<SucursalesCli> {

    public SucursalesCliFacade() {
        super(SucursalesCli.class);
    }
    
}
