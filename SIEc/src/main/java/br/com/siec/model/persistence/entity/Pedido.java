package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.StatusPedido;
import java.util.Date;

public class Pedido implements IPedido {

    private long id;
    private StatusPedido status;
    private double valorTotal;
    private Date dataCompra;
    private ICliente iCliente;
    private ItemPedido itemPedido;

    @Override
    public long getid() {
        return 0;
    }

    @Override
    public void setId(long id) {
    }

    @Override
    public StatusPedido getStatus() {
        return null;
    }

    @Override
    public void setStatus(StatusPedido status) {
    }

    @Override
    public double getValorTotal() {
        return 0;
    }

    @Override
    public void setValorTotal(double valotTotal) {
    }

    @Override
    public Date getDataCompra() {
        return null;
    }

    @Override
    public void setDataCompra(Date data) {
    }
}
