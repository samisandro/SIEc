package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IMetodoDePagamento;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @version 1.0.0 17 November, 2013.
 * @author Josimar Alves
 */
@Entity
@Table(name = "TB_PAGAMENTO_NA_ENTREGA_PNE")
public class PagamentoNaEntrega implements IMetodoDePagamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PNE_CODIGO")
    private long id;
    
    @Column(name = "PNE_VALOR_RECEBIDO")
    private double valorRecebido;
    
    @Column(name = "PNE_TROCO")
    private double troco;    

    public PagamentoNaEntrega(double valorRecebido, double troco) {
        this.valorRecebido = valorRecebido;
        this.troco = troco;
    }  

    public PagamentoNaEntrega() {
    }
    
    @Override
    public void pagar(double valor) {
        System.out.println("Pagamento Efetuado na entrega........");
        System.out.println("Valor do Pagamento: R$ " + valor);
        System.out.println("Pagamento Efetuado.....");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }   

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }   
}
