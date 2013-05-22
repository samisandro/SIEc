package br.com.siec.util.factory;

import java.util.List;

public class ProdutoFactory implements AbstractFactory {
 
	private static ProdutoFactory factory;
	 
	private ProdutoFactory(){}
	 
	/**
	 * @see br.com.siec.util.factory.AbstractFactory#createObject(java.lang.String)
	 */
        @Override
	public <T> T createObject(String typeObject) {
		return null;
	}
	 
	/**
	 * @see br.com.siec.util.factory.AbstractFactory#createDependObject(java.lang.String, java.util.)
	 */
        @Override
	public <T> T createDependObject(String typeObject, List<T> dependencies) {
		return null;
	}
	 
}
 
