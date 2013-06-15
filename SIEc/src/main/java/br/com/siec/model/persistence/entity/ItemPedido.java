package br.com.siec.model.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.MetaValue;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "TB_ITEMPEDIDO_ITM")
@Audited
@AuditTable(value="TB_ITEMPEDIDO_AUDIT")
public class ItemPedido implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITM_CODIGO")
    private long id;
    
    @ManyToOne(targetEntity = Pedido.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "PDD_CODIGO", nullable = false, insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private IPedido pedido;
    @NotAudited
    @Any(metaColumn =
            @Column(name = "TIPO_PRODUTO"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Componente.class, value = "COMPONENTE"),
        @MetaValue(targetEntity = Acompanhamento.class, value = "ACOMPANHAMENTO"),
    })
    @JoinColumn(name = "CPT_CODIGO")
    private Produto produto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IPedido getPedido() {
        return this.pedido;
    }

    public void setPedido(IPedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }   
}
