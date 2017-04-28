/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;

/**
 *
 * @author Kmilo
 */
public abstract class AbstractMasterDataModel <T> implements Serializable{
    private String errorObservation;

    public String getErrorObservation() {
        return errorObservation;
    }

    public void setErrorObservation(String errorObservation) {
        this.errorObservation = errorObservation;
    }
    
    public abstract void setEntity(T entity);
    public abstract T getEntity();
    
    
}
