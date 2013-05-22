package br.com.siec.service;

import br.com.siec.model.persistence.entity.Produto;

public interface IProdutoService {
 
	public boolean saveProduto(Produto produto);
	public boolean updateProduto(Produto produto);
	public boolean removeProduto(Produto produto);
	public Produto validateProduto();
}
 
