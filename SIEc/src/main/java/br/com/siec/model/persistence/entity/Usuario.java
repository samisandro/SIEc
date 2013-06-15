package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "TB_USUARIO_USR", schema = "siec")
@Audited
public class Usuario implements IUsuario, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USR_CODIGO")
    private Long id;
    @Column(name = "USR_LOGIN")
    private String login;
    @Column(name = "USR_SENHA")
    private String senha;
    @Column(name = "USR_LEMBRETE")
    private String lembreteSenha;
    @Temporal(TemporalType.DATE)
    @Column(name = "USR_DATACADASTRO")
    private Date dataCadastro;
    @Column(name = "USR_ATIVO", nullable = false)
    private boolean ativo;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    @OneToOne(targetEntity=Pessoa.class)
    private IPessoa pessoa;
    
    @OneToOne(mappedBy="usuario", targetEntity=Cliente.class)
    private ICliente cliente;

    public Usuario() {
    }

    
    public Usuario(IPessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public String getLogin() {
        return this.login;
    }
    
    @Override
    public void setPessoa(IPessoa p){
        this.pessoa = p;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getSenha() {
        return this.senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String getLembreteSenha() {
        return this.lembreteSenha;
    }

    @Override
    public void setLembreteSenha(String lembrete) {
        this.lembreteSenha = lembrete;
    }

    @Override
    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    @Override
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public TipoUsuario getTipo() {
        return this.tipo;
    }

    @Override
    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean getAtivo() {
        return this.ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 23 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 23 * hash + (this.lembreteSenha != null ? this.lembreteSenha.hashCode() : 0);
        hash = 23 * hash + (this.dataCadastro != null ? this.dataCadastro.hashCode() : 0);
        hash = 23 * hash + (this.ativo ? 1 : 0);
        hash = 23 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        hash = 23 * hash + (this.pessoa != null ? this.pessoa.hashCode() : 0);
        hash = 23 * hash + (this.cliente != null ? this.cliente.hashCode() : 0);
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
        final Usuario other = (Usuario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if ((this.lembreteSenha == null) ? (other.lembreteSenha != null) : !this.lembreteSenha.equals(other.lembreteSenha)) {
            return false;
        }
        if (this.dataCadastro != other.dataCadastro && (this.dataCadastro == null || !this.dataCadastro.equals(other.dataCadastro))) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.pessoa != other.pessoa && (this.pessoa == null || !this.pessoa.equals(other.pessoa))) {
            return false;
        }
        if (this.cliente != other.cliente && (this.cliente == null || !this.cliente.equals(other.cliente))) {
            return false;
        }
        return true;
    }
}
