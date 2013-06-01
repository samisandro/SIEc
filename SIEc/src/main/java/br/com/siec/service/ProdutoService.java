package br.com.siec.service;

import br.com.siec.model.persistence.dao.IGenericDAO;
import br.com.siec.model.persistence.entity.Produto;

public class ProdutoService extends Service<Produto> implements IProdutoService {

    public ProdutoService(IGenericDAO dao) {
        super(dao);
    }
}
