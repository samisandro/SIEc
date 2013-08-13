package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IPedido;
import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.business.pricestrategy.MultiplePrice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "TB_PRODUTO_PRT", schema = "siec")
@Inheritance(strategy = InheritanceType.JOINED)
@Audited
@AuditTable(value = "TB_PRODUTO_AUDIT")
public abstract class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRT_CODIGO")
    private long id;
    @Column(name = "PRT_NOME", length = 30, insertable = true, updatable = true)
    private String nome;
    @NotAudited
    @Column(name = "PRT_PRECOS")
    @ElementCollection
    @CollectionTable(name = "TB_PRECO_PRC", joinColumns =
            @JoinColumn(name = "PRT_CODIGO"))
    private List<Preco> precos = new ArrayList<Preco>();
    @Column(name = "PRT_CATEGORIA")
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    @OneToOne(mappedBy = "produto")
    @Cascade(CascadeType.ALL)
    private Imagem imagem;
    @ManyToMany(targetEntity = Pedido.class, mappedBy = "itens")
    private Collection<IPedido> pedidos = new ArrayList<IPedido>();
    @Transient
    private MultiplePrice typePrice;

    public Produto() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categorias getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Imagem getImagem() {
        return this.imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public Collection<IPedido> getPedidos() {
        return this.pedidos;
    }

    public void addPreco(Preco preco) {
        this.precos.add(preco);
    }

    public List<Preco> getPrecos() {
        return this.precos;
    }

    public MultiplePrice getTypePrice() {
        return this.typePrice;
    }

    public void setTypePrice(MultiplePrice typePrice) {
        this.typePrice = typePrice;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", precos=" + precos + ", categoria=" + categoria + ", imagem=" + imagem + ", pedidos=" + pedidos + ", typePrice=" + typePrice + '}';
    }
}
