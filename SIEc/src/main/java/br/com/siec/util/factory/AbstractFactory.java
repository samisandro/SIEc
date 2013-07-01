package br.com.siec.util.factory;

import java.io.Serializable;
import java.util.List;

public interface AbstractFactory extends Serializable {

    public <T> T createObject(ClassType typeClass);

    public <T> T createDependObject(ClassType typeObject, List<Object> dependencies);
}
