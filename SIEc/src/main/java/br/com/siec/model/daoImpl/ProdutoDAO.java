/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.daoImpl;

import br.com.siec.model.dao.IProdutoDAO;
import br.com.siec.model.persistence.entity.Produto;
import java.util.List;

/**
 *
 * @author josimar
 */
public class ProdutoDAO extends GenericDAOImpl<Produto> implements IProdutoDAO {
/*
    @Override
    public List<Produto> listAll() {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{ProdutoDAO - listAll()} Buscando todas as entidades: [Produto]");
            }
            return this.entityManager.createQuery("select p from Produto p join fetch p.produtos").getResultList();
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{ProdutoDAO - listAll() -> Erro} Erro na busca de todas as entidades: " + e);
            }
            return null;
        }

    }*/
}
