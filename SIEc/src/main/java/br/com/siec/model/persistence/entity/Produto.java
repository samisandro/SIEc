package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.model.persistence.util.TamanhoPizza;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public interface Produto extends Serializable {

    public long getId();

    public void setId(long id);

    public String getNome();

    public void setNome(String nome);

    public void addPreco(String chave, double preco);

    public HashMap<String, Double> getPrecos();

    public Categorias getCategoria();

    public void setCategoria(Categorias categoria);

    public byte[] getImagem();

    public void setImagem(byte[] imagem);

    public void addComponente(Produto componente);

    public void removeComponente(Produto componente);

    public Produto getComponente(int i);
}
