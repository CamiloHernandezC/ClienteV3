/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Facade.AbstractFacade;
import Entities.Objetos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kmilo
 */
@Stateless
public class ObjetosFacade extends AbstractFacade<Objetos> {

    @PersistenceContext(unitName = "ClienteV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetosFacade() {
        super(Objetos.class);
    }
    
}
