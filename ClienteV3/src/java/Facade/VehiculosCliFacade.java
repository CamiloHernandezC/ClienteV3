/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.VehiculosCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAURICIO
 */
@Stateless
public class VehiculosCliFacade extends AbstractFacade<VehiculosCli> {

    @PersistenceContext(unitName = "PorteriaV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculosCliFacade() {
        super(VehiculosCli.class);
    }
    
}
