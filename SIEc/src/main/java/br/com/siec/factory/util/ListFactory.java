/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.factory.util;

import br.com.siec.factory.AbstractFactory;
import br.com.siec.factory.ClassType;
import br.com.siec.factory.Factory;
import br.com.siec.factory.entity.ListEnum;
import br.com.siec.factory.entity.ProdutoFactory;
import br.com.siec.factory.qualifiers.ListFactoryQualifier;
import br.com.siec.model.persistence.entity.Telefone;
import br.com.siec.model.persistence.interfaces.ITelefone;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Produces;
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
    
    @Produces
    public List<ITelefone> createListTelefones(){
        return new ArrayList<ITelefone>();
    }
    
}
