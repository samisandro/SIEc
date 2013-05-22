/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Josimar Alves
 * @version 1.0
 */
public class JPAUtil {

    /**
     *
     */
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("SIECPU");

    private void JPAUtil() {}
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
