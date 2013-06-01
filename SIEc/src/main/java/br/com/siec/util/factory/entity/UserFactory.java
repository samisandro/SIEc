/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.util.factory.entity;

import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.Factory;

/**
 *
 * @author josimar
 */
public class UserFactory extends Factory implements AbstractFactory{
    
    private static UserFactory factory;
    
    private void UserFactory(){
        
    }
    
    public static AbstractFactory getInstance(){
       if (factory != null) {
            return factory;
        } else {
            return new UserFactory();
        }
        
    }
    
}
