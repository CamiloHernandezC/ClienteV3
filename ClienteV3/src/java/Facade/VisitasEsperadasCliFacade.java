/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.VisitasEsperadasCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class VisitasEsperadasCliFacade extends AbstractFacade<VisitasEsperadasCli> {

    public VisitasEsperadasCliFacade() {
        super(VisitasEsperadasCli.class);
    }
    
}
