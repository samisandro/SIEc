/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.api.factory.util;

import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.Factory;
import br.com.siec.api.factory.entity.ListEnum;
import br.com.siec.api.factory.entity.ProdutoFactory;
import br.com.siec.api.factory.qualifiers.ListFactoryQualifier;
import javax.inject.Singleton;

/**
 *
 * @author josimar
 */
@Singleton
@ListFactoryQualifier
public class ListFactory extends Factory implements AbstractFactory{
    
    private static ProdutoFactory FACTORY;
   
    public ListFactory() {
    }

    public static AbstractFactory getInstance() {
        if (FACTORY != null) {
            return FACTORY;
        } else {
            return new ProdutoFactory();
        }
    }
    
    @Override
    public ListEnum createObject(ClassType typeObject) {
        return (ListEnum) super.createObject(typeObject);
    }
    
}
