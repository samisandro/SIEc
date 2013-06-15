package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.StatusPedido;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name="TB_PEDIDO_PDD", schema="siec")
@Audited
@AuditTable(value="TB_PEDIDO_AUDIT")
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
    
    @ManyToOne(targetEntity = Cliente.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CLT_CODIGO", nullable = false, insertable = true, updatable = true)
    @Fetch(FetchMode.JOIN)
    @Cascade(CascadeType.SAVE_UPDATE)
    private ICliente cliente;
    
    @NotAudited
    @OneToMany(targetEntity=ItemPedido.class, mappedBy="pedido", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
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
        return this.cliente;
    }

    @Override
    public void setICliente(ICliente iCliente) {
        this.cliente = iCliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void addItens(ItemPedido item) {
        this.itens.add(item);
    }        
}
