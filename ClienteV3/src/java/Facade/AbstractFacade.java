/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Utils.Constants;
import Utils.Result;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

/**
 *
 * @author MAURICIO
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public Result findByQuery(String squery, boolean maxResult) {
        try {
            EntityManager em = getEntityManager();
            Query query = em.createQuery(squery);
            T entity;
            if (maxResult) {
                entity = (T) query.setMaxResults(1).getSingleResult();
            } else {
                entity = (T) query.getSingleResult();
            }
            return new Result(entity, Constants.OK);
        } catch (NoResultException nre) {
            return new Result(null, Constants.NO_RESULT_EXCEPTION);
        } catch (NonUniqueResultException nure) {
            return new Result(null, Constants.NO_UNIQUE_RESULT_EXCEPTION);
        }
    }
    
    public Result findByQueryArray(String squery) {
        
        EntityManager em = getEntityManager();
        Query query = em.createQuery(squery);
        List<T> list;
        list = (List<T>) query.getResultList();
        if(list.isEmpty()){
            return new Result(list, Constants.NO_RESULT_EXCEPTION);
        }
        return new Result(list, Constants.OK);
    }
    
}
