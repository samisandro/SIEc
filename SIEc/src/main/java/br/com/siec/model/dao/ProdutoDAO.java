package br.com.siec.model.dao;

import br.com.siec.model.dao.core.DAO;
import br.com.siec.model.persistence.entity.Componente;
import br.com.siec.model.persistence.entity.Composicao;
import br.com.siec.model.persistence.entity.Produto;
import br.com.siec.model.persistence.interfaces.Composite;
import br.com.siec.model.persistence.resource.Categorias;
import br.com.siec.model.repository.Produtos;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * @version 1.00 May 14
 * @author Josimar
 */
public class ProdutoDAO
        extends DAO<Produto> implements Produtos {

    @Override
    public List<Produto> listProductWithOutComposition() {
        Query query = super.getEntityManager().createQuery("select p from Produto p "
                + "where p.categoria != :categoria", Produto.class);
        query.setParameter("categoria", Categorias.Composicao);
        return query.getResultList();
    }

    @Override
    public List<Produto> filterBy(String[] param, String[] atribute, boolean isWithComposition, boolean isExato) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{filterBy(String[] param, String[] atribute)} Filtrando Produtos: [" + param.toString() + "]");
            }

            Criteria criteria = getSession().createCriteria(Produto.class);

            for (int i = 0; i < param.length; i++) {
                if (isExato) {
                    if (atribute[i].equals("categoria")) {
                        criteria.add(Restrictions.eq(atribute[i], Categorias.valueOf(param[i])));
                    } else {
                        criteria.add(Restrictions.eq(atribute[i], param[i]));
                    }
                } else {
                    criteria.add(Restrictions.like(atribute[i], param[i], MatchMode.ANYWHERE));
                }
            }

            if (!isWithComposition) {
                criteria.add(Restrictions.not(Restrictions.eq("categoria", Categorias.Composicao.toString())));
            }

            criteria.addOrder(Order.asc("id"));

            return criteria.list();

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{filterBy(String[] param, String[] atribute)-> Erro} Filtrando Produtos: [" + e + "]");
            }
            return null;
        }

    }
    
    @Override
    public List<Produto> filterByCategory(Categorias categoria){
         Query query = super.getEntityManager().createQuery("select p from Produto p "
                + "where p.categoria = :categoria", Produto.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }

    @Override
    public List<Composicao> listComposite() {
        Query query = super.getEntityManager().createQuery("select c from Composicao c ", Composicao.class);
        return query.getResultList();
    }    

    @Override
    public List<Componente> filterComponenteByCategory(Categorias categoria) {
        Query query = super.getEntityManager().createQuery("select c from Componente c "
                + "where c.categoria = :categoria", Componente.class);
        query.setParameter("categoria", categoria);
        return query.getResultList();
    }
}
