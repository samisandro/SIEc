package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.Estados;
import br.com.siec.model.persistence.util.TipoEndereco;
import java.util.List;

public interface IEndereco {
 
	public void setId(long id);
	public long getId();
	public String getLogradouro();
	public void setLogradouro(String logradouro);
	public int getNumero();
	public void setNumero(int numero);
	public String getComplemento();
	public void setComplemento(String complemento);
	public String getCep();
	public void setCep(String cep);
	public Estados getEstado();
	public void setEstado(Estados estado);
	public TipoEndereco getTipoEndereco();
	public void setTipoEndereco(TipoEndereco tipo);
	public List<IPessoa> getPessoas();
	public void addPessoa(IPessoa pessoa);
}
 
