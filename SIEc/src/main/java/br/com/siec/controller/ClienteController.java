package br.com.siec.controller;

import br.com.siec.factory.entity.ListEnumModel;

import br.com.siec.business.recommendation.IRecomendacao;

import br.com.siec.config.jsf.ViewContext;

import br.com.siec.controller.resource.BuscaEnderecoWS;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.model.persistence.entity.Produto;

import br.com.siec.model.persistence.interfaces.IEndereco;
import br.com.siec.model.persistence.interfaces.IPJ;
import br.com.siec.model.persistence.interfaces.IPerfil;
import br.com.siec.model.persistence.interfaces.IPf;
import br.com.siec.model.persistence.interfaces.ITelefone;
import br.com.siec.model.persistence.interfaces.IUsuario;

import br.com.siec.model.persistence.resource.Estados;
import br.com.siec.model.persistence.resource.TipoEndereco;
import br.com.siec.model.persistence.resource.TipoSexo;
import br.com.siec.model.persistence.resource.TipoTelefone;

import br.com.siec.service.ClienteService;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;

import java.io.Serializable;

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
public class ClienteController implements Serializable{

    @Inject
    @ClienteServiceQualifier
    private ClienteService clienteService;
    
    @Inject
    private ViewContext viewContext;
    
    private Cliente cliente;
    
    @Inject
    private IRecomendacao recomendar;
    
    @Inject
    private IPerfil perfil;
    
    @Inject
    private IPJ pj;
    
    @Inject
    private IPf pf;
    
    @Inject
    private IUsuario user;
    
    @Inject
    private IEndereco endereco;
    
    @Inject
    private ITelefone phonePrincipal;
    
    @Inject
    private ITelefone phoneComercial;
    
    @Inject
    private ITelefone phoneResidencial;
    
    private List<ITelefone> phones = new ArrayList<ITelefone>();
    
    @NotNull
    @Size(min = 6, max = 100)
    private String confirmPassword;
    
    private boolean typeCustomerPf;
    
    private static final String HOME = "home.jsf";
    private static final String INDEX = "index.jsf";

    @PostConstruct
    public void init() {
        /**
         * @Refatorar 
         * @Motivo: Colocar objetos demasiadamente na sessão pode
         * sobrecarregar o servidor.
         */
        cliente = viewContext.getObjectInSession("cliente");
        viewContext.removeObjectInSession("cliente");

        if (cliente == null) {
            cliente = clienteService.create("ClientePF");
            typeCustomerPf = true;
        } else {
            prepareForUpdate();
        }
    }

    public String save() {
        prepareForSave();
        cliente.setPerfil(perfil);

        if (this.clienteService.save(cliente)) {
            viewContext.info("msg_info_saved");
            return INDEX;
        } else {
            viewContext.error("msg_error");
            return "";
        }
    }

    public String update() {
        
        this.cliente.getUsuario().getPessoa().getEnderecos().clear();
        this.cliente.getUsuario().getPessoa().getTelefones().clear();
        prepareForSave();
        System.out.println("Endereco: "+this.cliente.getUsuario().getPessoa().getEnderecos().get(0).getId());
        if (this.clienteService.update(this.cliente)) {
            viewContext.info("msg_info_saved");
            return HOME;
        } else {
            viewContext.error("msg_error");
            return INDEX;
        }
    }

    public void addressLoad(AjaxBehaviorEvent event) {
        BuscaEnderecoWS webServiceCep = BuscaEnderecoWS.searchCep(getEndereco().getCep());

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
    }

    public void confirmPassword(AjaxBehaviorEvent event) {
        if (!cliente.getUsuario().getSenha().equals(confirmPassword)) {
            viewContext.createError("msg_passwords_match");
        }
    }
    
    public String myAccount(){
        viewContext.setObjectInSession("cliente", cliente);
        return "/secure/customer/myAccount.jsf";
    }
    
    public String goUpdateAccount(){
        viewContext.setObjectInSession("cliente", cliente);
        return "/secure/customer/updateAccount.jsf";
    }
    
    public void prepareForUpdate(){
              
        this.cliente = clienteService.findBy("login", cliente.getUsuario().getLogin()).get(0);
        
        if(this.cliente.getUsuario().getPessoa() instanceof IPf ){
            this.pf = (IPf) this.cliente.getUsuario().getPessoa();
            this.typeCustomerPf = true;
        } else {
            this.pj = (IPJ) this.cliente.getUsuario().getPessoa();
            this.typeCustomerPf = false;
        }
        
      this.endereco = this.cliente.getUsuario().getPessoa().getEnderecos().get(0);
      
      for(ITelefone fone : this.cliente.getUsuario().getPessoa().getTelefones()){
          
          if(fone.getTipo().equals(TipoTelefone.COMERCIAL)){
              this.phoneComercial = fone;
          }
          if(fone.getTipo().equals(TipoTelefone.PRINCIPAL)){
              this.phonePrincipal = fone;
          }
          if(fone.getTipo().equals(TipoTelefone.RESIDENCIAL)){
              this.phoneResidencial = fone;
          }          
          
      }      
    }
    
    public void prepareForSave(){
        this.cliente.getUsuario().getPessoa().addEndereco(endereco);
        this.cliente.getUsuario().getPessoa().addTelefone(phonePrincipal);
        if(this.phoneComercial != null){
            cliente.getUsuario().getPessoa().addTelefone(phoneComercial);
        }
        if(this.phoneResidencial != null){
            this.cliente.getUsuario().getPessoa().addTelefone(phoneResidencial);
        }
        this.cliente.getUsuario().getPessoa().addEndereco(endereco);
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

    public ITelefone getPhonePrincipal() {
        return phonePrincipal;
    }

    public void setPhonePrincipal(ITelefone phonePrincipal) {
        this.phonePrincipal = phonePrincipal;
        this.phonePrincipal.setTipo(TipoTelefone.PRINCIPAL);
    }

    public ITelefone getPhoneComercial() {
        return phoneComercial;
    }

    public void setPhoneComercial(ITelefone phoneComercial) {
        this.phoneComercial = phoneComercial;
        if (this.phoneComercial != null) {
            this.phoneComercial.setTipo(TipoTelefone.COMERCIAL);
        }
    }

    public ITelefone getPhoneResidencial() {
        return phoneResidencial;
    }

    public void setPhoneResidencial(ITelefone phoneResidencial) {
        this.phoneResidencial = phoneResidencial;
        if(this.phoneResidencial != null){
            this.phoneResidencial.setTipo(TipoTelefone.RESIDENCIAL);
        }
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

    public IPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(IPerfil perfil) {
        this.perfil = perfil;
    }

    public List<SelectItem> getProdutosPreferencia() {
        List<SelectItem> selectProduts = new ArrayList<SelectItem>();

        for (Produto produto : recomendar.recomendarPreferencias()) {
            selectProduts.add(new SelectItem(produto, produto.getNome()));
        }

        return selectProduts;
    }
}
