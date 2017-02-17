/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.util.JsfUtil;
import Facade.AbstractFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;

/**
 *
 * @author MAURICIO
 */
public abstract class AbstractPersistenceController<T> implements Serializable{

    protected abstract AbstractFacade getFacade();
    protected abstract T getSelected();
    protected abstract void setSelected(T selected);
    protected abstract void setItems(List<T> items);
    protected abstract void setEmbeddableKeys();
    protected abstract void initializeEmbeddableKey();
    protected abstract Object calculatePrimaryKey();
    protected abstract void prepareCreate();
    protected abstract void prepareUpdate();
    
    protected void create() {
        prepareCreate();
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("Utils/Bundle").getString("SuccessfullyCreatedRegistry"));
        if (!JsfUtil.isValidationFailed()) {
            setItems(null);// Invalidate list of items to trigger re-query.
        }
    }

    protected void update() {
        prepareUpdate();
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("Utils/Bundle").getString("SuccessfullyUpdatedRegistry"));
    }

    protected void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("Utils/Bundle").getString("SuccessfullyDeletedRegistry"));
        if (!JsfUtil.isValidationFailed()) {
            setSelected(null); // Remove selection
            setItems(null);// Invalidate list of items to trigger re-query.
        }
    }
    
    protected void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (getSelected() != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(getSelected());
                } else {
                    getFacade().remove(getSelected());
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("Utils/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("Utils/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
}
