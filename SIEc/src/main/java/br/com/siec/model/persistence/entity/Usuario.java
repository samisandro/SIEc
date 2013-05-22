package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoUsuario;
import java.util.Date;

public class Usuario implements IUsuario {

    private String login;
    private String senha;
    private String lembreteSenha;
    private Date dataCadastro;
    private TipoUsuario tipo;
    private IPessoa iPessoa;

    @Override
    public void Usuario(IPessoa pessoa) {
    }

    @Override
    public String getLogin() {
        return null;
    }

    public void setLogin(String login) {
    }

    @Override
    public String getSenha() {
        return null;
    }

    @Override
    public void setSenha(String senha) {
    }

    @Override
    public String getLembreteSenha() {
        return null;
    }

    @Override
    public void setLembreteSenha(String lembrete) {
    }

    @Override
    public Date getDataCadastro() {
        return null;
    }

    @Override
    public void setDataCadastro(Date dataCadastro) {
    }

    @Override
    public String getTipo() {
        return null;
    }

    @Override
    public void setTipo(TipoUsuario tipo) {
    }

    @Override
    public boolean getAtivo() {
        return false;
    }

    @Override
    public void setAtivo(boolean ativo) {
    }
}
