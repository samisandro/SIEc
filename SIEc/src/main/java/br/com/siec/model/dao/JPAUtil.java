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
package br.com.siec.model.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPAUtil: Efetua a criação do EntityManager para a utilização das classes Data
 * Acess Object - DAO.
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public class JPAUtil {

    /**
     * <p> Metodo usado através de Injeção de Dependências </p>
     * @return EntityManagerFactory
     */
    @Produces @ApplicationScoped 
    public EntityManagerFactory criaFactory() {
        return Persistence.createEntityManagerFactory("SIECPU");
    }

    /**
     *
     * @param factory
     * @return EntityManeger
     */
    @Produces @RequestScoped
    public EntityManager criaEM(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
    
    /**
     *
     * @param manager
     */
    public void finaliza(@Disposes EntityManager manager) {
      manager.close();
   }
}
