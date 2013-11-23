/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.entity.Produto;

import br.com.siec.model.persistence.resource.Categorias;

import java.util.List;

/**
 * @version 1.0.0 May 07, 2013.
 * @author Josimar Alves
 */
public interface ProdutoService extends Service<Produto> {
    
    public Produto convertTo(Produto p, String typeProduto);
    
    public List<Preco> createPryces(boolean isMultiplePryce);
    
    public List<Produto> search(String nome, String categoria);
    
    public boolean delete(Produto produto);
    
    public List<Produto> listProductWithOutComposition();
    
    public List<Produto> filterBy(String[] param, String[] atribute, boolean isWithComposition, boolean isExato);
    
    public Produto validate(Produto produto);
    
    public List<Produto> filterByCategory(Categorias categoria);
    
    public List<Composicao> listComposite();
    
    public List<Componente> filterComponenteByCategory(Categorias categoria);
    
    public List<Produto> getTopSellingProducts(int quantity);
    
    public List<Produto> getProductsSoldLess(int quantity);
}
