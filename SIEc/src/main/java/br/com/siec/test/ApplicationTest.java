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

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.TamanhoPizza;
import br.com.siec.service.Service;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ApplicationFactory;
import br.com.siec.util.factory.ClassType;

/**
 * ApplicationTest : Classe de Testes
 *
 * @version 1.00 21 May 2013
 * @author Josimar Alves
 */
public class ApplicationTest {

    public static void main(String args[]) {
        

   /* 
        AbstractFactory appFactory = ApplicationFactory.getInstance();
        AbstractFactory pessoaFactory = appFactory.createObject(ClassType.PessoaFactory);
        AbstractFactory userFactory = appFactory.createObject(ClassType.UserFactory);
        AbstractFactory serviceFactory = appFactory.createObject(ClassType.ServiceFactory);

        IService pessoaService = serviceFactory.createObject(ClassType.PessoaService);
        IService userService = serviceFactory.createObject(ClassType.UsuarioService);

        IPf pessoa = pessoaFactory.createObject(ClassType.PF);

        pessoa.setNome("Teste 02");
        pessoa.setEmail("teste@02.com.br");

        IUsuario user = userFactory.createObject(ClassType.Usuario);
        user.setPessoa(pessoa);

        user.setAtivo(false);
        user.setLembreteSenha("Lembrete2");
        user.setSenha("password");
        user.setLogin("testesa");


        if (pessoaService.save(pessoa)) {

            System.out.println("Good Job, little Padawan!!!");

            if (userService.save(user)) {
                System.out.println("Great!!!  You'll must a JEDI on day!!!");
            }
        } else {
            System.out.println("Try again or join to Dark Side of The Force");
        }
*/
        AbstractFactory appFactory = ApplicationFactory.getInstance();
        AbstractFactory produtoFactory = appFactory.createObject(ClassType.ProdutoFactory);
        AbstractFactory serviceFactory = appFactory.createObject(ClassType.ServiceFactory);
        
        Produto pizza = produtoFactory.createObject(ClassType.Componente);
        Produto mussarella = produtoFactory.createObject(ClassType.Componente);
        Produto presunto = produtoFactory.createObject(ClassType.Componente);
        Produto massa = produtoFactory.createObject(ClassType.Componente);        
        Produto refrigerante = produtoFactory.createObject(ClassType.Acompanhamento);
                
        mussarella.setCategoria(Categorias.Ingredientes);
        mussarella.setNome("Mussarella");
        mussarella.addPreco(TamanhoPizza.Pequena.toString(), 1.99);
        mussarella.addPreco(TamanhoPizza.Média.toString(), 1.99);
        mussarella.addPreco(TamanhoPizza.Grande.toString(), 1.99);
        mussarella.addPreco(TamanhoPizza.Família.toString(), 1.99);
        
        presunto.setCategoria(Categorias.Ingredientes);
        presunto.setNome("Presunto");
        presunto.addPreco(TamanhoPizza.Pequena.toString(), 1.99);
        presunto.addPreco(TamanhoPizza.Média.toString(), 1.99);
        presunto.addPreco(TamanhoPizza.Grande.toString(), 1.99);
        presunto.addPreco(TamanhoPizza.Família.toString(), 1.99);
        
        massa.setCategoria(Categorias.Massa);
        massa.setNome("Massa Recheada");
        massa.addPreco(TamanhoPizza.Pequena.toString(), 1.99);
        massa.addPreco(TamanhoPizza.Média.toString(), 1.99);
        massa.addPreco(TamanhoPizza.Grande.toString(), 1.99);
        massa.addPreco(TamanhoPizza.Família.toString(), 1.99);
        
        refrigerante.setCategoria(Categorias.Bebidas);
        refrigerante.setNome("Coca-Cola");
        refrigerante.addPreco("Bebida", 4.99);
        
        pizza.setNome("Pizza Presunto Mussarella");
        pizza.addComponente(refrigerante);
        pizza.addComponente(mussarella);
        pizza.addComponente(presunto);
        pizza.addComponente(massa);
        
        Service produtoService = serviceFactory.createObject(ClassType.ProdutoService);
        
        if(produtoService.save(pizza)){
            System.out.println("OK");
        } else {
            System.out.println("Not OK");
        }
        /*
        Produto p = (Produto) produtoService.findById(1);
        
        if(p != null){
            System.out.println("OK");
        } else {
            System.out.println("Not OK");
        }*/    
    }
}
