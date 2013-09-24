/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.repository;

import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.resource.Categorias;
import java.util.List;

/**
 *
 * @author Josimar
 */
public interface Produtos {

    public boolean save(Produto produto);

    public boolean delete(Produto produto);

    public Produto find(Long id);

    public boolean update(Produto produto);

    public List<Produto> listAll();
    
    public List<Composicao> listComposite();

    public List<Produto> listProductWithOutComposition();

    public List<Produto> filterBy(String[] param, String[] atribute, boolean isWithComposition, boolean isExato);
    
    public List<Produto> filterByCategory(Categorias categoria);
    
    public List<Componente> filterComponenteByCategory(Categorias categoria);
    
}
