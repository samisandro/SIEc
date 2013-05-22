package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.util.StatusPedido;
import java.util.Date;

public interface IPedido {
 
	public long getid();
	public void setId(long id);
	public StatusPedido getStatus();
	public void setStatus(StatusPedido status);
	public double getValorTotal();
	public void setValorTotal(double valotTotal);
	public Date getDataCompra();
	public void setDataCompra(Date data);
}
 
