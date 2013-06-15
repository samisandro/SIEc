/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.control.beans;

import br.com.siec.model.persistence.entity.Pessoa;
import br.com.siec.model.persistence.entity.Pf;
import br.com.siec.service.Service;
import br.com.siec.service.qualifiers.PessoaServiceQualifier;
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
@ManagedBean(name = "pessoaMB")
@RequestScoped
public class PessoaBean implements Serializable {

    @Inject @PessoaServiceQualifier
    private Service<Pessoa> pessoaService;
    private Pessoa pessoa;
    
    public PessoaBean(){
        this.pessoa = this.pessoaService.create("PF");
    }

    public void save() {
        if (this.pessoaService.save(pessoa)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Ocorreu um erro durante o processo.", ""));
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
