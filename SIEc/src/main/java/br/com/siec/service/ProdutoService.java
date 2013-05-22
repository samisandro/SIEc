package br.com.siec.service;

import br.com.siec.model.persistence.entity.Produto;

public class ProdutoService implements IProdutoService {

    @Override
    public boolean saveProduto(Produto produto) {
        return false;

    }

    @Override
    public boolean updateProduto(Produto produto) {
        return false;

    }

    @Override
    public Produto validateProduto() {
        return null;

    }

    /**
     * @see
     * br.com.siec.service.IProdutoService#removeProduto(br.com.siec.model.persistence.entity.Produto)
     */
    @Override
    public boolean removeProduto(Produto produto) {
        return false;
    }
}
