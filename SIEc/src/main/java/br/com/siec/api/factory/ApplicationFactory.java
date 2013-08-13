package br.com.siec.api.factory;

import java.util.List;

public class ApplicationFactory extends Factory implements AbstractFactory {

    private static ApplicationFactory applicationFactory;

    private ApplicationFactory() {
    }

    public static AbstractFactory getInstance() {
        if (applicationFactory != null) {
            return applicationFactory;
        } else {
            return new ApplicationFactory();
        }
    }

    @Override
    public <T> T createObject(ClassType typeObject) {
        return super.createObject(typeObject);
    }

    @Override
    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
