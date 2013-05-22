package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.Categorias;
import java.util.Map;

public class Acompanhamento implements Produto {

    private long id;
    private String nome;
    private Map<String, Double> precos;
    private Categorias categoria;
    private byte[] imagem;

    public void Acompanhamento(String nome,
            Map<String, Double> precos,
            Categorias categoria,
            byte[] imagem) {
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {
    }

    @Override
    public String getNome() {
        return null;
    }

    @Override
    public void setNome(String nome) {
    }

    @Override
    public void addPreço(String chave, double preco) {
    }

    @Override
    public Map<String, Double> getPrecos() {
        return null;
    }

    @Override
    public Categorias getCategoria() {
        return null;
    }

    @Override
    public void setCategoria(Categorias categoria) {
    }

    @Override
    public byte[] getImagem() {
        return null;
    }

    /**
     *
     * @param imagem
     */
    @Override
    public void setImagem(byte[] imagem) {
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#addComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void addComponente(Componente componente) {
    }

    /**
     * @see
     * br.com.siec.model.persistence.entity.Produto#removeComponente(br.com.siec.model.persistence.entity.Componente)
     */
    @Override
    public void removeComponente(Componente componente) {
    }

    /**
     * @see br.com.siec.model.persistence.entity.Produto#getComponente(int)
     */
    @Override
    public void getComponente(int i) {
    }
}
