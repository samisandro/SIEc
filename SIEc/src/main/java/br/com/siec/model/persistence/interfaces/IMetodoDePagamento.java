package br.com.siec.model.persistence.interfaces;

import java.io.Serializable;

/**
 * Metodo de Pagamento
 * @version 1.0.0
 * @author Josimar Alves
 */
public interface IMetodoDePagamento extends Serializable{
    
    public void pagar(double valor);
    
}
