package br.com.siec.api.factory.entity;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.Factory;
import br.com.siec.api.factory.qualifiers.ClienteFactoryQualifier;
import br.com.siec.api.factory.qualifiers.PessoaFactoryQualifier;
import java.util.List;

/**
 *
 * @author josimarr
 */
@ClienteFactoryQualifier
public class ClienteFactory extends Factory implements AbstractFactory {

    private static ClienteFactory clienteFactory;
    
    public ClienteFactory(){
        
    }

    public static AbstractFactory getInstance() {
        if (clienteFactory != null) {
            return clienteFactory;
        } else {
            return new ClienteFactory();
        }

    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createObject(java.lang.String)
     */
    @Override
    public Cliente createObject(ClassType typeObject) {
        return super.createObject(typeObject);        
    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createDependObject(java.lang.String,
     * java.util.)
     */
    @Override
    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies) {
        return (T) (Cliente) super.createDependObject(typeObject, dependencies);
    }
}
