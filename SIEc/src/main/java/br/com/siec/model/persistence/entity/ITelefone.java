package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoTelefone;
import java.util.List;

public interface ITelefone {
 
	public long getId();
	public void setId(long id);
	public long getNumero();
	public void setNumero(long numero);
	public String getDdd();
	public void setDdd(String ddd);
	public TipoTelefone getTipo();
	public void setTipo(TipoTelefone tipo);
	public List<IPessoa> getPessoas();
	public void addPessoa(IPessoa pessoa);
}
 
