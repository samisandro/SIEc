/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.entity;

import br.com.siec.model.persistence.interfaces.IPreco;
import br.com.siec.model.persistence.util.TipoPreco;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author josimar
 */
@Embeddable
public class Preco implements IPreco, Serializable  {

    @Enumerated(EnumType.STRING)
    @Column(name = "PRC_TIPO")
    private TipoPreco tipo;
    @Column(name = "PRC_VALOR")
    private double valor;

    public Preco() {
    }
    
    public Preco(TipoPreco tipo, double valor){
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public TipoPreco getTipo() {
        return this.tipo;
    }

    @Override
    public void setTipo(TipoPreco tipo) {
        this.tipo = tipo;
    }

    @Override
    public double getValor() {
        return this.valor;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Preco{" + "tipo=" + tipo + ", valor=" + valor + '}';
    }    
}
