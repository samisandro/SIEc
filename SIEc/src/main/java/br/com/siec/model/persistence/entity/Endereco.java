package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.Estados;
import br.com.siec.model.persistence.util.TipoEndereco;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "TB_ENDERECO_END", schema = "siec")
public class Endereco implements IEndereco, Serializable {

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
    @Column(name = "END_CEP")
    private String cep;
    @Column(name = "END_ESTADO")
    private Estados estado;
    @Enumerated(EnumType.STRING)
    @Column(name = "END_TIPO")
    private TipoEndereco tipoEndereco;
    @ManyToAny(metaColumn =
            @Column(name = "TIPO_PESSOA"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Pf.class, value = "PF"),
        @MetaValue(targetEntity = Pj.class, value = "PJ")})
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_PESSOA_ENDERECO_ASS", joinColumns =
            @JoinColumn(name = "END_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "PSS_CODIGO"))
    private List<IPessoa> pessoas;

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
    public List<IPessoa> getPessoas() {
        return this.pessoas;
    }

    @Override
    public void addPessoa(IPessoa pessoa) {
        this.pessoas.add(pessoa);
    }
}
