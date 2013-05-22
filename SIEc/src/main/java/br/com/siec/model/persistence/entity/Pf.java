package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoSexo;
import java.util.Date;

public class Pf extends Pessoa implements IPf {

    private String cpf;
    private Date dataNascimento;
    private TipoSexo sexo;

    @Override
    public String getCpf() {
        return null;
    }

    @Override
    public void setCpf(String cpf) {
    }

    @Override
    public Date getDataNascimento() {
        return null;
    }

    @Override
    public void setDataNascimento(Date data) {
    }

    @Override
    public TipoSexo getSexo() {
        return null;
    }

    @Override
    public void setSexo(TipoSexo sexo) {
    }
}
