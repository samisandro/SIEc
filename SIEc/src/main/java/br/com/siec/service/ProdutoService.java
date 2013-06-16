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
import java.util.List;
import javax.inject.Inject;

@ProdutoServiceQualifier
public class ProdutoService implements Service<Produto> {

    @Inject
    private IAcompanhamentoDAO acompanhamentoDao;
    @Inject
    private IComponenteDAO componenteDao;
    @Inject
    @ProdutoFactoryQualifier
    AbstractFactory produtoFactory;

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
    public List<Produto> listAll() {
        List<Acompanhamento> acompanhamentos = acompanhamentoDao.listAll();
        List<Componente> ingredientes = componenteDao.listAll();

        List<Produto> produtos = null;

        produtos.addAll(ingredientes);
        produtos.addAll(acompanhamentos);

        for (int i = 0; i <= produtos.size(); produtos.size()) {
            if (produtos.get(i).getCategoria().equals(Categorias.Composicao)) {
                produtos.remove(i);
            }

        }

        return produtos;
    }

    @Override
    public Produto validate(Produto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> findBy(String param, String atribute) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Produto findById(long id, String classType) {
        if ("Acompanhamento".equals(classType.getClass().getSimpleName())) {
            return (Produto) acompanhamentoDao.find(id);
        } else {
            return (Produto) componenteDao.find(id);
        }
    }

    @Transacional
    public Produto validate(Produto t, String classType) {
        if ("Acompanhamento".equals(classType.getClass().getSimpleName())) {
            return (Produto) acompanhamentoDao.validate((Acompanhamento) t);
        } else {
            return (Produto) componenteDao.validate((Componente) t);
        }
    }

    public List<? extends Produto> findBy(String param, String atribute, String classType) {
        if ("Acompanhamento".equals(classType.getClass().getSimpleName())) {
            return acompanhamentoDao.findBy(param, atribute);
        } else {
            return componenteDao.findBy(param, atribute);
        }
    }
}
