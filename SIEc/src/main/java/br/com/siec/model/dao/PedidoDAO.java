/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.dao;

import br.com.siec.model.dao.core.DAO;
import br.com.siec.model.persistence.entity.Item;
import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.resource.StatusPedido;
import br.com.siec.model.repository.Pedidos;
import java.util.ArrayList;
import java.util.Date;
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
    public boolean save(Pedido pedido) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(Pedido pedido)} Salvando Pedido");
            }
            List<Item> itens = new ArrayList<Item>();

            for (Item item : pedido.getItens()) {
                item.setPedido(pedido);
                itens.add(super.validate(item));
            }

            pedido.getCliente().addPedido(pedido);

            pedido.getItens().addAll(itens);

            super.save(pedido);

            return true;

        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{save(Pedido pedido) --> erro } Ocorreu um problema ao tentar salvar o Pedido. -> Exception: " + e);
            }
            return false;
        }
    }

    @Override
    public boolean updateStatus(Pedido pedido) {
        return super.update(pedido);
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
                logger.debug("{getValueOfOrdersByStatus(StatusPedido status)} Verificando o valor dos pedidos [" + status.getDescricao() + "].");
            }


            double valueOfPendingOrders = super.getEntityManager()
                    .createQuery("SELECT SUM(p.valorTotal) FROM Pedido p WHERE p.status = :status ", Double.class)
                    .setParameter("status", status)
                    .getSingleResult();

            return valueOfPendingOrders;

        } catch (NullPointerException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getValueOfOrdersByStatus(StatusPedido status)} Não existe nenhum pedido [" + status.getDescricao() + "]");
            }
            return 0.0;
        }
    }

    @Override
    public Long getQuantityOfOrders(Date dateIni, Date dateFim, StatusPedido status) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{getQuantityOfOrders(Date today)} Verificando quantidade de pedidos recebidos hoje.");
            }

            Query query = super.getEntityManager().createQuery(getQuantityOfOrdersQuery(dateIni, dateFim, status), Long.class);

            boolean withDate = dateIni != null && dateFim != null;

            if (withDate) {
                query.setParameter("dateIni", dateIni);
                query.setParameter("dateFim", dateFim);
            }

            if (status != null) {
                query.setParameter("status", status);
            }

            return (Long) query.getSingleResult();

        } catch (NullPointerException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getQuantityOfOrders(Date dateIni, Date dateFim) --> erro} Nenhum pedido");
            }
            return 0L;
        }
    }

    private String getQuantityOfOrdersQuery(Date dateIni, Date dateFim, StatusPedido status) {
        StringBuilder query = new StringBuilder();
        boolean withDate = dateIni != null && dateFim != null;

        query.append("SELECT COUNT(p) FROM Pedido p ");

        if (withDate) {
            query.append(" WHERE p.dataCompra BETWEEN :dateIni AND :dateFim ");
        }

        if (withDate && status != null) {
            query.append(" AND p.status = :status ");
            return query.toString();
        }
        
        if (status != null) {
            query.append(" WHERE p.status = :status ");
            return query.toString();
        }

        return query.toString();

    }

    @Override
    public Double getValueOfOrdersByStatus(StatusPedido status, Date dateIni, Date dateFim) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("{getValueOfOrdersByStatus(StatusPedido status, Date dateIni, Date dateFim))} Verificando o valor dos pedidos [" + status.getDescricao() + "].");
            }


            double valueOfPendingOrders = super.getEntityManager()
                    .createQuery("SELECT SUM(p.valorTotal) "
                    + "  FROM Pedido p "
                    + " WHERE p.status = :status "
                    + "   AND p.dataCompra BETWEEN :dateIni AND :dateFim ", Double.class)
                    .setParameter("status", status)
                    .setParameter("dateIni", dateIni)
                    .setParameter("dateFim", dateFim)
                    .getSingleResult();

            return valueOfPendingOrders;

        } catch (NullPointerException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("{getValueOfOrdersByStatus(StatusPedido status, Date dateIni, Date dateFim))} Não existe nenhum pedido [" + status.getDescricao() + "]");
            }
            return 0.0;
        }
    }
}
