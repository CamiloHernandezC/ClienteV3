/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Facade.AbstractFacade;
import Entities.Arl;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kmilo
 */
@Stateless
public class ArlFacade extends AbstractFacade<Arl> {

    @PersistenceContext(unitName = "ClienteV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArlFacade() {
        super(Arl.class);
    }
    
}
