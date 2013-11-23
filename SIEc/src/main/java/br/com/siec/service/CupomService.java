package br.com.siec.service;

import br.com.siec.model.persistence.entity.CupomDesconto;

/**
 * Facade para as operações relacionadas aos Cupons de Desconto.
 * @version 1.0.0 17, November 2013.
 * @author Josimar Alves
 */
public interface CupomService {
    
    public boolean criarCupom(CupomDesconto cupom);
    
    public String gerarCodigoDoCupom();
    
    public boolean isCodigoDoCupomValido(String codigoCupom);
    
    public boolean alterarCupom(CupomDesconto cupom);
    
    
}

