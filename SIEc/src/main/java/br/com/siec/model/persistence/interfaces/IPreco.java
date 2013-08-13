/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siec.model.persistence.interfaces;

import br.com.siec.model.persistence.util.TipoPreco;
import java.io.Serializable;

/**
 *
 * @author josimar
 */
public interface IPreco extends Serializable {

    public TipoPreco getTipo();

    public void setTipo(TipoPreco tipo);

    public double getValor();

    public void setValor(double valor);
}
