/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converters;


import Converters.util.JsfUtil;
import Entities.AbstractEntity;
import Facade.AbstractFacade;
import Utils.Constants;
import Utils.Result;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author MAURICIO
 * @param <T> Entity class
 */
public abstract class AbstractPersistenceController<T> implements Serializable {
    
    protected String validationErrorObservation;

    protected abstract AbstractFacade getFacade();

    protected abstract T getSelected();

    protected abstract void setSelected(T selected);

    protected abstract void setItems(List<T> items);

    protected abstract void setEmbeddableKeys();

    protected abstract void initializeEmbeddableKey();

    protected abstract void prepareCreate();

    protected abstract void prepareUpdate();
    
    protected void prepareDelete(){
        AbstractEntity entity = (AbstractEntity) getSelected();
        entity.setStatus(Constants.STATUS_INACTIVE);
        setSelected((T) entity);
        prepareUpdate();
    }
    
    public abstract void clean();

    public void calculatePrimaryKey(String squery) {
        Result result = getFacade().findByQuery(squery, true);//Only need the first result
        if (result.errorCode == Constants.NO_RESULT_EXCEPTION) {
            AbstractEntity entity = (AbstractEntity) getSelected();
            entity.setPrimaryKey(1);
            setSelected((T) entity);
        } else {
            AbstractEntity entity = (AbstractEntity) getSelected();
            entity.setPrimaryKey(((AbstractEntity) result.result).getPrimaryKey() + 1);
            setSelected((T) entity);
        }
        //In previous section we need get and set selected because all data are already loaded
    }

    public void assignParametersToUpdate() {
        AbstractEntity entity = (AbstractEntity) getSelected();
        entity.setUser(JsfUtil.getSessionUser().getPersona());
        entity.setDate(new Date());
        setSelected((T) entity);
    }

    public Result create() {
        prepareCreate();
        return persist(JsfUtil.PersistAction.CREATE);
    }

    public Result update() {
        prepareUpdate();
        return persist(JsfUtil.PersistAction.UPDATE);
    }

    public Result delete() {
        prepareDelete();
        return persist(JsfUtil.PersistAction.DELETE);
    }

    protected Result persist(JsfUtil.PersistAction persistAction) {
        if (getSelected() != null) {
            setEmbeddableKeys();
            
            //Fields validation for entity
            T entity = getSelected();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
            if (constraintViolations.size() > 0) {
                validationErrorObservation = "";
                Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
                while (iterator.hasNext()) {
                    ConstraintViolation<T> cv = iterator.next();
                    validationErrorObservation += cv.getPropertyPath() + " " + cv.getMessage();
                }
                return new Result(validationErrorObservation, Constants.VALIDATION_ERROR);
            }
            try {
                if (persistAction == JsfUtil.PersistAction.UPDATE || persistAction == JsfUtil.PersistAction.DELETE) {
                    getFacade().edit(getSelected());
                }
                if (persistAction == JsfUtil.PersistAction.CREATE) {
                    getFacade().create(getSelected());
                }
                return new Result(null, Constants.OK);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    return new Result(msg, Constants.PERSISTANCE_EXCEPTION);
                } else {
                    return new Result(ex, Constants.PERSISTANCE_EXCEPTION);
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                return new Result(ex, Constants.PERSISTANCE_EXCEPTION);
            }
        }
        return new Result(null, Constants.UNKNOWN_EXCEPTION);//This should never happen
    }
}
