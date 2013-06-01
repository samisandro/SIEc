package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.StatusPedido;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IPedido extends Serializable {

    public long getid();

    public void setId(long id);

    public StatusPedido getStatus();

    public void setStatus(StatusPedido status);

    public double getValorTotal();

    public void setValorTotal(double valotTotal);

    public Date getDataCompra();

    public void setDataCompra(Date data);

    public ICliente getICliente();

    public void setICliente(ICliente iCliente);

    public List<ItemPedido> getItens();

    public void addItens(ItemPedido item);
}
