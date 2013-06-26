package br.com.siec.service;

import br.com.siec.model.persistence.dao.IAcompanhamentoDAO;
import br.com.siec.model.persistence.dao.IComponenteDAO;
import br.com.siec.model.persistence.entity.Acompanhamento;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ClassType;
import br.com.siec.util.factory.qualifiers.ProdutoFactoryQualifier;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.Logger;

@ProdutoServiceQualifier
public class ProdutoService implements Service<Produto> {

    @Inject
    private IAcompanhamentoDAO acompanhamentoDao;
    @Inject
    private IComponenteDAO componenteDao;
    @Inject
    @ProdutoFactoryQualifier
    AbstractFactory produtoFactory;
    
    protected Logger logger = Logger.getLogger(ProdutoService.class);

    @Override
    public Produto create(String classType) {
        return produtoFactory.createObject(ClassType.valueOf(classType));
    }

    @Override
    @Transacional
    public boolean save(Produto t) {
        if ("Acompanhamento".equals(t.getClass().getSimpleName())) {
            return acompanhamentoDao.salve((Acompanhamento) t);
        } else {
            return componenteDao.salve((Componente) t);
        }
    }

    @Override
    @Transacional
    public boolean update(Produto t) {
        if ("Acompanhamento".equals(t.getClass().getSimpleName())) {
            return acompanhamentoDao.update((Acompanhamento) t);
        } else {
            return componenteDao.update((Componente) t);
        }
    }

    @Override
    @Transacional
    public boolean delete(Produto t) {
        if ("Acompanhamento".equals(t.getClass().getSimpleName())) {
            return acompanhamentoDao.delete((Acompanhamento) t);
        } else {
            return componenteDao.delete((Componente) t);
        }
    }

    @Override
    public Produto findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transacional
    public List<Produto> listAll() {
        List<Acompanhamento> acompanhamentos = acompanhamentoDao.listAll();
        List<Componente> ingredientes = componenteDao.listAll();

        List<Produto> produtos = new ArrayList<Produto>();

        produtos.addAll(ingredientes);
        produtos.addAll(acompanhamentos);

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCategoria().equals(Categorias.Composicao)) {
                produtos.remove(i);
            }

        }

        return produtos;
    }

    @Override
    public List<Produto> findBy(String param, String atribute) {
        List<Acompanhamento> acompanhamentos = acompanhamentoDao.findBy(param, atribute);
        List<Componente> ingredientes = componenteDao.findBy(param, atribute);
        List<Produto> produtos = new ArrayList<Produto>();

        if (ingredientes != null) {
            produtos.addAll(ingredientes);
        }

        if (acompanhamentos != null) {
            produtos.addAll(acompanhamentos);
        }

        if (produtos != null) {
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getCategoria().equals(Categorias.Composicao)) {
                    produtos.remove(i);
                }
            }
        }

        return produtos;
    }

    @Override
    public Produto findById(long id, String classType) {
        if (logger.isDebugEnabled()) {
            logger.debug("{findById(long id, String classType)} Buscando Produto: Id [" + id + "] - Classe [" + classType + "]");
        }
        if (("Acompanhamento".equals(classType))
                || ("Bebidas".equals(classType))) {
            return (Produto) acompanhamentoDao.find(id);
        } else {
            return (Produto) componenteDao.find(id);
        }
    }

    @Transacional
    @Override
    public Produto validate(Produto t) {
        if ("Acompanhamento".equals(t.getClass().getSimpleName())) {
            return (Produto) acompanhamentoDao.validate((Acompanhamento) t);
        } else {
            return (Produto) componenteDao.validate((Componente) t);
        }
    }

    @Override
    public List<Produto> findBy(String param, String atribute, String classType) {
        if (logger.isDebugEnabled()) {
            logger.debug("{findById(long id, String classType)} Buscando Produto por:  [" + atribute + "] - Parametro ["+param+"] - Classe [" + classType + "]");
        }
        if (("Acompanhamento".equals(classType))
                || ("Bebidas".equals(classType))) {
            return (List<Produto>) (Produto) acompanhamentoDao.findBy(param, atribute);
        } else {
            return (List<Produto>) (Produto) componenteDao.findBy(param, atribute);
        }
    }
}
