package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.StatusPedido;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;
import org.hibernate.envers.Audited;

@Entity
@Table(name="TB_PEDIDO_PDD", schema="siec")
public class Pedido implements IPedido {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PDD_CODIGO")
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name="PDD_STATUS")
    private StatusPedido status;
    @Column(name="PDD_TOTAL")
    private double valorTotal;
    @Temporal(TemporalType.DATE)
    @Column(name="PDD_DATACOMPRA")
    private Date dataCompra;
    @Any(metaColumn =
            @Column(name = "TIPO_CLIENTE"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Cliente.class, value = "CLIENTE")})
    @JoinColumn(name = "CLT_CODIGO")
    private ICliente iCliente;
    @ManyToAny(metaColumn =
            @Column(name = "TIPO_ITEM"))
    @AnyMetaDef(idType = "long", metaType = "string", metaValues = {
        @MetaValue(targetEntity = Pf.class, value = "ITEM")})
    @Cascade({CascadeType.SAVE_UPDATE})
    @JoinTable(name = "TB_PEDIDO_ITENS_ASS", joinColumns =
            @JoinColumn(name = "PDD_CODIGO"),
            inverseJoinColumns =
            @JoinColumn(name = "ITM_CODIGO"))
    private List<ItemPedido> itens;

    @Override
    public long getid() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public StatusPedido getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    @Override
    public double getValorTotal() {
        return this.valorTotal;
    }

    @Override
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public Date getDataCompra() {
        return this.dataCompra;
    }

    @Override
    public void setDataCompra(Date data) {
        this.dataCompra = data;
    }

    @Override
    public ICliente getICliente() {
        return this.iCliente;
    }

    @Override
    public void setICliente(ICliente iCliente) {
        this.iCliente = iCliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void addItens(ItemPedido item) {
        this.itens.add(item);
    }        
}
