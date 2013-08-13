package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IPJ;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "PSJ_CODIGO")
@Table(name="TB_PESSOAJURIDICA_PSJ", schema = "siec")
public class Pj extends Pessoa implements IPJ {

    @Column(name = "PSJ_CNPJ")
    private String cnpj;
    @Column(name = "PSJ_RAZAOSOCIAL")
    private String razaoSocial;
    @Column(name = "PSJ_INSCRICAO")
    private String inscricaoEstadual;

    @Override
    public String getCnpj() {
        return this.cnpj;
    }

    @Override
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    @Override
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    @Override
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }
}
