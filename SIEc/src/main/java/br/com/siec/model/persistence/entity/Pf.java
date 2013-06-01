package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoSexo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@PrimaryKeyJoinColumn(name = "PSF_CODIGO")
@Table(name="TB_PESSOAFISICA_PSF", schema = "siec")
public class Pf extends Pessoa implements IPf {

    @Column(name="PSF_CPF")
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @Enumerated(EnumType.STRING)
    private TipoSexo sexo;

    @Override
    public String getCpf() {
        return this.cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    @Override
    public void setDataNascimento(Date data) {
        this.dataNascimento = data;
    }

    @Override
    public TipoSexo getSexo() {
        return this.sexo;
    }

    @Override
    public void setSexo(TipoSexo sexo) {
        this.sexo = sexo;
    }
}
