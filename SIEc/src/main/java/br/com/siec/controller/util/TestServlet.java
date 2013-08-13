/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller.util;

import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.interfaces.Composite;
import br.com.siec.model.persistence.interfaces.IAcompanhamento;
import br.com.siec.model.persistence.interfaces.IComponente;
import br.com.siec.model.persistence.interfaces.IProduto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.TipoPreco;
import br.com.siec.service.Service;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ApplicationFactory;
import br.com.siec.api.factory.ClassType;
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
public class TestServlet extends HttpServlet {

    @Inject
    @ProdutoServiceQualifier
    private Service<Produto> produtoService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        AbstractFactory appFactory = ApplicationFactory.getInstance();
        AbstractFactory produtoFactory = appFactory.createObject(ClassType.ProdutoFactory);

        IComponente mussarela = produtoFactory.createObject(ClassType.Componente);
        IComponente calabresa = produtoFactory.createObject(ClassType.Componente);
        IComponente massa = produtoFactory.createObject(ClassType.Componente);
        IAcompanhamento refrigerante = produtoFactory.createObject(ClassType.Acompanhamento);
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
        
        produtoService.save((Produto) mussarela);

        calabresa.setCategoria(Categorias.Ingredientes);
        calabresa.setNome("Calabresa");
        calabresa.addPreco(precoP);
        calabresa.addPreco(precoM);
        calabresa.addPreco(precoG);
        calabresa.addPreco(precoF);
        
        produtoService.save((Produto) calabresa);

        massa.setCategoria(Categorias.Massa);
        massa.setNome("Massa");
        massa.addPreco(precoP);
        massa.addPreco(precoM);
        massa.addPreco(precoG);
        massa.addPreco(precoF);
        
        produtoService.save((Produto) massa);

        refrigerante.setCategoria(Categorias.Bebidas);
        refrigerante.setNome("Coca-Cola");
        refrigerante.addPreco(precoC);
        
        produtoService.save((Produto) refrigerante);

        pizza.setNome("Pizza Tradicional");
        pizza.addComponente(mussarela);
        pizza.addComponente(calabresa);
        pizza.addComponente(massa);
                
        //Composicao composicao = produtoService.findById(6L);
        //response.getWriter().write(composicao.toString());

        if (produtoService.save((Produto)pizza)) {
            System.out.println("OK");
        } else {
            System.out.println("Not OK");
        }
    }
}
