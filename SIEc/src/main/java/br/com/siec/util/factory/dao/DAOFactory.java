/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.util.factory.dao;

import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ClassType;
import br.com.siec.util.factory.Factory;
import java.util.List;

/**
 *
 * @author josimar
 */
public class DAOFactory extends Factory implements AbstractFactory{
     private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static AbstractFactory getInstance() {
        if (daoFactory != null) {
            return daoFactory;
        } else {
            return new DAOFactory();
        }

    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createObject(java.lang.String)
     */
    @Override
    public <T> T createObject(ClassType typeObject) {
        return super.createObject(typeObject);        
    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createDependObject(java.lang.String,
     * java.util.)
     */
    @Override
    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies) {
        return null;
    }
    
}
