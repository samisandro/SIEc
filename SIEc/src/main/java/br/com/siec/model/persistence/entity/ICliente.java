package br.com.siec.model.persistence.entity;

import java.io.Serializable;
import java.util.List;

public interface ICliente extends Serializable {

    public long getId();

    public void setId(long id);

    public List<IPedido> getPedidos();

    public void addPedido(IPedido pedido);
}
