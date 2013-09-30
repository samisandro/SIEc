/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import br.com.caelum.stella.type.Estado;
import br.com.siec.api.factory.entity.ListEnumModel;
import br.com.siec.api.factory.util.cepwebservice.CepWebService;
import br.com.siec.config.jsf.ViewContext;

import br.com.siec.controller.resource.WebServiceCep;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.entity.Telefone;

import br.com.siec.model.persistence.interfaces.IEndereco;
import br.com.siec.model.persistence.interfaces.IPJ;
import br.com.siec.model.persistence.interfaces.IPf;
import br.com.siec.model.persistence.interfaces.ITelefone;
import br.com.siec.model.persistence.interfaces.IUsuario;
import br.com.siec.model.persistence.resource.Estados;
import br.com.siec.model.persistence.resource.TipoEndereco;
import br.com.siec.model.persistence.resource.TipoSexo;
import br.com.siec.model.persistence.resource.TipoTelefone;
import br.com.siec.model.persistence.resource.TipoUsuario;

import br.com.siec.service.ClienteService;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import javax.inject.Inject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @version 1.0.0 August 13, 2013.
 * @author Josimar Alves
 */
@ManagedBean(name = "clienteController")
@ViewScoped
public class ClienteController {

    @Inject
    @ClienteServiceQualifier
    private ClienteService clienteService;
    
    @Inject
    private ViewContext viewContext;
    
    @Inject
    private CepWebService cepWebService;
    
    private Cliente cliente;
    
    @Inject
    private IPJ pj;
    
    @Inject
    private IPf pf;
    
    @Inject
    private IUsuario user;
    
    @Inject
    private IEndereco endereco;
    
    @Inject
    private Telefone phonePrincipal;
    
    @Inject
    private Telefone phoneComercial;
    
    @Inject
    private Telefone phoneResidencial;
    
    private List<ITelefone> phones = new ArrayList<ITelefone>();
    
    @NotNull
    @Size(min = 6, max = 100)
    private String confirmPassword;
    
    private boolean typeCustomerPf;
    
    private static final String HOME = "home.jsf";
    private static final String INDEX = "index.jsf";
    private static final String UPDATE = "updateCustomer.jsf";

    @PostConstruct
    public void init() {
        /**
         * @Refatorar @Motivo: Colocar objetos demasiadamente na sessão pode
         * sobrecarregar o servidor.
         */
        cliente = viewContext.getObjectInSession("cliente");
        viewContext.removeObjectInSession("cliente");

        if (cliente == null) {
            cliente = clienteService.create("ClientePF");
            typeCustomerPf = true;
        }
    }

    public String save() {
        cliente.getUsuario().getPessoa().addEndereco(endereco);        
        cliente.getUsuario().getPessoa().addTelefone(phonePrincipal);
        cliente.getUsuario().getPessoa().addTelefone(phoneComercial);
        cliente.getUsuario().getPessoa().addTelefone(phoneResidencial);
        cliente.getUsuario().getPessoa().addEndereco(endereco);
        
        if (this.clienteService.save(cliente)) {
            viewContext.info("msg_info_saved");
            return INDEX;
        } else {
            viewContext.error("msg_error");
            return "";
        }
    }

    public String update() {
        if (this.clienteService.update(cliente)) {
            viewContext.info("msg_info_saved");
            return HOME;
        } else {
            viewContext.error("msg_error");
            return INDEX;
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

    public void addressLoad(AjaxBehaviorEvent event) {
        WebServiceCep webServiceCep = WebServiceCep.searchCep(getEndereco().getCep());

        if (webServiceCep.getBairro() != null) {
            getEndereco().setBairro(webServiceCep.getBairro());
        }
        if (webServiceCep.getLogradouro() != null) {
            getEndereco().setLogradouro(webServiceCep.getLogradouro());
        }
        if (webServiceCep.getCidade() != null) {
            getEndereco().setCidade(webServiceCep.getCidade());
        }
        if (webServiceCep.getUf() != null) {
            getEndereco().setEstado(Estados.valueOf(webServiceCep.getUf()));
        }
        
        System.out.println("WebService: "+webServiceCep.wasSuccessful());
        System.out.println("WebService: "+webServiceCep.getResultText());
    }

    public void confirmPassword(AjaxBehaviorEvent event) {
        if (!cliente.getUsuario().getSenha().equals(confirmPassword)) {
            viewContext.createError("msg_passwords_match");
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public IEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(IEndereco endereco) {
        this.endereco = endereco;
        this.endereco.setTipoEndereco(TipoEndereco.Principal);
    }

    public Telefone getPhonePrincipal() {
        return phonePrincipal;
    }

    public void setPhonePrincipal(Telefone phonePrincipal) {
        this.phonePrincipal = phonePrincipal;
        this.phonePrincipal.setTipo(TipoTelefone.PRINCIPAL);
//        cliente.getUsuario().getPessoa().addTelefone(this.phoneComercial);
    }

    public Telefone getPhoneComercial() {
        return phoneComercial;
    }

    public void setPhoneComercial(Telefone phoneComercial) {
        this.phoneComercial = phoneComercial;
        this.phoneComercial.setTipo(TipoTelefone.COMERCIAL);
//        cliente.getUsuario().getPessoa().addTelefone(this.phoneComercial);
    }

    public Telefone getPhoneResidencial() {
        return phoneResidencial;
    }

    public void setPhoneResidencial(Telefone phoneResidencial) {
        this.phoneResidencial = phoneResidencial;
        this.phoneResidencial.setTipo(TipoTelefone.RESIDENCIAL);
//        cliente.getUsuario().getPessoa().addTelefone(this.phoneComercial);
    }

    public List<ITelefone> getPhones() {
        return phones;
    }

    public void setPhones(List<ITelefone> phones) {
        this.phones = phones;
    }

    public List<SelectItem> getEstados() {
        List<SelectItem> selectEstados = new ArrayList<SelectItem>();
        List<Estados> estados = ListEnumModel.getEstados();

        for (Estados est : estados) {
            selectEstados.add(new SelectItem(est.name(), est.getDescricao()));
        }

        return selectEstados;
    }

    public List<SelectItem> getSexos() {
        List<SelectItem> selectSexos = new ArrayList<SelectItem>();

        selectSexos.add(new SelectItem(TipoSexo.F, TipoSexo.F.getDescricao()));
        selectSexos.add(new SelectItem(TipoSexo.M, TipoSexo.M.getDescricao()));

        return selectSexos;

    }

    public boolean isTypeCustomerPf() {
        return typeCustomerPf;
    }

    public void setTypeCustomerPf(boolean typeCustomerPf) {
        if (typeCustomerPf) {
            pf.getTelefones().addAll(pj.getTelefones());
            cliente.getUsuario().setPessoa(pf);
        } else {
            pj.getTelefones().addAll(pf.getTelefones());
            cliente.getUsuario().setPessoa(pj);
        }
        this.typeCustomerPf = typeCustomerPf;
    }

    public IPJ getPj() {
        return pj;
    }

    public void setPj(IPJ pj) {
        this.pj = pj;
    }

    public IPf getPf() {
        return pf;
    }

    public void setPf(IPf pf) {
        this.pf = pf;
    }

    public IUsuario getUser() {
        return user;
    }

    public void setUser(IUsuario user) {
        this.user = user;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
