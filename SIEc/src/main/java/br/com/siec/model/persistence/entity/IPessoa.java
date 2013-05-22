package br.com.siec.model.persistence.entity;

import java.util.List;

public interface IPessoa {

    public long getId();

    public void setId(long id);

    public String getNome();

    public void setNome(String nome);

    public String getEmail();

    public void setEmail(String email);

    public void addEndereco(IEndereco endereco);

    public List<IEndereco> getEnderecos();

    public void addTelefone(ITelefone telefone);

    public List<ITelefone> getTelefones();
}
