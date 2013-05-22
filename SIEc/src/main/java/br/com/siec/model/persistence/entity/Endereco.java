package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.Estados;
import br.com.siec.model.persistence.util.TipoEndereco;
import java.util.List;

public class Endereco implements IEndereco {
 
	private long id;
	 
	private String logradouro;
	 
	private int numero;
	 
	private String complemento;
	 
	private String cep;
	 
	private Estados estado;
	 
	private TipoEndereco tipoEndereco;
	 
	private List<IPessoa> pessoas;
	 
        @Override
	public void setId(long id) {
	 
	}
	 
        @Override
	public long getId() {
		return 0;
	}
	 
        @Override
	public String getLogradouro() {
		return null;
	}
	 
        @Override
	public void setLogradouro(String logradouro) {
	 
	}
	 
        @Override
	public int getNumero() {
		return 0;
	}
	 
        @Override
	public void setNumero(int numero) {
	 
	}
	 
        @Override
	public String getComplemento() {
		return null;
	}
	 
        @Override
	public void setComplemento(String complemento) {
	 
	}
	 
        @Override
	public String getCep() {
		return null;
	}
	 
        @Override
	public void setCep(String cep) {
	 
	}
	 
        @Override
	public Estados getEstado() {
		return null;
	}
	 
        @Override
	public void setEstado(Estados estado) {
	 
	}
	 
        @Override
	public TipoEndereco getTipoEndereco() {
		return null;
	}
	 
        @Override
	public void setTipoEndereco(TipoEndereco tipo) {
	 
	}
	 
        @Override
	public List<IPessoa> getPessoas() {
		return null;
	}
	 
        @Override
	public void addPessoa(IPessoa pessoa) {
	 
	}
	 
}
 
