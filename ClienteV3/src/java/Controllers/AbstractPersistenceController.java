/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.util.JsfUtil;
import Entities.AbstractEntity;
import Facade.AbstractFacade;
import Utils.Constants;
import Utils.Result;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;

/**
 *
 * @author MAURICIO
 * @param <AbstractEntity>
 */
public abstract class AbstractPersistenceController<T> implements Serializable{
    
    protected abstract AbstractFacade getFacade();
    protected abstract T getSelected();
    protected abstract void setSelected(T selected);
    protected abstract void setItems(List<T> items);
    protected abstract void setEmbeddableKeys();
    protected abstract void initializeEmbeddableKey();
    protected abstract void prepareCreate();
    protected abstract void prepareUpdate();
    
    public void calculatePrimaryKey(String squery){
        Result result = getFacade().findByQuery(squery, true);//Only need the first result
        if(result.errorCode==Constants.NO_RESULT_EXCEPTION){
            AbstractEntity entity = (AbstractEntity) getSelected();
            entity.setPrimaryKey(((AbstractEntity) result.result).getPrimaryKey()+1L);
            setSelected((T) entity);
        }else{
            AbstractEntity entity = (AbstractEntity) getSelected();
            entity.setPrimaryKey(((AbstractEntity) result.result).getPrimaryKey()+1L);
            setSelected((T) entity);
        }
        //In previous section we need get and set selected because all data are already loaded
    }
    
    public void assignParametersToUpdate(){
        AbstractEntity entity = (AbstractEntity) getSelected();
        entity.setUser(JsfUtil.getSessionUser().getIdPersona());
        entity.setDate(new Date());
        setSelected((T) entity);
    }
    
    public void create() {
        prepareCreate();
        persist(JsfUtil.PersistAction.CREATE);
        if (!JsfUtil.isValidationFailed()) {
            setItems(null);// Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        prepareUpdate();
        persist(JsfUtil.PersistAction.UPDATE);
    }

    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE);
        if (!JsfUtil.isValidationFailed()) {
            setSelected(null); // Remove selection
            setItems(null);// Invalidate list of items to trigger re-query.
        }
    }
    
    protected void persist(JsfUtil.PersistAction persistAction) {
        if (getSelected() != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(getSelected());
                } else {
                    getFacade().remove(getSelected());
                }
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
