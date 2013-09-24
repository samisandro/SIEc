/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.facade;

import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.resource.StatusPedido;
import br.com.siec.model.repository.Pedidos;
import br.com.siec.service.PedidoService;
import br.com.siec.service.qualifiers.PedidoServiceQualifier;
import java.util.List;
import javax.inject.Inject;

/**
 * <b>PedidoFacade</b>
 * @version 1.0.0 August 10, 2013.
 * @author Josimar Alves
 */
@PedidoServiceQualifier
public class PedidoFacade implements PedidoService{
    
    @Inject
    private Pedidos pedidoDAO;
    

    @Override
    public boolean save(Pedido pedido) {
        return pedidoDAO.save(pedido);
    }

    @Override
    public boolean updateStatus(Long id, StatusPedido status) {
        return pedidoDAO.updateStatus(id, status);
    }

    @Override
    public List<Pedido> getLastOrders(int quantityOfOrders) {
        return pedidoDAO.getLastOrders(quantityOfOrders);
    }

    @Override
    public List<Pedido> getOrderByStatus(StatusPedido status) {
        return pedidoDAO.getOrderByStatus(status);
    }

    @Override
    public Long getQuantityOfOrders() {
        return pedidoDAO.getQuantityOfOrders();
    }

    @Override
    public Double getValueOfOrdersByStatus(StatusPedido status) {
        return pedidoDAO.getValueOfOrdersByStatus(status);
    }
    
}
