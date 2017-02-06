/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.MovDocumentosCli;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chernandez
 */
@Stateless
public class MovDocumentosCliFacade extends AbstractFacade<MovDocumentosCli> {

    public MovDocumentosCliFacade() {
        super(MovDocumentosCli.class);
    }
    
}
