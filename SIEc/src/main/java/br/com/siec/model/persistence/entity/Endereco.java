package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IEndereco;
import br.com.siec.model.persistence.interfaces.IPessoa;

import br.com.siec.model.persistence.resource.Estados;
import br.com.siec.model.persistence.resource.TipoEndereco;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @version 1.00 May 21, 2013.
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_ENDERECO_END", schema = "siec")
public class Endereco implements IEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "END_CODIGO")
    private long id;
    
    @Column(name = "END_LOGRADOURO")
    private String logradouro;
    
    @Column(name = "END_NUMERO")
    private int numero;
    
    @Column(name = "END_COMPLEMENTO")
    private String complemento;
    
    @Column(name = "END_BAIRRO")
    private String bairro;
    
    @Column(name = "END_CIDADE")
    private String cidade;
    
    @Column(name = "END_CEP")
    private String cep;
    
    @Column(name = "END_ESTADO")
    private Estados estado;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "END_TIPO")
    private TipoEndereco tipoEndereco;
    /*
     * Relacionamento 3:1 - Endereco : Pessoa
     */
    @ManyToMany(targetEntity = Pessoa.class, mappedBy = "enderecos", fetch = FetchType.LAZY)
    private Collection<IPessoa> pessoas = new ArrayList<IPessoa>();

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getLogradouro() {
        return this.logradouro;
    }

    @Override
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Override
    public int getNumero() {
        return this.numero;
    }

    @Override
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String getComplemento() {
        return this.complemento;
    }

    @Override
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    @Override
    public String getBairro() {
        return this.bairro;
    }

    @Override
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    @Override
    public String getCidade() {
        return this.cidade;
    }

    @Override
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String getCep() {
        return this.cep;
    }

    @Override
    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public Estados getEstado() {
        return this.estado;
    }

    @Override
    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    @Override
    public TipoEndereco getTipoEndereco() {
        return this.tipoEndereco;
    }

    @Override
    public void setTipoEndereco(TipoEndereco tipo) {
        this.tipoEndereco = tipo;
    }

    @Override
    public Collection<IPessoa> getPessoas() {
        return this.pessoas;
    }

    @Override
    public void addPessoa(IPessoa pessoa) {
        this.pessoas.add(pessoa);
    }

    @Override
    public String toString() {
        return "Endereco{" + "id=" + id + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento=" + complemento + ", cep=" + cep + ", estado=" + estado + ", tipoEndereco=" + tipoEndereco + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.logradouro != null ? this.logradouro.hashCode() : 0);
        hash = 37 * hash + this.numero;
        hash = 37 * hash + (this.complemento != null ? this.complemento.hashCode() : 0);
        hash = 37 * hash + (this.bairro != null ? this.bairro.hashCode() : 0);
        hash = 37 * hash + (this.cidade != null ? this.cidade.hashCode() : 0);
        hash = 37 * hash + (this.cep != null ? this.cep.hashCode() : 0);
        hash = 37 * hash + (this.estado != null ? this.estado.hashCode() : 0);
        hash = 37 * hash + (this.tipoEndereco != null ? this.tipoEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if ((this.logradouro == null) ? (other.logradouro != null) : !this.logradouro.equals(other.logradouro)) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if ((this.complemento == null) ? (other.complemento != null) : !this.complemento.equals(other.complemento)) {
            return false;
        }
        if ((this.bairro == null) ? (other.bairro != null) : !this.bairro.equals(other.bairro)) {
            return false;
        }
        if ((this.cidade == null) ? (other.cidade != null) : !this.cidade.equals(other.cidade)) {
            return false;
        }
        if ((this.cep == null) ? (other.cep != null) : !this.cep.equals(other.cep)) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (this.tipoEndereco != other.tipoEndereco) {
            return false;
        }
        return true;
    }
    
}
