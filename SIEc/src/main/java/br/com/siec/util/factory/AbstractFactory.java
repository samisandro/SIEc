package br.com.siec.util.factory;

import java.util.List;

public interface AbstractFactory {

    public <T> T createObject(ClassType typeClass);

    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies);
}
