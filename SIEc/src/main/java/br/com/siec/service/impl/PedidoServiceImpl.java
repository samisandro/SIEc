/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.service.impl;

import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.resource.StatusPedido;
import br.com.siec.model.repository.Pedidos;
import br.com.siec.resource.notification.Notification;
import br.com.siec.resource.notification.NotificationType;
import br.com.siec.resource.notification.qualifiers.CustomerNotificationQualifier;
import br.com.siec.service.PedidoService;
import br.com.siec.service.interceptors.Transacional;
import br.com.siec.service.qualifiers.PedidoServiceQualifier;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletRequest;

/**
 * <b>PedidoServiceImpl</b>
 * @version 1.0.0 August 10, 2013.
 * @author Josimar Alves
 */
@PedidoServiceQualifier
public class PedidoServiceImpl implements PedidoService{
    
    @Inject
    private Pedidos pedidoRepository;
    
    @Inject
    private ServletRequest servletRequest;
    
    @Inject
    @CustomerNotificationQualifier
    private Notification customerNotification;    

    @Override
    @Transacional
    public boolean save(Pedido pedido) {        
        customerNotification.sendNotification(pedido.getCliente(), NotificationType.SALE_ORDER, servletRequest);
        return pedidoRepository.save(pedido);
    }

    @Override
    @Transacional
    public boolean update(Pedido pedido) {
        customerNotification.sendNotification(pedido.getCliente(), NotificationType.SHIPPMENT_ORDER, servletRequest);
        return pedidoRepository.updateStatus(pedido);
    }
    
    
    @Override
    public Pedido create(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> getLastOrders(int quantityOfOrders) {
        return pedidoRepository.getLastOrders(quantityOfOrders);
    }

    @Override
    public List<Pedido> getOrderByStatus(StatusPedido status) {
        return pedidoRepository.getOrderByStatus(status);
    }

    @Override
    public Long getQuantityOfOrders() {
        return pedidoRepository.getQuantityOfOrders();
    }

    @Override
    public Double getValueOfOrdersByStatus(StatusPedido status) {
        return pedidoRepository.getValueOfOrdersByStatus(status);
    }

    @Override
    public Long getQuantityOfOrders(Date dayIni, Date dayFim, StatusPedido status) {
        return pedidoRepository.getQuantityOfOrders(dayIni, dayFim, status);
    }

    @Override
    public Double getValueOfOrdersByStatus(StatusPedido status, Date dayIni, Date dayFim) {
        return pedidoRepository.getValueOfOrdersByStatus(status, dayIni, dayFim);
    }
    
    @Override
    public List<Pedido> listAll(){
        return pedidoRepository.listAll();
    }
    
}
