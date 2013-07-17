package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.Categorias;
import br.com.siec.util.factory.AbstractFactory;
import br.com.siec.util.factory.ApplicationFactory;
import br.com.siec.util.factory.ClassType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

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
    @Column(name = "PRT_PRECOS")
    @ElementCollection(fetch = FetchType.LAZY)
    private Map<String, Double> precos = new HashMap<String, Double>();
    @Column(name = "PRT_CATEGORIA")
    @Enumerated(EnumType.STRING)
    private Categorias categoria;
    @OneToOne(mappedBy = "produto")
    @Cascade(CascadeType.ALL)
    private Imagem imagem;
    @ManyToOne(fetch=FetchType.EAGER, targetEntity = Produto.class)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "PRT_COMPOSICAO")
    private Produto produto;
    @OneToMany(mappedBy = "produto", targetEntity = Produto.class, fetch=FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    @Fetch(FetchMode.SUBSELECT)
    private List<Produto> produtos = new ArrayList<Produto>();

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

    public void addPreco(String chave, Double preco) {
        this.precos.put(chave, preco);
    }

    public Map<String, Double> getPrecos() {
        return this.precos;
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

    public List<Produto> getProdutos() {
        return this.produtos;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void addComponente(Produto componente) {
    }

    public void removeComponente(Produto componente) {
    }

    public Produto getComponente(int i) {
        return null;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", precos=" + precos + ", categoria=" + categoria + ", imagem=" + imagem + ", produto=" + produto + ", produtos=" + produtos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 59 * hash + (this.precos != null ? this.precos.hashCode() : 0);
        hash = 59 * hash + (this.categoria != null ? this.categoria.hashCode() : 0);
        hash = 59 * hash + (this.imagem != null ? this.imagem.hashCode() : 0);
        hash = 59 * hash + (this.produto != null ? this.produto.hashCode() : 0);
        hash = 59 * hash + (this.produtos != null ? this.produtos.hashCode() : 0);
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
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if (this.precos != other.precos && (this.precos == null || !this.precos.equals(other.precos))) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        if (this.imagem != other.imagem && (this.imagem == null || !this.imagem.equals(other.imagem))) {
            return false;
        }
        if (this.produto != other.produto && (this.produto == null || !this.produto.equals(other.produto))) {
            return false;
        }
        if (this.produtos != other.produtos && (this.produtos == null || !this.produtos.equals(other.produtos))) {
            return false;
        }
        return true;
    }
}
