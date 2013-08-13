package br.com.siec.api.factory.entity;

import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.Factory;
import br.com.siec.api.factory.qualifiers.PessoaFactoryQualifier;
import java.util.List;

/**
 *
 * @author josimarr
 */
@PessoaFactoryQualifier
public class PessoaFactory extends Factory implements AbstractFactory {

    private static PessoaFactory pessoaFactory;
    
    public PessoaFactory(){
        
    }

    public static AbstractFactory getInstance() {
        if (pessoaFactory != null) {
            return pessoaFactory;
        } else {
            return new PessoaFactory();
        }

    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createObject(java.lang.String)
     */
    @Override
    public Pessoa createObject(ClassType typeObject) {
        return super.createObject(typeObject);        
    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createDependObject(java.lang.String,
     * java.util.)
     */
    @Override
    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies) {
        return (T) (Pessoa) super.createDependObject(typeObject, dependencies);
    }
}
