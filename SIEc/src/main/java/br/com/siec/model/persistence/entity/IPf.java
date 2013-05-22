package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoSexo;
import java.util.Date;

public interface IPf extends IPessoa {
 
	public String getCpf();
	public void setCpf(String cpf);
	public Date getDataNascimento();
	public void setDataNascimento(Date data);
	public TipoSexo getSexo();
	public void setSexo(TipoSexo sexo);
}
 
