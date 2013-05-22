package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoTelefone;
import java.util.List;

public class Telefone implements ITelefone {
 
	private long id;
	 
	private String ddd;
	 
	private String numero;
	 
	private TipoTelefone tipo;
	 
	private IPessoa iPessoa;
	 
        @Override
	public long getId() {
		return 0;
	}
	 
        @Override
	public void setId(long id) {
	 
	}
	 
        @Override
	public long getNumero() {
		return 0;
	}
	 
        @Override
	public void setNumero(long numero) {
	 
	}
	 
        @Override
	public String getDdd() {
		return null;
	}
	 
        @Override
	public void setDdd(String ddd) {
	 
	}
	 
        @Override
	public TipoTelefone getTipo() {
		return null;
	}
	 
        @Override
	public void setTipo(TipoTelefone tipo) {
	 
	}
	 
        @Override
	public List<IPessoa> getPessoas() {
		return null;
	}
	 
        @Override
	public void addPessoa(IPessoa pessoa) {
	 
	}
	 
}
 
