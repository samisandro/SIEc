package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.entity.Item;
import br.com.siec.model.persistence.resource.StatusPedido;

import java.io.Serializable;
import java.util.Collection;

import java.util.Date;

public interface IPedido extends Serializable {

    public long getid();

    public void setId(long id);

    public StatusPedido getStatus();

    public void setStatus(StatusPedido status);

    public double getValorTotal();

    public void setValorTotal(double valotTotal);

    public Date getDataCompra();

    public void setDataCompra(Date data);

    public ICliente getCliente();

    public void setCliente(ICliente iCliente);

    public Collection<Item> getItens();

    public void addItens(Item item);
    
    public ICupomDesconto getCupomDesconto();
    
    public void setCupomDesconto(ICupomDesconto cupom);
}
