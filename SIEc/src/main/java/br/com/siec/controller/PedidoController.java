package br.com.siec.controller;

import br.com.siec.config.jsf.ViewContext;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.entity.Pedido;
import br.com.siec.model.persistence.interfaces.IPedido;

import br.com.siec.service.PedidoService;
import br.com.siec.service.qualifiers.PedidoServiceQualifier;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.inject.Inject;

/**
 *
 * @author Josimar Alves
 */
@ManagedBean(name = "pedidoController")
@ViewScoped
public class PedidoController implements Serializable{
    
    @Inject
    @PedidoServiceQualifier
    private PedidoService pedidoService;
    
    private Pedido pedido;    
    
    private Cliente cliente;
    
    private List<IPedido> pedidos;
    
    @Inject
    private ViewContext viewContext;

    public PedidoController() {
    }
    
    @PostConstruct
    public void init(){
         pedido = viewContext.getObjectInSession("pedido");
        viewContext.removeObjectInSession("pedido");
        
        if (pedido == null) {
            pedido = new Pedido();            
        }
    }
    
    public String save(){
        if(pedidoService.save(pedido)){
            viewContext.info("");
            return "index.jsf";
        } else {
            viewContext.info("");
            return "index.jsf";
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<IPedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<IPedido> pedidos) {
        this.pedidos = pedidos;
    }    

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    
}
