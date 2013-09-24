package br.com.siec.test;

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


import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.interfaces.Composite;
import br.com.siec.model.persistence.interfaces.IAcompanhamento;
import br.com.siec.model.persistence.interfaces.IComponente;
import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.persistence.resource.TipoPreco;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ApplicationFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.service.ProdutoService;

/**
 * ApplicationTest : Classe de Testes
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public class ApplicationTest {

    public static void main(String args[]) {

        AbstractFactory appFactory = ApplicationFactory.getInstance();
        AbstractFactory produtoFactory = appFactory.createObject(ClassType.ProdutoFactory);
        AbstractFactory serviceFactory = appFactory.createObject(ClassType.ServiceFactory);

        IComponente mussarela = produtoFactory.createObject(ClassType.Componente);
        IComponente calabresa = produtoFactory.createObject(ClassType.Componente);
        IComponente massa = produtoFactory.createObject(ClassType.Componente);
        IAcompanhamento refrigerante = produtoFactory.createObject(ClassType.Componente);
        Composite pizza = produtoFactory.createObject(ClassType.Composite);
        Preco precoC = produtoFactory.createObject(ClassType.Preco);
        Preco precoP = produtoFactory.createObject(ClassType.Preco);
        Preco precoM = produtoFactory.createObject(ClassType.Preco);
        Preco precoG = produtoFactory.createObject(ClassType.Preco);
        Preco precoF = produtoFactory.createObject(ClassType.Preco);

        precoC.setTipo(TipoPreco.COMUM);
        precoC.setValor(4.99);
        precoP.setTipo(TipoPreco.PEQUENA);
        precoP.setValor(1.99);
        precoM.setTipo(TipoPreco.MEDIA);
        precoM.setValor(2.99);
        precoG.setTipo(TipoPreco.GRANDE);
        precoG.setValor(3.99);
        precoF.setTipo(TipoPreco.FAMILIA);
        precoF.setValor(4.99);

        mussarela.setCategoria(Categorias.Ingredientes);
        mussarela.setNome("Mussarela");
        mussarela.addPreco(precoP);
        mussarela.addPreco(precoM);
        mussarela.addPreco(precoG);
        mussarela.addPreco(precoF);

        calabresa.setCategoria(Categorias.Ingredientes);
        calabresa.setNome("Calabresa");
        calabresa.addPreco(precoP);
        calabresa.addPreco(precoM);
        calabresa.addPreco(precoG);
        calabresa.addPreco(precoF);

        massa.setCategoria(Categorias.Massa);
        massa.setNome("Massa");
        massa.addPreco(precoP);
        massa.addPreco(precoM);
        massa.addPreco(precoG);
        massa.addPreco(precoF);

        refrigerante.setCategoria(Categorias.Bebidas);
        refrigerante.setNome("Coca-Cola");
        refrigerante.addPreco(precoC);

        pizza.setNome("Pizza Tradicional");
        pizza.addComponente(mussarela);
        pizza.addComponente(calabresa);
        pizza.addComponente(massa);

        ProdutoService produtoService = serviceFactory.createObject(ClassType.ProdutoService);

        if (produtoService.save((Produto) pizza)) {
            System.out.println("OK");
        } else {
            System.out.println("Not OK");
        }
    }
}
