package br.com.siec.util.factory;

import java.util.List;

public class PessoaFactory implements AbstractFactory {
 
	private static PessoaFactory factory;
	 
	private void PessoaFactory() {
	 
	}
	 
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
	public <T> T createDependObject(String typeObject, List<T> dependencies) {
		return null;
	}
	 
}
 
