/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import Connection.Connection;
import Utils.Constants;
import Utils.Result;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author chernandez
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        EntityManagerFactory emf = Connection.getInstance().getEntityManagerFactory();
        return emf.createEntityManager();
    }

    /**
     * Realiza un insert en la tabla correspondiente
     *
     * @param entity
     * @return errorCode of result class
     */
    public Result create(T entity) {
        String errorMsg = "";//Here store error message if exist
        int errorCode = 0;//Here store the errorCode to make switch in finally statement
        Result validateResult = validate(entity);
        if (validateResult.errorCode != Constants.OK) {
            return validateResult;
        }
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();//this never must throw an exception because never should be an active transacction if getEntityManager return a new instance
            em.persist(entity);
            em.flush();
        } catch (Exception e) {
            errorCode = Constants.UNKNOWN_EXCEPTION;
            errorMsg = e.getCause().getLocalizedMessage();
        } finally {
            return endTransaction(tx, em, new Result(errorMsg, errorCode));
        }
    }

    /**
     * Devuleve la lista de los objetos en la tabla de la base de datos
     *
     * @return ArraysList with all data ErrorCode: 1 no result
     */
    public Result findAll() {
        EntityManager em = getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> listaTmp = em.createQuery(cq).getResultList();
        if (listaTmp.isEmpty()) {
            System.out.println(Constants.MESSAGE_NO_RESULT_EXCEPTION + entityClass);
            return new Result(null, Constants.NO_RESULT_EXCEPTION);
        }
        /*for (int i = 0; i < listaTmp.size(); i++) {
            em.refresh(listaTmp.get(i));
        }*/
        return new Result(listaTmp, Constants.OK);
    }

    /**
     *
     * @param entity
     * @return Result - T true si es posible editar la entidad, codeError 1 si
     * hay error en el constrain, 2 si hay una excepcion durante la transaccion,
     * 3 si hay una excepcion en el commit
     */
    public Result edit(T entity) {
        int errorCode = 0;
        String errorMsg = "";

        Result validateResult = validate(entity);
        if (validateResult.errorCode != Constants.OK) {
            return validateResult;
        }
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(entity);
        } catch (Exception e) {
            errorCode = Constants.UNKNOWN_EXCEPTION;
            errorMsg = e.getCause().getMessage();
        } finally {
            if (errorMsg.isEmpty()) {
                errorMsg = "Error de data en Base de datos";
            }
            return endTransaction(tx, em, new Result(errorMsg, errorCode));
        }
    }

    /**
     * 
     *
     * @param entity
     * @return errorCode 1 unknown exception 2 rollback exception
     */
    public Result remove(T entity) {
        int errorCode = 0;
        String errorMsg = "";
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(entity);
        } catch (Exception e) {
            errorMsg = e.getLocalizedMessage();
            errorCode = Constants.UNKNOWN_EXCEPTION;
        } finally {
            return endTransaction(tx, em, new Result(errorMsg, errorCode));
        }
    }

    /**
     * Devuelve el modelo con primary key = al parámetro
     *
     * @param id primary key
     * @return model if no exception occurs errorCode: 0 no error, 1 no result
     * exception, 2 castException
     */
    public Result find(Object id) {
        T entity = getEntityManager().find(entityClass, id);
        if (entity == null) {
            System.out.println(Constants.MESSAGE_NO_RESULT_EXCEPTION+ entityClass+" ID: " + id);
            return new Result(null, Constants.NO_RESULT_EXCEPTION);
        }
        return new Result(entity, Constants.OK);
    }

    /**
     * Ejecuta un query y devuelve un array
     *
     * @param squery string que se desea ejecutar
     * @return same as findByQueryArray with parameters
     */
    public Result findByQueryArray(String squery) {
        return findByQueryArray(squery, new ArrayList<>(), new HashMap());
    }

    /**
     * Find an array of data whit the parameters given
     *
     * @param squery
     * @param keyName parameter's name in a list (correspond to map key)
     * @param parameters Map with parameter's value and key
     * @return errorCode: 0 no error, 1 no result, 2 unknown exception, 3 commit
     * exception, 4 cast exception
     */
    public Result findByQueryArray(String squery, List<String> keyName, Map parameters) {
        Result result = null;

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery(squery);
            for (String p : keyName) {//if parameters are empty, won't enter to this method
                query.setParameter(p, parameters.get(p));
            }
            //Ejecuta el Query , obtiene el resultado
            List<T> list;
            list = (List<T>) query.getResultList();
            if (list.isEmpty()) {
                result = new Result(null, Constants.NO_RESULT_EXCEPTION);
                return null;//go to finally statement
            }
            /*for (int i = 0; i < list.size(); i++) {
                //em.persist(u.get(i));
                em.refresh(list.get(i));
            }*/
            result = new Result(list, Constants.OK);
        } catch (Exception e) {
            result = new Result(null, Constants.UNKNOWN_EXCEPTION);
        } finally {
            return endTransaction(tx, em, result);
        }
    }

    /**
     * Ejecuta un query y devuelve el primer resultado
     *
     * @param squery string que se desea ejecutar
     * @param maxResult true si se desea hacer setMaxResult para solo traer uno,
     * false de lo contrario (hace un getSingleResult)
     * @return T - modelo si hay un resultado errorCode= 1 no result exception,
     * 2 no unique result exception, 3 unknown exception, 4 comit exception, 5
     * castException
     */
    public Result findByQuery(String squery, boolean maxResult) {
        Result result = null;

        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query query = em.createQuery(squery);
            T entity;
            if (maxResult) {
                entity = (T) query.setMaxResults(1).getSingleResult();
            } else {
                entity = (T) query.getSingleResult();
            }
            /*em.refresh(entity);
            if (maxResult) {
                entity = (T) query.setMaxResults(1).getSingleResult();
            } else {
                entity = (T) query.getSingleResult();
            }*/
            result = new Result(entity, Constants.OK);
        } catch (NoResultException nre) {
            result = new Result(null, Constants.NO_RESULT_EXCEPTION);
            return null;//go to finally statement
        } catch (NonUniqueResultException nure) {
            result = new Result(null, Constants.NO_UNIQUE_RESULT_EXCEPTION);
            return null;//go to finally statement
        } catch (Exception e) {
            result = new Result(null, Constants.UNKNOWN_EXCEPTION);
            return null;//go to finally statement
        } finally {
            return endTransaction(tx, em, result);
        }
    }

    /**
     * rollback transaction if transaction is still actived and close the entity
     * manager
     *
     * @param tx
     * @param em
     * @throws Exception
     */
    private Result endTransaction(EntityTransaction tx, EntityManager em, Result result) {
        try {
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
            String errorMsg = e.getMessage();
            System.out.println(Constants.MESSAGE_UNKNOWN_EXCEPTION_AT_FINALLY + entityClass);
            return new Result(errorMsg, Constants.UNKNOWN_EXCEPTION_AT_FINALLY);
        }
        switch (result.errorCode) {
            case Constants.NO_RESULT_EXCEPTION:
                System.out.println(Constants.MESSAGE_NO_RESULT_EXCEPTION);
                break;
            case Constants.NO_UNIQUE_RESULT_EXCEPTION:
                System.out.println(Constants.MESSAGE_NO_RESULT_EXCEPTION);
                break;
            case Constants.UNKNOWN_EXCEPTION:
                System.out.println(Constants.MESSAGE_UNKNOWN_EXCEPTION);
                break;
            case Constants.OK:
                return result;
                //return new Result(result.result, Constants.OK);
        }
        return result;

    }

    private Result validate(T entity) {
        String errorMsg = "";
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            System.out.println(Constants.MESSAGE_VALIDATION_ERROR + " " + entityClass);
            Iterator<ConstraintViolation<Object>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<Object> cv = iterator.next();
                errorMsg += cv.getPropertyPath() + " " + cv.getMessage();
                System.out.println(cv.getRootBeanClass().getName() + "." + errorMsg);
            }
            return new Result(errorMsg, Constants.VALIDATION_ERROR);
        }
        return new Result(null, Constants.OK);
    }

}
