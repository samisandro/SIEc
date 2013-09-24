/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.test;

import br.com.siec.model.persistence.entity.Acompanhamento;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.interfaces.Composite;
import br.com.siec.model.persistence.interfaces.IAcompanhamento;
import br.com.siec.model.persistence.interfaces.IComponente;
import br.com.siec.model.persistence.interfaces.IPreco;
import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.persistence.resource.TipoPreco;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ApplicationFactory;
import br.com.siec.api.factory.ClassType;

/**
 *
 * @author josimar
 */
public class FastTest {

    public static void main(String args[]) {
        
        AbstractFactory appFactory = ApplicationFactory.getInstance();
        AbstractFactory produtoFactory = appFactory.createObject(ClassType.ProdutoFactory);
        
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


        for (Preco preco : pizza.getPrecos()) {
            System.out.println("Pizza Tradicional - Tamanho:" + preco.getTipo().toString() + " Preco: " + preco.getValor());
        }
        for (Preco preco : refrigerante.getPrecos()) {
            System.out.println("Refrigerante:" + preco.getTipo().toString() + " Preco: " + preco.getValor());
        }

        refrigerante.addPreco(precoF);

        for (Preco preco : refrigerante.getPrecos()) {
            System.out.println("Refrigerante:" + preco.getTipo().toString() + " Preco: " + preco.getValor());
        }


    }
}