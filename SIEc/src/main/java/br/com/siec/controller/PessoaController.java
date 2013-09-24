/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.controller;

import br.com.siec.model.persistence.entity.Cliente;
import br.com.siec.service.ClienteService;
import br.com.siec.service.qualifiers.ClienteServiceQualifier;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author josimar
 */
@ManagedBean(name = "pessoaController")
@RequestScoped
public class PessoaController implements Serializable {

    @Inject
    @ClienteServiceQualifier
    private ClienteService pessoaService;
    private Cliente pessoa;

    public void save() {
        if (this.pessoaService.save(pessoa)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorreu um erro durante o processo.", ""));
        }
    }

    public Cliente getPessoa() {
        return pessoa = pessoaService.create("PF");
    }

    public void setPessoa(Cliente pessoa) {
        this.pessoa = pessoa;
    }
}
