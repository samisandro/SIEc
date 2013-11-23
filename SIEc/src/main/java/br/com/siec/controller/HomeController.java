/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.entity.Usuario;
import br.com.siec.model.persistence.resource.StatusPedido;

import br.com.siec.service.UsuarioService;
import br.com.siec.service.ClienteService;
import br.com.siec.service.PedidoService;

import br.com.siec.service.qualifiers.ClienteServiceQualifier;
import br.com.siec.service.qualifiers.PedidoServiceQualifier;
import br.com.siec.service.qualifiers.UsuarioServiceQualifier;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.inject.Inject;

/**
 * <b> HomeController <b> carrega as informações
 * para o painel inicial do Admin.
 * @version 1.0.0 15 September, 2013.
 * @author Josimar
 */

@ManagedBean(name="homeController")
@SessionScoped
public class HomeController implements Serializable{
    
    @Inject
    @UsuarioServiceQualifier
    private UsuarioService userService;
    
    @Inject
    @ClienteServiceQualifier
    private ClienteService customerService;
    
    @Inject
    @PedidoServiceQualifier
    private PedidoService orderService;
    
    public List<Usuario> lastUsers(){
        return userService.getLastUsers(5);
    }
    
    public long numberOfClients(){
        return customerService.getQuantityOfClients();
    }
    
    public long numberOfOrders(){
        return orderService.getQuantityOfOrders(new Date(), new Date(), null);
    }
    
    public long numberOfPaidOrders(){
        return orderService.getQuantityOfOrders(new Date(), new Date(), StatusPedido.Faturado);
    }
    
    public long numberOfPreparingOrders(){
        return orderService.getQuantityOfOrders(new Date(), new Date(), StatusPedido.SendoPreparado);
    }
    
    public long numberOfShippedOrders(){
        return orderService.getQuantityOfOrders(new Date(), new Date(), StatusPedido.SaiuparaEntrega);
    }
    
    public long numberOfDeliveredOrders(){
        return orderService.getQuantityOfOrders(new Date(), new Date(), StatusPedido.Entregue);
    }
    
    public double valueOfPendingOrders(){
        return orderService.getValueOfOrdersByStatus(StatusPedido.AguardandoPagamento, new Date(), new Date());
    }
    
    public double valueOfPaidOrders(){
        return orderService.getValueOfOrdersByStatus(StatusPedido.Faturado, new Date(), new Date());
    }
    
    public List<Pedido> lastOrders(){
        return orderService.getLastOrders(4);
    }
    
    public Date getToday(){
        return new Date();
    }
    
    
}
