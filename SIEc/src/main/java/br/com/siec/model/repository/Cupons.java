package br.com.siec.model.repository;

import br.com.siec.model.persistence.entity.CupomDesconto;

/**
 * Repositorio dos Cupons de Desconto
 * @version 1.0.0 17, November 2013.
 * @author Josimar Alves
 */
public interface Cupons {
    
    public boolean criarCupom(CupomDesconto cupom);
    
    public boolean alterarCupom(CupomDesconto cupom);
    
    public boolean isCodigoCupomValido(String codigo);
    
}
