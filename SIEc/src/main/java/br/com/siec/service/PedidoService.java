/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service;

import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.resource.StatusPedido;
import java.util.Date;
import java.util.List;

/**
 * <b>PedidoService</b>
 *
 * @version 1.0.0 August 10, 2013.
 * @author Josimar Alves
 */
public interface PedidoService extends Service<Pedido> {
    
    public List<Pedido> getLastOrders(int quantityOfOrders);
    
    public List<Pedido> getOrderByStatus(StatusPedido status);
    
    public Long getQuantityOfOrders();
    
    public Long getQuantityOfOrders(Date dayIni, Date dayFim, StatusPedido status);
    
    public Double getValueOfOrdersByStatus(StatusPedido status);        
    
    public Double getValueOfOrdersByStatus(StatusPedido status, Date dayIni, Date dayFim);
    
}
