/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.DepartamentosCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class DepartamentosCliFacade extends AbstractFacade<DepartamentosCli> {

    public DepartamentosCliFacade() {
        super(DepartamentosCli.class);
    }
    
}
