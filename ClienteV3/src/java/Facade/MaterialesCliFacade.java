/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.MaterialesCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class MaterialesCliFacade extends AbstractFacade<MaterialesCli> {

    public MaterialesCliFacade() {
        super(MaterialesCli.class);
    }
    
}
