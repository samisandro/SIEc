package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.entity.Usuario;
import java.io.Serializable;
import java.util.List;

public interface IPessoa extends Serializable{

    public long getId();

    public void setId(long id);

    public String getNome();

    public void setNome(String nome);

    public String getEmail();

    public void setEmail(String email);

    public void addEndereco(IEndereco endereco);
    
    public void addEnderecos(List<IEndereco> enderecos);

    public List<IEndereco> getEnderecos();

    public void addTelefone(ITelefone telefone);
    
    public void addTelefones(List<ITelefone> telefones);

    public List<ITelefone> getTelefones();

    public void setUsuario(Usuario usuario);

    public Usuario getUsuario();
}
