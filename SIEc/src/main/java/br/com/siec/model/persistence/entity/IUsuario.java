package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoUsuario;
import java.util.Date;

public interface IUsuario {

    public void Usuario(IPessoa pessoa);

    public String getLogin();

    public String getSenha();

    public void setSenha(String senha);

    public String getLembreteSenha();

    public void setLembreteSenha(String lembrete);

    public Date getDataCadastro();

    public void setDataCadastro(Date dataCadastro);

    public String getTipo();

    public void setTipo(TipoUsuario tipo);

    public boolean getAtivo();

    public void setAtivo(boolean ativo);
}
