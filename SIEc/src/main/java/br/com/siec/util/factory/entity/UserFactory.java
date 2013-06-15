/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.util.factory.entity;

import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.Factory;
import br.com.siec.util.factory.qualifiers.UsuarioFactoryQualifier;

/**
 *
 * @author josimar
 */
@UsuarioFactoryQualifier
public class UserFactory extends Factory implements AbstractFactory {

    private static UserFactory factory;

    public void UserFactory() {
    }

    public static AbstractFactory getInstance() {
        if (factory != null) {
            return factory;
        } else {
            return new UserFactory();
        }

    }
}
