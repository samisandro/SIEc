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
package br.com.siec.test;

import br.com.siec.model.persistence.entity.IPJ;
import br.com.siec.model.persistence.entity.IPf;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ApplicationFactory;
import br.com.siec.util.factory.ClassType;

/**
 * Creational : Classe de Testes
 *
 * @version 1.00 25 May 2013
 * @author Josimar Alves
 */
public class CreationalTest {

    public static void main(String args[]) {
        AbstractFactory applicationFactory = ApplicationFactory.getInstance();

        AbstractFactory pessoaFactory = applicationFactory.createObject(ClassType.PessoaFactory);
        AbstractFactory produtoFactory = applicationFactory.createObject(ClassType.ProdutoFactory);

        IPJ pessoaJuridica = pessoaFactory.createObject(ClassType.PJ);
        IPf pessoaFisica = pessoaFactory.createObject(ClassType.PF);

        Produto produtoComponente = produtoFactory.createObject(ClassType.Acompanhamento);
        Produto produtoAcompanhamento = produtoFactory.createObject(ClassType.Componente);

        if ((applicationFactory != null)
                && (pessoaFactory != null)
                && (produtoFactory != null)
                && (pessoaJuridica != null)
                && (pessoaFisica != null)
                && (produtoComponente != null)
                && (produtoAcompanhamento != null)) {

            System.out.println("Factorys OK.");

        } else {
            System.out.println("Factorys NOT OK.");
        }
    }
}
