/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.entity.Imagem;
import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.entity.Preco;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.business.pricestrategy.MultiplePrice;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author josimar
 */
public interface IProduto {

    public long getId();

    public void setId(long id);

    public String getNome();

    public void setNome(String nome);

    public Categorias getCategoria();

    public void setCategoria(Categorias categoria);

    public Imagem getImagem();

    public void setImagem(Imagem imagem);

    public void addPedido(Pedido pedido);

    public Collection<IPedido> getPedidos();

    public void addPreco(Preco preco);

    public List<Preco> getPrecos();

    public MultiplePrice getTypePrice();

    public void setTypePrice(MultiplePrice typePrice);
}
