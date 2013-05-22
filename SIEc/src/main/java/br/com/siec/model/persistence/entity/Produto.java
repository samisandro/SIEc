package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.Categorias;
import java.util.Map;

public interface Produto {

    public long getId();

    public void setId(long id);

    public String getNome();

    public void setNome(String nome);

    public void addPreço(String chave, double preco);

    public Map<String, Double> getPrecos();

    public Categorias getCategoria();

    public void setCategoria(Categorias categoria);

    public byte[] getImagem();

    public void setImagem(byte[] imagem);

    public void addComponente(Componente componente);

    public void removeComponente(Componente componente);

    public void getComponente(int i);
}
