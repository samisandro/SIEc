package br.com.siec.facade;


import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.entity.Preco;

import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.persistence.resource.TipoPreco;

import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;

import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.qualifiers.ProdutoFactoryQualifier;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.repository.Produtos;

import br.com.siec.service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

/**
 * ProdutoService
 *
 * @version 1.00 04 August 2013
 * @author Josimar Alves
 */
@ProdutoServiceQualifier
public class ProdutoFacade implements ProdutoService {

    @Inject
    private Produtos produtoDAO;
    
    @Inject
    @ProdutoFactoryQualifier
    AbstractFactory produtoFactory;
    
    protected Logger logger = Logger.getLogger(ProdutoFacade.class);

    @Override
    public Produto create(String classType) {
        if (logger.isDebugEnabled()) {
            logger.debug("{create(String classType)} Instanciando Produto: " + classType);
        }
        try {
            return produtoFactory.createObject(ClassType.valueOf(classType));
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{create(String classType) -> Erro} Erro na instancia do Produto: " + e);
            }
            return null;
        }

    }

    @Override
    @Transacional
    public boolean save(Produto produto) {
        return produtoDAO.save(produto);
    }

    @Override
    @Transacional
    public boolean update(Produto produto) {
        return produtoDAO.update(produto);
    }

    @Override
    @Transacional
    public boolean delete(Produto produto) {
        return produtoDAO.delete(produto);
    }

    @Override
    @Transacional
    public List<Produto> listAll() {
        return produtoDAO.listProductWithOutComposition();
    }

    @Override
    @Transacional
    public List<Produto> search(String nome, String categoria) {
        String[] param = {nome, categoria};
        String[] atribute = {"nome", "categoria"};
        return produtoDAO.filterBy(param, atribute, false, false);
    }

    @Override
    @Transacional
    public Produto find(Long id) {
        return produtoDAO.find(id);
    }

    @Override
    @Transacional
    public List<Produto> listProductWithOutComposition() {
        return produtoDAO.listProductWithOutComposition();
    }

    @Override
    @Transacional
    public List<Produto> filterBy(String[] param, String[] atribute, boolean isWithComposition, boolean isExato) {
        return produtoDAO.filterBy(param, atribute, isWithComposition, isExato);
    }

    @Override
    public Produto convertTo(Produto p, String typeProduto) {
        Produto produtoChanged;

        if (typeProduto.equalsIgnoreCase("Acompanhamento")
                || typeProduto.equalsIgnoreCase("Bebidas")) {
            produtoChanged = create("Acompanhamento");
            produtoChanged.setNome(p.getNome());
            produtoChanged.setCategoria(Categorias.valueOf(typeProduto));
            produtoChanged.setImagem(p.getImagem());
            produtoChanged.setId(p.getId());
            for (Preco preco : p.getPrecos()) {
                produtoChanged.getPrecos().add(preco);
            }
            return produtoChanged;
        } else {
            produtoChanged = create("Componente");
            produtoChanged.setNome(p.getNome());
            produtoChanged.setCategoria(Categorias.valueOf(typeProduto));
            produtoChanged.setImagem(p.getImagem());
            produtoChanged.setId(p.getId());
            for (Preco preco : p.getPrecos()) {
                produtoChanged.getPrecos().add(preco);
            }
            return produtoChanged;
        }
    }

    @Override
    public List<Preco> createPryces(boolean isMultiplePryce) {
        List<Preco> precos = new ArrayList<Preco>();
        if (isMultiplePryce) {
            precos.add(new Preco(TipoPreco.PEQUENA, 0.0));
            precos.add(new Preco(TipoPreco.MEDIA, 0.0));
            precos.add(new Preco(TipoPreco.GRANDE, 0.0));
            precos.add(new Preco(TipoPreco.FAMILIA, 0.0));
        } else {
            precos.add(new Preco(TipoPreco.COMUM, 0.0));
        }

        return precos;

    }

    @Override
    public List<Produto> filterByCategory(Categorias categoria) {
        return produtoDAO.filterByCategory(categoria);
    }

    @Override
    public List<Composicao> listComposite() {
        return produtoDAO.listComposite();
    }

    @Override
    public List<Componente> filterComponenteByCategory(Categorias categoria) {
        return produtoDAO.filterComponenteByCategory(categoria);
    }
}
