/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.api.factory.dao;

import br.com.siec.model.dao.core.IDAO;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.Factory;
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
    public IDAO createObject(ClassType typeObject) {
        return (IDAO) super.createObject(typeObject);        
    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createDependObject(java.lang.String,
     * java.util.)
     */
    @Override
    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies) {
        return (T) (IDAO) super.createDependObject(typeObject, dependencies);
    }
    
}
