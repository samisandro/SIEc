/*
 * %W% %E% Josimar Alves
 *
 * Copyright (c) 2013-2014 Josimar Alves, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Josimar Alves. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package br.com.siec.model.persistence.daoImpl;

import br.com.siec.model.persistence.dao.IGenericDAO;
import br.com.siec.model.persistence.dao.JPAUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * GenericDAOImpl<T> : DAO generico parametrizado 
 * com a Classe ao qual o DAO será usado.
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public abstract class GenericDAOImpl<T> implements IGenericDAO<T> {

    
    protected EntityManager entityManager;
    private Class<T> typeClass;

    public GenericDAOImpl() {        
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        typeClass = (Class) pt.getActualTypeArguments()[0];
        this.entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public boolean salve(T t) {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(t);
            this.entityManager.getTransaction().commit();
            this.entityManager.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            this.entityManager.remove(entityManager.merge(t));
            //this.entityManager.remove(this.entityManager.getReference(typeClass, t));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T find(Object id) {
        try {
            return (T) this.entityManager.find(typeClass, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<T> findBy(String param, String attribute) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(typeClass);
            Root<T> root = criteriaQuery.from(typeClass);
            criteriaQuery.select(root);

            TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
            return (List<T>) query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean update(T t) {
        return false;

    }

    @Override
    public List<T> listAll() {
        try {
            return this.entityManager.createQuery("select o from " + typeClass.getSimpleName() + " o").getResultList();
        } catch (Exception e) {
            return null;
        }

    }
    
    @Override
    public T validate(T t){
        return null;
        
    }
}
