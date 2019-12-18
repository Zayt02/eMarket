/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Zayt
 */
public abstract class AbstractSessionBean<T> {

    private Class<T> entityClass;

    public AbstractSessionBean(Class<T> entityClass) {
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
    
    public List<T> findByNamedQuery(String query, String parameter, String parameter_value)
    {
        Query q = getEntityManager().createNamedQuery(query);
        q.setParameter(parameter, parameter_value);
        return q.getResultList();
    }
    
    public List<T> findByUserQuery(String query)
    {
        Query q = getEntityManager().createNamedQuery(query);
        return q.getResultList();
    }

//    public List<T> findByFK(String table_name, String reference_table, String field, String id) {
//        String query = "select a from " + table_name + " a," + reference_table + " b where a." + field + "." + field + " = b." + field + " and b." + field + " = " + id;
//        return getEntityManager().createQuery(query).getResultList();
//    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq
                = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq
                = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq
                = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
