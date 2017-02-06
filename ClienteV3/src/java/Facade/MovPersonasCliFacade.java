/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.MovPersonasCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class MovPersonasCliFacade extends AbstractFacade<MovPersonasCli> {

    public MovPersonasCliFacade() {
        super(MovPersonasCli.class);
    }
    
}
