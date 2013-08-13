/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.api.factory.service;

import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.Factory;
import java.util.List;

/**
 *
 * @author josimar
 */
public class ServiceFactory extends Factory implements AbstractFactory{
    
    private static ServiceFactory factory;

    private ServiceFactory() {
    }

    public static AbstractFactory getInstance() {
        if (factory != null) {
            return factory;
        } else {
            return new ServiceFactory();
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
