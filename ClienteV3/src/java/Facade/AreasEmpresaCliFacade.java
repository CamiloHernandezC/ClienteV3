/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.AreasEmpresaCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MAURICIO
 */
@Stateless
public class AreasEmpresaCliFacade extends AbstractFacade<AreasEmpresaCli> {

    @PersistenceContext(unitName = "ClienteV3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreasEmpresaCliFacade() {
        super(AreasEmpresaCli.class);
    }
    
}
