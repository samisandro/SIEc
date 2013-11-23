/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.repository;

import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.resource.StatusPedido;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0.0 August 10, 2013.
 * @author Josimar
 */
public interface Pedidos {
    
   public boolean save(Pedido pedido);
   
   public boolean updateStatus(Pedido pedido);
   
   public List<Pedido> listAll();
    
   public List<Pedido> getLastOrders(int quantityOfOrders);
    
   public List<Pedido> getOrderByStatus(StatusPedido status);
   
   public Long getQuantityOfOrders();
   
   public Double getValueOfOrdersByStatus(StatusPedido status);
   
   public Long getQuantityOfOrders(Date dayIni, Date dayFim, StatusPedido status);
   
   public Double getValueOfOrdersByStatus(StatusPedido status, Date dayIni, Date dayFim);
    
}
