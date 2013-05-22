package br.com.siec.model.persistence.entity;

public interface IPJ extends IPessoa {
 
	public String getCnpj();
	public void setCnpj(String cnpj);
	public String getRazaoSocial();
	public void setRazaoSocial(String razaoSocial);
	public String getInscricaoEstadual();
	public void setInscricaoEstadual(String inscricaoEstadual);
}
 
