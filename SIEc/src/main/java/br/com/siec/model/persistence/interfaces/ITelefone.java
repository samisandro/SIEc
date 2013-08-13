package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.util.TipoTelefone;
import java.io.Serializable;

public interface ITelefone extends Serializable{
 
	public long getId();
	public void setId(long id);
	public String getNumero();
	public void setNumero(String numero);
	public String getDdd();
	public void setDdd(String ddd);
	public TipoTelefone getTipo();
	public void setTipo(TipoTelefone tipo);
	public IPessoa getPessoa();
	public void setPessoa(IPessoa pessoa);
}
 
