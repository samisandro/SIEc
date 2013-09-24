/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import br.com.siec.config.jsf.ViewContext;
import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.service.ClienteService;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author josimar
 */
@ManagedBean
@ViewScoped
public class ClienteController {
    
    @Inject
    @ClienteServiceQualifier
    private ClienteService clienteService;
    
    @Inject
    private ViewContext viewContext;
    
    @Inject
    private Cliente cliente;
    
    private List<Cliente> clientes;

    public String save() {
        if (this.clienteService.save(cliente)) {
            viewContext.info("msg_info_saved");
            return "faces?redirect=true";
        } else {
            viewContext.error("msg_error");
            return "";
        }
    }

    public String update() {
        if (this.clienteService.update(cliente)) {
            viewContext.info("msg_info_saved");
            return "faces?redirect=true";
        } else {
            viewContext.error("msg_error");
            return "";
        }
    }

    public String delete() {
        if (this.clienteService.delete(cliente)) {
            viewContext.info("msg_info_saved");
            return "faces?redirect=true";
        } else {
            viewContext.error("msg_error");
            return "";
        }
    }
    
    public List<Cliente> listAll() {
        if (clientes == null) {
            clientes = clienteService.listAll();
            return clientes;
        } else {
            return null;
        }
    }
    
    public List<Cliente> search(){
        if (this.clientes != null) {
            this.clientes.clear();
        }
        return clientes = clienteService.findBy("", "nome");
    }
    
}
