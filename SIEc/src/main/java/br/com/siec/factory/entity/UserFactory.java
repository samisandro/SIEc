/*
 * %W% %E% Josimar Alves
 *
 * Copyright (c) 2013-2014 Josimar Alves, All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Josimar Alves. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package br.com.siec.factory.entity;

import br.com.siec.factory.AbstractFactory;
import br.com.siec.factory.Factory;
import br.com.siec.factory.qualifiers.UsuarioFactoryQualifier;

/**
 * Categorias
 *
 * @version 1.00 04 August 2013
 * @author Josimar Alves
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
