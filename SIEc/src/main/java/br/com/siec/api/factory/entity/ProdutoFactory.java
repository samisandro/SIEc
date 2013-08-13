package br.com.siec.api.factory.entity;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.Factory;
import br.com.siec.api.factory.qualifiers.ProdutoFactoryQualifier;
import java.util.List;
import javax.inject.Singleton;

@Singleton
@ProdutoFactoryQualifier
public class ProdutoFactory extends Factory implements AbstractFactory {

    private static ProdutoFactory FACTORY;

    public ProdutoFactory() {
    }

    public static AbstractFactory getInstance() {
        if (FACTORY != null) {
            return FACTORY;
        } else {
            return new ProdutoFactory();
        }
    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createObject(java.lang.String)
     */
    @Override
    public Produto createObject(ClassType typeObject) {
        return (Produto) super.createObject(typeObject);
    }

    /**
     * @see
     * br.com.siec.util.factory.AbstractFactory#createDependObject(java.lang.String,
     * java.util.)
     */
    @Override
    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies) {
        return (T) (Produto) super.createDependObject(typeObject, dependencies);
    }
}
