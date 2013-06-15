package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoTelefone;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "TB_TELEFONE_TLF", schema = "siec")
@Audited
@AuditTable(value="TB_TELEFONE_AUDIT")
public class Telefone implements ITelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TLF_CODIGO")
    private long id;
    @Column(name = "TLF_DDD")
    private String ddd;
    @Column(name = "TLF_NUMERO")
    private String numero;
    @Enumerated(EnumType.STRING)
    @Column(name = "TLF_TIPO")
    private TipoTelefone tipo;
    @ManyToOne(targetEntity = Pessoa.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "PSS_CODIGO", nullable = false, insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private IPessoa pessoa;

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getNumero() {
        return this.numero;
    }

    @Override
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String getDdd() {
        return this.ddd;
    }

    @Override
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    @Override
    public TipoTelefone getTipo() {
        return this.tipo;
    }

    @Override
    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    @Override
    public IPessoa getPessoa() {
        return this.pessoa;
    }

    @Override
    public void setPessoa(IPessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 17 * hash + (this.ddd != null ? this.ddd.hashCode() : 0);
        hash = 17 * hash + (this.numero != null ? this.numero.hashCode() : 0);
        hash = 17 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        hash = 17 * hash + (this.pessoa != null ? this.pessoa.hashCode() : 0);
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
        final Telefone other = (Telefone) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.ddd == null) ? (other.ddd != null) : !this.ddd.equals(other.ddd)) {
            return false;
        }
        if ((this.numero == null) ? (other.numero != null) : !this.numero.equals(other.numero)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.pessoa != other.pessoa && (this.pessoa == null || !this.pessoa.equals(other.pessoa))) {
            return false;
        }
        return true;
    }
}
