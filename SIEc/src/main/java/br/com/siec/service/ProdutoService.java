/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.repository.Produtos;
import java.util.List;

/**
 *
 * @author josimar
 */
public interface ProdutoService extends Produtos{
    
    public Produto create(String classType);
    public Produto convertTo(Produto p, String typeProduto);
    public List<Preco> createPryces(boolean isMultiplePryce);
    public List<Produto> search(String nome, String categoria);
}
