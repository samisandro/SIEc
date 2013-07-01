/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.control.beans.util;

import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.StatusPedido;
import br.com.siec.model.persistence.util.TamanhoPizza;
import br.com.siec.service.Service;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ApplicationFactory;
import br.com.siec.util.factory.ClassType;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josimar
 */
@WebServlet("/apptest/*")
public class AppTestServlet extends HttpServlet {

    @Inject
    @ProdutoServiceQualifier
    private Service<Produto> produtoService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        AbstractFactory appFactory = ApplicationFactory.getInstance();
        AbstractFactory produtoFactory = appFactory.createObject(ClassType.ProdutoFactory);

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
        mussarella.setProduto(pizza);
        presunto.setCategoria(Categorias.Ingredientes);
        presunto.setNome("Presunto");
        presunto.addPreco(TamanhoPizza.Pequena.toString(), 1.99);
        presunto.addPreco(TamanhoPizza.Média.toString(), 1.99);
        presunto.addPreco(TamanhoPizza.Grande.toString(), 1.99);
        presunto.addPreco(TamanhoPizza.Família.toString(), 1.99);
        presunto.setProduto(pizza);
        massa.setCategoria(Categorias.Massa);
        massa.setNome("Massa Recheada");
        massa.addPreco(TamanhoPizza.Pequena.toString(), 1.99);
        massa.addPreco(TamanhoPizza.Média.toString(), 1.99);
        massa.addPreco(TamanhoPizza.Grande.toString(), 1.99);
        massa.addPreco(TamanhoPizza.Família.toString(), 1.99);
        massa.setProduto(pizza);
        refrigerante.setCategoria(Categorias.Bebidas);
        refrigerante.setNome("Coca-Cola");
        refrigerante.addPreco("Bebida", 4.99);
        produtoService.save(refrigerante);

        pizza.setNome("Pizza Presunto Mussarella");
        pizza.setCategoria(Categorias.Composicao);
        pizza.addComponente(mussarella);
        pizza.addComponente(presunto);
        pizza.addComponente(massa);

        /*
         Produto produto = produtoService.findById(5, "Composicao");
        
         produto.addPreco("Família", 6.5);
         produto.addPreco("Grande", 5.5);
         produto.addPreco("Média", 4.5);
         produto.addPreco("Pequena", 3.5);
         */

        if (produtoService.save(pizza)) {
            System.out.println("OK");
        } else {
            System.out.println("Not OK");
        }
    }
}
