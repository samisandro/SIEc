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
package br.com.siec.model.daoImpl;

import br.com.siec.model.dao.IGenericDAO;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.jboss.logging.Logger;

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
    @Default
    protected EntityManager entityManager;
    private Class<T> typeClass;
    protected Logger logger;

    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        typeClass = (Class) pt.getActualTypeArguments()[0];
        logger = Logger.getLogger(this.typeClass);
    }

    @Override
    public boolean salve(T t) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(T entidade)} Incluindo entidade: " + t);
            }
            this.entityManager.persist(t);
            return true;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(T entidade) -> Erro} Erro de inclusão: " + e);
            }
            return false;
        }
    }

    @Override
    public boolean delete(T t) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{delete(T entidade)} Excluindo entidade: " + t);
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

    @Override
    public List<T> findBy(String param, String attribute) {
        try {/*
             CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
             CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(typeClass);
             Root<T> root = criteriaQuery.from(typeClass);
             criteriaQuery.select(root);

             TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
             return (List<T>) query.getResultList();*/

            Query query = entityManager.createQuery("select o from " + typeClass.getSimpleName() + " o where o." + attribute + " like :param");
            query.setParameter("param", "%" + param + "%");
            List<T> results = query.getResultList();

            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(String param, String attribute)} Buscando entidade por: [" + attribute + "]" + " - Parametro" + "[" + param + "]");
            }
            return results;
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{findBy(String param, String attribute) -> Erro} Erro de busca: " + e);
            }
            return null;
        }

    }

    @Override
    public boolean update(T t) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{update(T entidade)} Alterando entidade: [" + t + "]");
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
            return this.entityManager.createQuery("select o from " + typeClass.getSimpleName() + " o").getResultList();
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
    public T validate(T t) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{validate(T entidade)} Validando entidade: " + t);
            }
            final Session session = (Session) this.entityManager.getDelegate();
            final Criteria crt = session.createCriteria(typeClass);
            List<T> results = crt.add(Example.create(t)).list();
            return results.get(0);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{validate(T entidade) -> Erro} Entidade não encontrada - Inserindo Entidade: " + e);
            }
            salve(t);
            return t;
        }
    }
}
