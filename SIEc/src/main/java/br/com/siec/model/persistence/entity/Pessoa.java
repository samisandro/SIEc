package br.com.siec.model.persistence.entity;

import java.util.List;

public abstract class Pessoa implements IPessoa {
 
	private long id;
	 
	private String nome;
	 
	private String email;
	 
	private IEndereco endereco;
	 
	private ITelefone telefone;
	 
	private void Pessoa() {
	 
	}
	 
        @Override
	public long getId() {
		return 0;
	}
	 
        @Override
	public void setId(long id) {
	 
	}
	 
        @Override
	public String getNome() {
		return null;
	}
	 
        @Override
	public void setNome(String nome) {
	 
	}
	 
        @Override
	public String getEmail() {
		return null;
	}
	 
        @Override
	public void setEmail(String email) {
	 
	}
	 
        @Override
	public List<IEndereco> getEnderecos() {
		return null;
	}
	 
        @Override
	public void addEndereco(IEndereco endereco) {
	 
	}
	 
        @Override
	public List<ITelefone> getTelefones() {
		return null;
	}
	 
        @Override
	public void addTelefone(ITelefone telefone) {
	 
	}
	 
}
 
