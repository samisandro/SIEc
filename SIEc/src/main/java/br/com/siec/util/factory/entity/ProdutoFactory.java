package br.com.siec.util.factory.entity;

import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ClassType;
import br.com.siec.util.factory.Factory;
import br.com.siec.util.factory.qualifiers.ProdutoFactoryQualifier;
import java.util.List;

@ProdutoFactoryQualifier
public class ProdutoFactory extends Factory implements AbstractFactory {

    private static ProdutoFactory factory;

    public ProdutoFactory() {
    }

    public static AbstractFactory getInstance() {
        if (factory != null) {
            return factory;
        } else {
            return new ProdutoFactory();
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
