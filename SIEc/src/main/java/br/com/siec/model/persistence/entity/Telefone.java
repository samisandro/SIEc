package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.TipoTelefone;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "TB_TELEFONE_TLF", schema = "siec")
public class Telefone implements ITelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TLF_CODIGO")
    private long id;
    @Column(name="TLF_DDD")
    private String ddd;
    @Column(name="TLF_NUMERO")
    private String numero;
    @Enumerated(EnumType.STRING)
    @Column(name="TLF_TIPO")
    private TipoTelefone tipo;
    @Any(metaColumn =
            @Column(name = "TIPO_PESSOA"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Pf.class, value = "PF"),
        @MetaValue(targetEntity = Pj.class, value = "PJ")})
    @JoinColumn(name = "PSS_CODIGO")
    private IPessoa iPessoa;

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
        return this.iPessoa;
    }

    @Override
    public void setPessoa(IPessoa pessoa) {
        this.iPessoa = pessoa;
    }
}
