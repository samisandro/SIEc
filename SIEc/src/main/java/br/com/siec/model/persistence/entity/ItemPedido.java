package br.com.siec.model.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;

@Entity
@Table(name = "TB_ITEMPEDIDO_ITM")
public class ItemPedido implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITM_CODIGO")
    private long id;
    @Any(metaColumn =
            @Column(name = "TIPO_PEDIDO"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Pedido.class, value = "PEDIDO")})
    @JoinColumn(name = "PDD_CODIGO")
    private IPedido pedido;
    
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
