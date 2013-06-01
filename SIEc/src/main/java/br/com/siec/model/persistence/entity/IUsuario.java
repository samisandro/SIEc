package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoUsuario;
import java.io.Serializable;
import java.util.Date;

public interface IUsuario extends Serializable {

    public String getLogin();
    
    public void setLogin(String login);
    
    public void setPessoa(IPessoa p);

    public String getSenha();

    public void setSenha(String senha);

    public String getLembreteSenha();

    public void setLembreteSenha(String lembrete);

    public Date getDataCadastro();

    public void setDataCadastro(Date dataCadastro);

    public TipoUsuario getTipo();

    public void setTipo(TipoUsuario tipo);

    public boolean getAtivo();

    public void setAtivo(boolean ativo);
}
