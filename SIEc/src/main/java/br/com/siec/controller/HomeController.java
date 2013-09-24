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
    private UsuarioService userFacade;
    
    @Inject
    @ClienteServiceQualifier
    private ClienteService clienteFacada;
    
    @Inject
    @PedidoServiceQualifier
    private PedidoService pedidoFacade;
    
    public List<Usuario> lastUsers(){
        return userFacade.getLastUsers(5);
    }
    
    public long numberOfClients(){
        return clienteFacada.getQuantityOfClients();
    }
    
    public long numberOfOrders(){
        return pedidoFacade.getQuantityOfOrders();
    }
    
    public double valueOfPendingOrders(){
        return pedidoFacade.getValueOfOrdersByStatus(StatusPedido.AguardandoPagamento);
    }
    
    public double valueOfPaidOrders(){
        return pedidoFacade.getValueOfOrdersByStatus(StatusPedido.Faturado);
    }
    
    public List<Pedido> lastOrders(){
        return pedidoFacade.getLastOrders(4);
    }
    
    
}
