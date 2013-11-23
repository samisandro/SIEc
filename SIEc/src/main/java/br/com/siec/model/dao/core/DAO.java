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
package br.com.siec.model.dao.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.ejb.EntityManagerImpl;

/**
 * DAO<T> : DAO padrão da aplicação.
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public abstract class DAO<T> implements IDAO<T> {

    @Inject
    @Default
    private EntityManager entityManager;
    private Class<T> typeClass;
    protected Logger logger;

    public DAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        typeClass = (Class) pt.getActualTypeArguments()[0];
        logger = Logger.getLogger(this.typeClass);
    }

    @Override
    public boolean save(T t) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(T entidade)} Incluindo entidade: " + t.toString());
            }
            entityManager.persist(t);
            return true;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(T entidade) -> Erro} Erro na inclusão: " + this.typeClass.getSimpleName() + " -> " + e);
            }
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{delete(T entidade)} Excluindo entidade: " + t.toString());
            }
            this.entityManager.remove(entityManager.merge(t));
            return true;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{delete(T entidade) -> Erro} Erro de exclusão: " + e);
            }
            return false;
        }
    }

    @Override
    public T find(Long id) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(Long id)} Buscando entidade: [" + id + "]");
            }
            return (T) this.entityManager.find(typeClass, id);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(Long id) -> Erro} Erro de busca: " + e);
            }
            return null;
        }
    }

    /**
     * Query dinamica de busca. Busca de acordo
     * com o atributo passado.
     * @param param
     * @param attribute
     * @return List<T> lista do objeto parametrizado. 
     */
    @Override
    public List<T> findBy(String param, String attribute) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(String param, String attribute)} Buscando entidade por: [" + attribute + "]" + " - Parametro" + "[" + param + "]");
            }

            Query query = entityManager.createQuery(getFindByQuery(attribute));

            query.setParameter("param", "%" + param + "%");
            query.setHint("org.hibernate.cacheable", true);
            return query.getResultList();
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(String param, String attribute) -> Erro} Erro de busca: " + e);
            }
            return null;
        }

    }

    private String getFindByQuery(String attribute) {
        StringBuilder findByQuery = new StringBuilder();
        findByQuery.append("select o from ")
                .append(typeClass.getName())
                .append(" o where ")
                .append(attribute)
                .append(" like :param");

        return findByQuery.toString();
    }

    @Override
    public boolean update(T t) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{update(T entidade)} Alterando entidade: [" + t.toString() + "]");
            }
            this.entityManager.merge(t);
            return true;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{update(T entidade) -> Erro} Erro de atualização: " + e);
            }
            return false;
        }


    }

    @Override
    public List<T> listAll() {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{listAll()} Buscando todas as entidades: [" + typeClass.getSimpleName() + "]");
            }

            Query query = entityManager.createQuery("select o from "+typeClass.getName()+" o");

            return query.getResultList();

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{listAll() -> Erro} Erro na busca de todas as entidades: " + e);
            }
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
    public <P> P validate(P p) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{validate(T entidade)} Validando entidade: " + p.toString());
            }
            List<P> results = getSession().createCriteria(p.getClass()).add(Example.create(p)).list();
            return results.get(0);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{validate(T entidade) -> Erro} Entidade não encontrada - Inserindo Entidade: " + e);
            }
            this.entityManager.persist(p);
            return p;
        }
    }

    public Session getSession() {
        Session session;
        if (this.entityManager.getDelegate() instanceof EntityManagerImpl) {
            EntityManagerImpl entityManagerImpl = (EntityManagerImpl) entityManager.getDelegate();
            session = entityManagerImpl.getSession();
        } else {
            session = (Session) entityManager.getDelegate();
        }
        return session;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
