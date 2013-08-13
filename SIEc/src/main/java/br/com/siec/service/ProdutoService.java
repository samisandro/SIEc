package br.com.siec.service;

import br.com.siec.model.dao.IProdutoDAO;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.ProdutoServiceQualifier;
import br.com.siec.api.factory.AbstractFactory;
import br.com.siec.api.factory.ClassType;
import br.com.siec.api.factory.qualifiers.ProdutoFactoryQualifier;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.jboss.logging.Logger;


/**
 * ProdutoService
 *
 * @version 1.00 04 August 2013
 * @author Josimar Alves
 */
@ProdutoServiceQualifier
public class ProdutoService implements Service<Produto> {

    @Inject
    private IProdutoDAO produtoDAO;
    @Inject
    @ProdutoFactoryQualifier
    AbstractFactory produtoFactory;
    protected Logger logger = Logger.getLogger(ProdutoService.class);

    @Override
    public Produto create(String classType) {
        try {
            return produtoFactory.createObject(ClassType.valueOf(classType));
        } catch (Exception e){
            System.out.println("Service Error: "+e);
            return null;
        }
        
    }

    @Override
    @Transacional
    public boolean save(Produto t) {
        return produtoDAO.salve(t);
    }

    @Override
    @Transacional
    public boolean update(Produto t) {
        return produtoDAO.update(t);
    }

    @Override
    @Transacional
    public boolean delete(Produto t) {
        return produtoDAO.delete(t);
    }

    @Override
    public Produto findById(long id) {
        return produtoDAO.find(id);
    }

    @Override
    @Transacional
    public List<Produto> listAll() {
        List<Produto> produtos = produtoDAO.listAll();

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCategoria().equals(Categorias.Composicao)) {
                produtos.remove(i);
            }

        }

        return produtos;
    }

    @Override
    public List<Produto> findBy(String param, String atribute) {
        List<Produto> produtos = new ArrayList<Produto>();

        if (produtos != null) {
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getCategoria().equals(Categorias.Composicao)) {
                    produtos.remove(i);
                }
            }
        }

        return produtos;
    }

    @Transacional
    @Override
    public Produto validate(Produto t) {
        return produtoDAO.validate((Produto)t);
    }
}
