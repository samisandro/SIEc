package br.com.siec.util.factory;

import java.util.List;

public interface AbstractFactory {
 
	public <T> T createObject(String typeObject);
	public <T> T createDependObject(String typeObject, List<T> dependencies);
}
 
