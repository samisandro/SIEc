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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 * GenericDAOImpl<T> : DAO padrão da aplicação.
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public abstract class GenericDAOImpl<T> implements IGenericDAO<T> {

    /**
     * EntityManager criado através de Injeção de Dependências.
     */
    @Inject
    protected EntityManager entityManager;
    private Class<T> typeClass;

    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        typeClass = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public boolean salve(T t) {
        try {
            this.entityManager.persist(t);
            return true;
        } catch (Exception e) {
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
    public T find(Long id) {
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
        try {
            this.entityManager.refresh(entityManager.merge(t));
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    @Override
    public List<T> listAll() {
        try {
            return this.entityManager.createQuery("select o from " + typeClass.getSimpleName() + " o").getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     *
     * @param t
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public T validate(T t) {
        try {
            final Session session = (Session) this.entityManager.getDelegate();
            final Criteria crt = session.createCriteria(typeClass);
            List<T> results = crt.add(Example.create(t)).list();
            return results.get(0);
        } catch (Exception e) {
            salve(t);
            return t;
        }
    }
}
