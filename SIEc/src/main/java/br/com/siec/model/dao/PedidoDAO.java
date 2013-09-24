/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.dao;

import br.com.siec.model.dao.core.DAO;
import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.resource.StatusPedido;
import br.com.siec.model.repository.Pedidos;
import java.util.List;
import javax.persistence.Query;

/**
 * <b>PedidoDAO</b>
 *
 * @version 1.0.0 August 15, 2013
 * @author Josimar Alves
 */
public class PedidoDAO
        extends DAO<Pedido> implements Pedidos {

    @Override
    public boolean save(Pedido pedido){
        return super.save(pedido);
    }
    
    @Override
    public boolean updateStatus(Long id, StatusPedido status){
        Pedido p = find(id);
        p.setStatus(status);
        return super.update(p);              
    }    
    
    @Override
    public List<Pedido> getLastOrders(int quantityOfOrders) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{getLastOrders(int quantityOfOrders)} Buscando os ["
                        + quantityOfOrders + "] ultimos Pedidos.");
            }

            Query query = super.getEntityManager().createQuery("SELECT p FROM Pedido p "
                    + "ORDER By p.dataCompra DESC ", Pedido.class);
            query.setMaxResults(quantityOfOrders);

            return query.getResultList();


        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getLastOrders(int quantityOfOrders) --> erro} Ocorreu um problema "
                        + "ao buscar os [" + quantityOfOrders + "] ultimos Pedidos. Exception -> " + e);
            }
            return null;
        }
    }

    @Override
    public List<Pedido> getOrderByStatus(StatusPedido status) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{getPedidoByStatus(StatusPedido status)} Buscando os Pedidos: [" + status + "].");
            }

            Query query = super.getEntityManager()
                    .createQuery("SELECT p FROM Pedido p"
                    + "WHERE p.status = :status ", Pedido.class)
                    .setParameter("status", status);

            return query.getResultList();

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getPedidoByStatus(StatusPedido status) --> erro} Ocorreu um problema "
                        + "ao buscar os Pedidos: [" + status + "]. Exception -> " + e);
            }
            return null;
        }
    }

    @Override
    public Long getQuantityOfOrders() {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{getQuantityOfOrders()} Verificando quantidade de pedidos recebidos.");
            }

            long quantityOfOrders = super.getEntityManager()
                    .createQuery("SELECT COUNT(p) FROM Pedido p ", Long.class).getSingleResult();

            return quantityOfOrders;

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getQuantityOfOrders() --> erro} Ocorreu uma erro ao verificar a "
                        + "quantidade de Pedidos recebidos. Exception: " + e);
            }
            return null;
        }

    }

    @Override
    public Double getValueOfOrdersByStatus(StatusPedido status) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{getValueOfOrdersByStatus(StatusPedido status)} Verificando o valor dos pedidos ["+status.getDescricao()+"].");
            }


            double valueOfPendingOrders = super.getEntityManager()
                    .createQuery("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.status = :status ", Double.class)
                    .setParameter("status", status) 
                    .getSingleResult();

            return valueOfPendingOrders;

        } catch (NullPointerException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getValueOfOrdersByStatus(StatusPedido status)} Não existe nenhum pedido ["+status.getDescricao()+"]");
            }
            return 0.0;
        }
    }
}
